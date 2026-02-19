package com.gaitanalyze.gaitanalysisapp.auth;

import com.gaitanalyze.gaitanalysisapp.caretaker.Caretaker;
import org.antlr.v4.runtime.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByCaretaker(Caretaker caretaker);
}
