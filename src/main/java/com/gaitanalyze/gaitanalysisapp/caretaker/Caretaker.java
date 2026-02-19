package com.gaitanalyze.gaitanalysisapp.caretaker;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Caretaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Fullname is required.")
    @Size(min = 3, max = 50, message = "Fullname must be between 3 and 50 characters.")
    private String fullname;
    @NotBlank(message = "Email is required.")
    @Email(message = "Email format not valid.")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "Password is required.")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    public Caretaker() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
