package com.gaitanalyze.gaitanalysisapp.user.impl;

import com.gaitanalyze.gaitanalysisapp.exception.ResourceNotFoundException;
import com.gaitanalyze.gaitanalysisapp.user.Role;
import com.gaitanalyze.gaitanalysisapp.user.User;
import com.gaitanalyze.gaitanalysisapp.user.UserRepository;
import com.gaitanalyze.gaitanalysisapp.user.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addPatient(String username, Long caretakerId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("No such username found."));

        Optional<User> caretaker = userRepository.findById(caretakerId);
        if(caretaker.isPresent() && caretaker.get().getRole() != Role.PATIENT){
            user.setCaretaker_id(caretaker.get().getId());
            userRepository.save(user);
        }
        else{
            throw new IllegalArgumentException("User invalid or not permitted to add patient.");
        }
    }
}
