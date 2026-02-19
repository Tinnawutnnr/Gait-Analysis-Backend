package com.gaitanalyze.gaitanalysisapp.patient;

import com.gaitanalyze.gaitanalysisapp.dto.PatientListDTO;

import java.util.List;

public interface PatientService {
    List<PatientListDTO> getPatients(Long caretakerId);
    void createPatient(Patient patientReq, Long caretakerId);
    Patient getPatientById(Long caretakerId, Long patientId);
    void updatePatient(Long caretakerId, Long patientId, Patient patientReq);
    void deletePatient(Long caretakerId, Long patientId);
}
