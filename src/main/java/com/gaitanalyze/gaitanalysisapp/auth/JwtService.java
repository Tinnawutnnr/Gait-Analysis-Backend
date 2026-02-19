package com.gaitanalyze.gaitanalysisapp.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("{application.security.jwt.secret-key}")
    private String secretKey;

    @Value("{application.security.jwt.expiration=900000}")
    private Long jwtExpiration;

    public String generateToken(String email){
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractEmail(String token){
        Claims claims = getAllClaims(token);
        return claims.getSubject();
    }

    public boolean isTokenExpired(String token){
        Claims claims = getAllClaims(token);
        Date expirationDate = claims.getExpiration();
        return expirationDate.before(new Date());
    }

    public boolean isTokenValid(String token, String userEmail){
        String emailInsideToken = extractEmail(token);
        return (emailInsideToken.equals(userEmail)) && !isTokenExpired(token);
    }
}
