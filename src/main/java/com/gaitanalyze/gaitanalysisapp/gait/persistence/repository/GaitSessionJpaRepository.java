package com.gaitanalyze.gaitanalysisapp.gait.persistence.repository;

import com.gaitanalyze.gaitanalysisapp.gait.persistence.entity.GaitSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GaitSessionJpaRepository extends JpaRepository<GaitSessionEntity, Long> {
    List<GaitSessionEntity> findByPatientId(Long patientId);
}