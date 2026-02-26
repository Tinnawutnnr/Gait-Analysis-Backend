package com.gaitanalyze.gaitanalysisapp.patientInfo.impl;

import com.gaitanalyze.gaitanalysisapp.dto.PatientInfoRequest;
import com.gaitanalyze.gaitanalysisapp.exception.ResourceNotFoundException;
import com.gaitanalyze.gaitanalysisapp.patientInfo.PatientInfo;
import com.gaitanalyze.gaitanalysisapp.patientInfo.PatientInfoRepo;
import com.gaitanalyze.gaitanalysisapp.patientInfo.PatientInfoService;
import com.gaitanalyze.gaitanalysisapp.user.Role;
import com.gaitanalyze.gaitanalysisapp.user.User;
import com.gaitanalyze.gaitanalysisapp.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientInfoServiceImpl implements PatientInfoService {

    private UserRepository userRepository;
    private PatientInfoRepo patientInfoRepo;

    public PatientInfoServiceImpl(UserRepository userRepository, PatientInfoRepo patientInfoRepo) {
        this.userRepository = userRepository;
        this.patientInfoRepo = patientInfoRepo;
    }

    @Override
    public void createInfo(PatientInfoRequest request, Long caretakerId) {

        boolean isAuthorized = userRepository.isPatientAssignedToCaretaker(request.getUserId(), caretakerId);

        if (!isAuthorized) {
            throw new IllegalArgumentException("Access Denied.");
        }

        User patient = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        PatientInfo newInfo = new PatientInfo();
        newInfo.setUser(patient);
        newInfo.setAge(request.getAge());
        newInfo.setHeight(request.getHeight());
        newInfo.setWeight(request.getWeight());

        patientInfoRepo.save(newInfo);
    }
}
