package com.gaitanalyze.gaitanalysisapp.gait.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Entity
@Table(name = "gait_sessions")
public class GaitSessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotNull
    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "device_id") // nullable ได้
    private String deviceId;

    @NotNull
    @Column(name = "sampling_hz", nullable = false)
    private Integer samplingHz;

    @NotNull
    @Column(nullable = false)
    private String status;

    @NotNull
    @Column(name = "started_at", nullable = false)
    private OffsetDateTime startedAt;

    @Column(name = "closed_at") // nullable ได้
    private OffsetDateTime closedAt;

    // ===== getters/setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public Integer getSamplingHz() { return samplingHz; }
    public void setSamplingHz(Integer samplingHz) { this.samplingHz = samplingHz; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public OffsetDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(OffsetDateTime startedAt) { this.startedAt = startedAt; }

    public OffsetDateTime getClosedAt() { return closedAt; }
    public void setClosedAt(OffsetDateTime closedAt) { this.closedAt = closedAt; }
}