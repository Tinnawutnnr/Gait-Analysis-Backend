package com.gaitanalyze.gaitanalysisapp.patient.impl;

import com.gaitanalyze.gaitanalysisapp.dto.PatientListDTO;
import com.gaitanalyze.gaitanalysisapp.caretaker.Caretaker;
import com.gaitanalyze.gaitanalysisapp.caretaker.CaretakerRepository;
import com.gaitanalyze.gaitanalysisapp.exception.ResourceNotFoundException;
import com.gaitanalyze.gaitanalysisapp.patient.Patient;
import com.gaitanalyze.gaitanalysisapp.patient.PatientRepository;
import com.gaitanalyze.gaitanalysisapp.patient.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    PatientRepository patientRepository;
    CaretakerRepository caretakerRepository;

    public PatientServiceImpl(PatientRepository patientRepository, CaretakerRepository caretakerRepository) {
        this.patientRepository = patientRepository;
        this.caretakerRepository = caretakerRepository;
    }

    @Override
    public List<PatientListDTO> getPatients(Long caretakerId) {
        List<Patient> patients = patientRepository.findByCaretakerId(caretakerId);
        return patients.stream().map(PatientListDTO::new).collect(Collectors.toList());
    }

    @Override
    public void createPatient(Patient patientReq, Long caretakerId) {
        if(patientReq == null || caretakerId == null){
            throw new ResourceNotFoundException("Patient request and caretaker ID must not be null");
        }
        
        Caretaker caretaker = caretakerRepository.findById(caretakerId)
                .orElseThrow(() -> new ResourceNotFoundException("Caretaker with id " + caretakerId + " not found"));
        
        patientReq.setCaretaker(caretaker);
        patientRepository.save(patientReq);
    }

    @Override
    public Patient getPatientById(Long caretakerId, Long patientId) {
        return patientRepository.findByCaretakerIdAndId(caretakerId, patientId).
                orElseThrow(()-> new ResourceNotFoundException("Patient not found."));
    }

    @Override
    public void updatePatient(Long caretakerId, Long patientId, Patient patientReq) {
        Patient patientFound = patientRepository.findByCaretakerIdAndId(caretakerId, patientId).
                orElseThrow(()-> new ResourceNotFoundException("Patient not found."));//if use .orElseThrow i can use Patient return type

        patientFound.setFullname(patientReq.getFullname());
        patientFound.setAge(patientReq.getAge());
        patientFound.setHeight(patientReq.getHeight());
        patientFound.setWeight(patientReq.getWeight());
        patientRepository.save(patientFound);
    }

    @Override
    public void deletePatient(Long caretakerId, Long patientId) {
        Patient patientFound = patientRepository.findByCaretakerIdAndId(caretakerId, patientId).
                orElseThrow(()-> new ResourceNotFoundException("Patient not found."));
        patientRepository.delete(patientFound);
    }
}
