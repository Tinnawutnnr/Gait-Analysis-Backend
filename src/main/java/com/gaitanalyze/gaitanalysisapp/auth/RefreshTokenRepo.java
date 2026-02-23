package com.gaitanalyze.gaitanalysisapp.auth;

import com.gaitanalyze.gaitanalysisapp.caretaker.Caretaker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByCaretaker(Caretaker caretaker);
    Optional<RefreshToken> findByToken(String token);
}
