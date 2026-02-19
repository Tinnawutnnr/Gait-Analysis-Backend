package com.gaitanalyze.gaitanalysisapp.auth.impl;

import com.gaitanalyze.gaitanalysisapp.auth.AuthService;
import com.gaitanalyze.gaitanalysisapp.auth.RefreshTokenRepo;
import com.gaitanalyze.gaitanalysisapp.caretaker.Caretaker;
import com.gaitanalyze.gaitanalysisapp.caretaker.CaretakerRepository;
import com.gaitanalyze.gaitanalysisapp.dto.AuthResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private CaretakerRepository caretakerRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private RefreshTokenRepo refreshTokenRepo;

    @Override
    public AuthResponse register(Caretaker caretaker) {
        return null;
    }

    @Override
    public AuthResponse login(String email, String password) {
        return null;
    }
}
