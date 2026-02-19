package com.gaitanalyze.gaitanalysisapp.auth;

import com.gaitanalyze.gaitanalysisapp.caretaker.Caretaker;
import com.gaitanalyze.gaitanalysisapp.dto.AuthResponse;

public interface AuthService {
    AuthResponse register(Caretaker caretaker);
    AuthResponse login(String email, String password);
}
