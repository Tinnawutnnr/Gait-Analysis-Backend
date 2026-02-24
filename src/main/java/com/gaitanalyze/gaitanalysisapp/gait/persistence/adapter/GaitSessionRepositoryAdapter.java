package com.gaitanalyze.gaitanalysisapp.gait.persistence.adapter;

import com.gaitanalyze.gaitanalysisapp.gait.domain.GaitSession;
import com.gaitanalyze.gaitanalysisapp.gait.persistence.entity.GaitSessionEntity;
import com.gaitanalyze.gaitanalysisapp.gait.persistence.repository.GaitSessionJpaRepository;
import com.gaitanalyze.gaitanalysisapp.gait.ports.GaitSessionRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GaitSessionRepositoryAdapter
        implements GaitSessionRepositoryPort {

    private final GaitSessionJpaRepository jpaRepo;

    public GaitSessionRepositoryAdapter(GaitSessionJpaRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public List<GaitSession> findByPatientId(Long patientId) {
        return jpaRepo.findByPatientId(patientId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<GaitSession> findById(Long id) {
        return jpaRepo.findById(id).map(this::toDomain);
    }

    @Override
    public GaitSession save(GaitSession session) {
        GaitSessionEntity entity = toEntity(session);
        return toDomain(jpaRepo.save(entity));
    }

    private GaitSession toDomain(GaitSessionEntity e) {
        return  new GaitSession(
                e.getId(),
                e.getPatientId(),
                e.getDeviceId(),
                e.getSamplingHz(),
                e.getStatus(),
                e.getStartedAt(),
                e.getClosedAt()
        );
    }

    private GaitSessionEntity toEntity(GaitSession d) {
        GaitSessionEntity e = new GaitSessionEntity();

        e.setId(d.getId());
        e.setPatientId(d.getPatientId());
        e.setDeviceId(d.getDeviceId());
        e.setSamplingHz(d.getSamplingHz());
        e.setStatus(d.getStatus());
        e.setStartedAt(d.getStartedAt());
        e.setClosedAt(d.getClosedAt());

        return e;
    }
}