package com.gaitanalyze.gaitanalysisapp.gait.persistence.adapter;

import com.gaitanalyze.gaitanalysisapp.gait.domain.GaitReport;
import com.gaitanalyze.gaitanalysisapp.gait.persistence.entity.GaitReportEntity;
import com.gaitanalyze.gaitanalysisapp.gait.persistence.repository.GaitReportJpaRepository;
import com.gaitanalyze.gaitanalysisapp.gait.ports.GaitReportRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GaitReportRepositoryAdapter implements GaitReportRepositoryPort {

    private final GaitReportJpaRepository jpaRepo;

    public GaitReportRepositoryAdapter(GaitReportJpaRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public List<GaitReport> findBySessionId(Long sessionId) {
        return jpaRepo.findBySessionIdOrderByCreatedAtDesc(sessionId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public GaitReport save(GaitReport report) {
        GaitReportEntity entity = toEntity(report);
        return toDomain(jpaRepo.save(entity));
    }

    private GaitReport toDomain(GaitReportEntity e) {
        return new GaitReport(
                e.getId(),
                e.getSessionId(),
                e.getReportType(),
                e.getGaitHealth(),
                e.getIsReliable(),
                e.getAnomalyScore(),
                e.getPayload(),
                e.getCreatedAt()
        );
    }

    private GaitReportEntity toEntity(GaitReport d) {
        GaitReportEntity e = new GaitReportEntity();
            e.setId(d.getId());
            e.setSessionId(d.getSessionId());
            e.setReportType(d.getReportType());
            e.setGaitHealth(d.getGaitHealth());
            e.setIsReliable(d.getIsReliable());
            e.setAnomalyScore(d.getAnomalyScore());
            e.setPayload(d.getPayload());
            e.setCreatedAt(d.getCreatedAt());
            return e;
    }
}