package com.gaitanalyze.gaitanalysisapp.user;

import com.gaitanalyze.gaitanalysisapp.dto.UsernameRequest;
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
    public ResponseEntity<String> addPatient(@RequestBody UsernameRequest username, @PathVariable Long caretakerId){
        userService.addPatient(username.getUsername(), caretakerId);
        return new ResponseEntity<>("Patient added.", HttpStatus.OK);
    }
}
