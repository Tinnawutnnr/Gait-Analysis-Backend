package com.gaitanalyze.gaitanalysisapp.user;

public interface UserService {
    void addPatient(String username, Long caretakerId);

    void deletePatient(String username, Long caretakerId);
}
