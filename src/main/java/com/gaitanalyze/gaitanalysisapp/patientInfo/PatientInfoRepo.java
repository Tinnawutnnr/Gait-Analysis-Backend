package com.gaitanalyze.gaitanalysisapp.patientInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PatientInfoRepo extends JpaRepository<PatientInfo, Long> {
    @Modifying
    @Query("DELETE FROM PatientInfo p WHERE p.user.id = :patientId")
    int isUserInfoDeleted(@Param("patientId") Long patientId);

    Optional<PatientInfo> findPatientInfoByUser_Id(Long userId);
}
