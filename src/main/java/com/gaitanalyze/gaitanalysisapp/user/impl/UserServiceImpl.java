package com.gaitanalyze.gaitanalysisapp.user.impl;

import com.gaitanalyze.gaitanalysisapp.exception.ResourceNotFoundException;
import com.gaitanalyze.gaitanalysisapp.patientInfo.PatientInfo;
import com.gaitanalyze.gaitanalysisapp.patientInfo.PatientInfoRepo;
import com.gaitanalyze.gaitanalysisapp.user.Role;
import com.gaitanalyze.gaitanalysisapp.user.User;
import com.gaitanalyze.gaitanalysisapp.user.UserRepository;
import com.gaitanalyze.gaitanalysisapp.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final PatientInfoRepo patientInfoRepo;
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, PatientInfoRepo patientInfoRepo) {
        this.userRepository = userRepository;
        this.patientInfoRepo = patientInfoRepo;
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

    @Override
    @Transactional
    public void deletePatient(String username, Long caretakerId) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("No such username found."));

        boolean isAuthorized = userRepository.isPatientAssignedToCaretaker(user.getId(), caretakerId);

        if (!isAuthorized) {
            throw new IllegalArgumentException("Access Denied.");
        }

        int rowDeleted = patientInfoRepo.isUserInfoDeleted(user.getId());

        if(rowDeleted <= 0){
            throw new ResourceNotFoundException("Patient not found.");
        }

        user.setCaretaker_id(null);
        userRepository.save(user);
    }
}
