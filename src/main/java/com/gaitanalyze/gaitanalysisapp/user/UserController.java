package com.gaitanalyze.gaitanalysisapp.user;

import com.gaitanalyze.gaitanalysisapp.dto.UsernameRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/{caretakerId}")
    public ResponseEntity<String> addPatient(@Valid @RequestBody UsernameRequest username, @PathVariable Long caretakerId){
        userService.addPatient(username.getUsername(), caretakerId);
        return new ResponseEntity<>("Patient added.", HttpStatus.OK);
    }

    @DeleteMapping("/{caretakerId}")
    public ResponseEntity<String> deletePatient(@Valid @RequestBody UsernameRequest username, @PathVariable Long caretakerId){
        userService.deletePatient(username.getUsername(), caretakerId);
        return new ResponseEntity<>("Patient deleted.", HttpStatus.OK);
    }
}
