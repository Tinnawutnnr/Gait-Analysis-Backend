package com.gaitanalyze.gaitanalysisapp.gait.application;

import com.gaitanalyze.gaitanalysisapp.gait.domain.GaitSession;
import com.gaitanalyze.gaitanalysisapp.gait.ports.GaitSessionRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPatientSessionsUseCase {

    private final GaitSessionRepositoryPort repository;

    public GetPatientSessionsUseCase(GaitSessionRepositoryPort repository) {
        this.repository = repository;
    }

    public List<GaitSession> execute(Long patientId) {
        return repository.findByPatientId(patientId);
    }
}