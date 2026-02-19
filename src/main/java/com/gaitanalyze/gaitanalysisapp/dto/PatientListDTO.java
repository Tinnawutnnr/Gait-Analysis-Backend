package com.gaitanalyze.gaitanalysisapp.dto;

import com.gaitanalyze.gaitanalysisapp.patient.Patient;

public class PatientListDTO {
    private Long id;
    private String fullname;
    private Long age;
    private Long height;
    private Double weight;
    private Long caretakerId;

    public PatientListDTO(Patient patient) {
        this.id = patient.getId();
        this.fullname = patient.getFullname();
        this.age = patient.getAge();
        this.height = patient.getHeight();
        this.weight = patient.getWeight();
        if(patient.getCaretaker() != null){
            this.caretakerId = patient.getCaretaker().getId();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Long getCaretakerId() {
        return caretakerId;
    }

    public void setCaretakerId(Long caretakerId) {
        this.caretakerId = caretakerId;
    }
}
