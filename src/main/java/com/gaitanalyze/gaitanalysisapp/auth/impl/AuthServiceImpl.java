package com.gaitanalyze.gaitanalysisapp.auth.impl;

import com.gaitanalyze.gaitanalysisapp.auth.AuthService;
import com.gaitanalyze.gaitanalysisapp.auth.JwtService;
import com.gaitanalyze.gaitanalysisapp.auth.RefreshToken;
import com.gaitanalyze.gaitanalysisapp.auth.RefreshTokenRepo;
import com.gaitanalyze.gaitanalysisapp.dto.AuthResponse;
import com.gaitanalyze.gaitanalysisapp.dto.RefreshTokenRequest;
import com.gaitanalyze.gaitanalysisapp.exception.ResourceNotFoundException;
import com.gaitanalyze.gaitanalysisapp.user.User;
import com.gaitanalyze.gaitanalysisapp.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private RefreshTokenRepo refreshTokenRepo;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, RefreshTokenRepo refreshTokenRepo) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.refreshTokenRepo = refreshTokenRepo;
    }

    @Override
    public AuthResponse register(User request) {

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(request);

        String jwtToken = jwtService.generateToken(savedUser.getUsername());
        String refreshToken = String.valueOf(createRefreshToken(savedUser));

        return new AuthResponse(jwtToken, refreshToken);

    }

    @Override
    public AuthResponse login(String username, String password) {
        //check password according to given email.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        User user = userRepository.findByUsername(username).
                orElseThrow(()->new IllegalArgumentException("Invalid username or password."));

        String jwtToken = jwtService.generateToken(user.getUsername());
        String refreshToken = String.valueOf(createRefreshToken(user));

        return new AuthResponse(jwtToken, refreshToken);
    }

    @Override
    public AuthResponse refreshToken(@org.checkerframework.checker.nullness.qual.MonotonicNonNull @Valid RefreshTokenRequest request) {

        String requestToken = request.getRefreshToken();

        RefreshToken refreshToken = refreshTokenRepo.findByToken(requestToken)
                .orElseThrow(()->new IllegalArgumentException("Refresh token not found."));

        if(refreshToken.getExpiryDate().compareTo(Instant.now()) < 0){
            refreshTokenRepo.delete(refreshToken);
            throw new IllegalArgumentException("Refresh token expired. Please login again.");
        }

        User user = refreshToken.getUser();
        String newJwtToken = jwtService.generateToken(user.getUsername());

        return new AuthResponse(newJwtToken, requestToken);
    }

    @Override
    @Transactional
    public void logout(String reqToken) {

        String cleanToken = reqToken.trim();

        int deletedRows = refreshTokenRepo.deleteByTokenDirectly(cleanToken);


        if (deletedRows == 0) {
            throw new ResourceNotFoundException("Refresh token not found.");
        }
    }


    private String createRefreshToken(User user){

        var existingToken = refreshTokenRepo.findByUser(user);

        existingToken.ifPresent(refreshToken -> refreshTokenRepo.delete(refreshToken));

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(604800000));//7days

        refreshTokenRepo.save(refreshToken);
        return refreshToken.getToken();
    }
}
