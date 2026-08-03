package com.omkar.inventory.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration:86400000}")
    private long expiration;

    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username) {

        return generateToken(new HashMap<>(), username);

    }

    public String generateToken(
            Map<String, Object> claims,
            String username) {

        return Jwts.builder()

                .claims(claims)

                .subject(username)

                .issuedAt(new Date())

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + expiration))

                .signWith(key, SignatureAlgorithm.HS256)

                .compact();
    }

    public String extractUsername(String token) {

        return extractAllClaims(token).getSubject();

    }

    public Date extractExpiration(String token) {

        return extractAllClaims(token).getExpiration();

    }

    public boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());

    }

    public boolean isTokenValid(
            String token,
            String username) {

        return username.equals(extractUsername(token))
                && !isTokenExpired(token);

    }

    /*
     * Used by Gateway
     */

    public boolean isTokenValid(String token) {

        try {

            return !isTokenExpired(token);

        } catch (Exception ex) {

            return false;

        }

    }

    public Claims extractAllClaims(String token) {

        return Jwts.parser()

                .verifyWith(key)

                .build()

                .parseSignedClaims(token)

                .getPayload();

    }

    public boolean validateToken(String token) {

        try {

            extractAllClaims(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }

}