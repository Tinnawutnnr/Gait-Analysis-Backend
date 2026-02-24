package com.gaitanalyze.gaitanalysisapp.gait.ports;

import com.gaitanalyze.gaitanalysisapp.gait.domain.GaitSession;

import java.util.List;
import java.util.Optional;

public interface GaitSessionRepositoryPort {
    List<GaitSession> findByPatientId(Long patientId);

    Optional<GaitSession> findById(Long id);

    GaitSession save(GaitSession session);
}