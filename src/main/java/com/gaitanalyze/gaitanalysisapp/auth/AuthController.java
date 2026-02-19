package com.gaitanalyze.gaitanalysisapp.auth;

import com.gaitanalyze.gaitanalysisapp.caretaker.Caretaker;
import com.gaitanalyze.gaitanalysisapp.caretaker.CaretakerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private CaretakerService caretakerService;

    public AuthController(CaretakerService caretakerService) {
        this.caretakerService = caretakerService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCaretaker(@Valid @RequestBody Caretaker caretakerReq){

    }
}
