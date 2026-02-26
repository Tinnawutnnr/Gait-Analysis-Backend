package com.gaitanalyze.gaitanalysisapp.auth;


import com.gaitanalyze.gaitanalysisapp.dto.AuthResponse;
import com.gaitanalyze.gaitanalysisapp.dto.RefreshTokenRequest;
import com.gaitanalyze.gaitanalysisapp.user.User;
import jakarta.validation.Valid;

public interface AuthService {
    AuthResponse register(User user);
    AuthResponse login(String username, String password);
    AuthResponse refreshToken(@Valid RefreshTokenRequest request);
    void logout(@Valid String token);
}
