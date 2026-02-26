package com.gaitanalyze.gaitanalysisapp.auth;

import com.gaitanalyze.gaitanalysisapp.dto.AuthResponse;
import com.gaitanalyze.gaitanalysisapp.dto.LoginRequest;
import com.gaitanalyze.gaitanalysisapp.dto.LogoutRequest;
import com.gaitanalyze.gaitanalysisapp.dto.RefreshTokenRequest;
import com.gaitanalyze.gaitanalysisapp.user.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody User request){
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request){
        AuthResponse response = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@Valid @RequestBody RefreshTokenRequest request){
        AuthResponse response = authService.refreshToken(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> refresh(@Valid @RequestBody LogoutRequest logoutRequest){
         authService.logout(logoutRequest.getRefreshToken());
        return new ResponseEntity<>("Logout successfully.", HttpStatus.OK);
    }
}
