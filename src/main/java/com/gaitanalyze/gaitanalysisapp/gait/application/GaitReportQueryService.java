package com.gaitanalyze.gaitanalysisapp.gait.application;

import com.gaitanalyze.gaitanalysisapp.gait.domain.GaitReport;
import com.gaitanalyze.gaitanalysisapp.gait.ports.GaitReportRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GaitReportQueryService {

    private final GaitReportRepositoryPort reportRepo;

    public GaitReportQueryService(GaitReportRepositoryPort reportRepo) {
        this.reportRepo = reportRepo;
    }

    public List<GaitReport> getReportsBySessionId(Long sessionId) {
        return reportRepo.findBySessionId(sessionId);
    }
}