package com.codexhub.common.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    public static String createToken(String subject, String role, String secret, long ttlSeconds) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(subject)
                .addClaims(Map.of("role", role))
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(ttlSeconds)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
