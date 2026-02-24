package com.gaitanalyze.gaitanalysisapp.gait.domain;

import java.time.OffsetDateTime;

public class GaitReport {

    private Long id;
    private Long sessionId;
    private String reportType;
    private String gaitHealth;
    private Boolean isReliable;
    private Double anomalyScore;
    private String payload;
    private OffsetDateTime createdAt;

    public GaitReport() {}

    public GaitReport(Long id, Long sessionId, String reportType,
                      String gaitHealth, Boolean isReliable,
                      Double anomalyScore, String payload,
                      OffsetDateTime createdAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.reportType = reportType;
        this.gaitHealth = gaitHealth;
        this.isReliable = isReliable;
        this.anomalyScore = anomalyScore;
        this.payload = payload;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public Long getSessionId() { return sessionId; }
    public String getReportType() { return reportType; }
    public String getGaitHealth() { return gaitHealth; }
    public Boolean getIsReliable() { return isReliable; }
    public Double getAnomalyScore() { return anomalyScore; }
    public String getPayload() { return payload; }
    public OffsetDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setSessionId(Long sessionId) { this.sessionId = sessionId; }
    public void setReportType(String reportType) { this.reportType = reportType; }
    public void setGaitHealth(String gaitHealth) { this.gaitHealth = gaitHealth; }
    public void setIsReliable(Boolean isReliable) { this.isReliable = isReliable; }
    public void setAnomalyScore(Double anomalyScore) { this.anomalyScore = anomalyScore; }
    public void setPayload(String payload) { this.payload = payload; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}