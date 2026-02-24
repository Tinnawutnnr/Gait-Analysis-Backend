package com.gaitanalyze.gaitanalysisapp.gait.ports;

import com.gaitanalyze.gaitanalysisapp.gait.domain.GaitReport;

import java.util.List;

public interface GaitReportRepositoryPort {
    List<GaitReport> findBySessionId(Long sessionId);

    GaitReport save(GaitReport report);
}