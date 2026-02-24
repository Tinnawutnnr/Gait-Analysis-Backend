package com.gaitanalyze.gaitanalysisapp.gait.persistence.repository;

import com.gaitanalyze.gaitanalysisapp.gait.persistence.entity.GaitReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GaitReportJpaRepository extends JpaRepository<GaitReportEntity, Long> {
    List<GaitReportEntity> findBySessionIdOrderByCreatedAtDesc(Long SessionId);
}