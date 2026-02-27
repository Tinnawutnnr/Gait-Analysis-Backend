package com.gaitanalyze.gaitanalysisapp.patientInfo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientInfoRepo extends JpaRepository<PatientInfo, Long> {
    Optional<PatientInfo> findByUserId(Long userId);
}
