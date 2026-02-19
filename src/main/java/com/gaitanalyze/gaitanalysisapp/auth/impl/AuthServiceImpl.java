package com.gaitanalyze.gaitanalysisapp.auth.impl;

import com.gaitanalyze.gaitanalysisapp.auth.AuthService;
import com.gaitanalyze.gaitanalysisapp.auth.JwtService;
import com.gaitanalyze.gaitanalysisapp.auth.RefreshToken;
import com.gaitanalyze.gaitanalysisapp.auth.RefreshTokenRepo;
import com.gaitanalyze.gaitanalysisapp.caretaker.Caretaker;
import com.gaitanalyze.gaitanalysisapp.caretaker.CaretakerRepository;
import com.gaitanalyze.gaitanalysisapp.dto.AuthResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private CaretakerRepository caretakerRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private RefreshTokenRepo refreshTokenRepo;

    public AuthServiceImpl(CaretakerRepository caretakerRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, RefreshTokenRepo refreshTokenRepo) {
        this.caretakerRepository = caretakerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.refreshTokenRepo = refreshTokenRepo;
    }

    @Override
    public AuthResponse register(Caretaker request) {

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Caretaker savedCaretaker = caretakerRepository.save(request);

        String jwtToken = jwtService.generateToken(savedCaretaker.getEmail());
        String refreshToken = createRefreshToken(savedCaretaker);

        return new AuthResponse(jwtToken, refreshToken);

    }

    @Override
    public AuthResponse login(String email, String password) {
        //check password according to given email.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        Caretaker caretaker = caretakerRepository.findByEmail(email).
                orElseThrow(()->new IllegalArgumentException("Invalid email or password."));

        String jwtToken = jwtService.generateToken(caretaker.getEmail());
        String refreshToken = createRefreshToken(caretaker);

        return new AuthResponse(jwtToken, refreshToken);
    }

    private String createRefreshToken(Caretaker caretaker){

        var existingToken = refreshTokenRepo.findByCaretaker(caretaker);

        existingToken.ifPresent(refreshToken -> refreshTokenRepo.delete(refreshToken));

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCaretaker(caretaker);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(604800000));//7days

        refreshTokenRepo.save(refreshToken);
        return refreshToken.getToken();
    }
}
