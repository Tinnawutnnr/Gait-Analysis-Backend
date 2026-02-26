package com.gaitanalyze.gaitanalysisapp.dto;

import com.gaitanalyze.gaitanalysisapp.user.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

public class PatientInfoRequest {
    @NotNull(message = "Age is required.")
    private Long age;
    @NotNull(message = "Height is required.")
    private double height;
    @NotNull(message = "Weight is required.")
    private double  weight;
    @NotNull(message = "Patient Id is required.")
    private Long userId;

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
