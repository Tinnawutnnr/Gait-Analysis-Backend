package com.gaitanalyze.gaitanalysisapp.patient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByCaretakerId(Long caretakerId);
    Optional<Patient> findByCaretakerIdAndId(Long caretakerId, Long patientId);
}
