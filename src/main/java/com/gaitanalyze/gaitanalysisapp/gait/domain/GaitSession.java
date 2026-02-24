package com.gaitanalyze.gaitanalysisapp.gait.domain;

import java.time.OffsetDateTime;

public class GaitSession {

    private Long id;
    private Long patientId;
    private String deviceId;
    private Integer samplingHz;
    private String status;
    private OffsetDateTime startedAt;
    private OffsetDateTime closedAt;

    public GaitSession() {}

    public GaitSession(Long id, Long patientId, String deviceId,
                       Integer samplingHz, String status,
                       OffsetDateTime startedAt, OffsetDateTime closedAt) {
        this.id = id;
        this.patientId = patientId;
        this.deviceId = deviceId;
        this.samplingHz = samplingHz;
        this.status = status;
        this.startedAt = startedAt;
        this.closedAt = closedAt;
    }

    public Long getId() { return id; }
    public Long getPatientId() { return patientId; }
    public String getDeviceId() { return deviceId; }
    public Integer getSamplingHz() { return samplingHz; }
    public String getStatus() { return status; }
    public OffsetDateTime getStartedAt() { return startedAt; }
    public OffsetDateTime getClosedAt() { return closedAt; }

    public void setId(Long id) { this.id = id; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }
    public void setSamplingHz(Integer samplingHz) { this.samplingHz = samplingHz; }
    public void setStatus(String status) { this.status = status; }
    public void setStartedAt(OffsetDateTime startedAt) { this.startedAt = startedAt; }
    public void setClosedAt(OffsetDateTime closedAt) { this.closedAt = closedAt; }
}