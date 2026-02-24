package com.gaitanalyze.gaitanalysisapp.gait.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

@Entity
@Table(name = "gait_reports")
public class GaitReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false) // DB: NOT NULL
    private Long id;

    @NotNull
    @Column(name = "session_id", nullable = false) // DB: NOT NULL
    private Long sessionId;

    @NotNull
    @Column(name = "report_type", nullable = false) // DB: NOT NULL
    private String reportType; // status/analysis/info

    @Column(name = "gait_health") // DB: nullable
    private String gaitHealth; // NORMAL/ANOMALY_DETECTED

    @Column(name = "is_reliable") // DB: nullable
    private Boolean isReliable;

    @Column(name = "anomaly_score") // DB: nullable
    private Double anomalyScore;

    // DB: NOT NULL
    @NotNull
    @Column(name = "payload", nullable = false, columnDefinition = "jsonb")
    private String payload;

    // DB: NOT NULL
    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    // ===== getters/setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSessionId() { return sessionId; }
    public void setSessionId(Long sessionId) { this.sessionId = sessionId; }

    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }

    public String getGaitHealth() { return gaitHealth; }
    public void setGaitHealth(String gaitHealth) { this.gaitHealth = gaitHealth; }

    public Boolean getIsReliable() { return isReliable; }
    public void setIsReliable(Boolean isReliable) { this.isReliable = isReliable; }

    public Double getAnomalyScore() { return anomalyScore; }
    public void setAnomalyScore(Double anomalyScore) { this.anomalyScore = anomalyScore; }

    public String getPayload() { return payload; }
    public void setPayload(String payload) { this.payload = payload; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}