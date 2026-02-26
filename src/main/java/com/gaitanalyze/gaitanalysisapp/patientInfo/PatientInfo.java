package com.gaitanalyze.gaitanalysisapp.patientInfo;

import com.gaitanalyze.gaitanalysisapp.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class PatientInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Age is required.")
    private Long age;
    @NotNull(message = "Height is required.")
    private double height;
    @NotNull(message = "Weight is required.")
    private double  weight;

    @NotNull(message = "User cannot be null")
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true) // <-- ADD UNIQUE!
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
