package com.gaitanalyze.gaitanalysisapp.auth;

import com.gaitanalyze.gaitanalysisapp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUser(User user);
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    @Query(value = "DELETE FROM refresh_token WHERE token = :token", nativeQuery = true)
    int deleteByTokenDirectly(@Param("token") String token);
}
