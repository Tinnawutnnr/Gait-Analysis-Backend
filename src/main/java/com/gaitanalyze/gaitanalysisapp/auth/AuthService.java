package com.gaitanalyze.gaitanalysisapp.auth;

import com.gaitanalyze.gaitanalysisapp.caretaker.Caretaker;
import com.gaitanalyze.gaitanalysisapp.dto.AuthResponse;
import com.gaitanalyze.gaitanalysisapp.dto.RefreshTokenRequest;

public interface AuthService {
    AuthResponse register(Caretaker caretaker);
    AuthResponse login(String email, String password);
    AuthResponse refreshToken(RefreshTokenRequest request);
}
