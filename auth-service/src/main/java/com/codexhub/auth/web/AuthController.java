package com.codexhub.auth.web;

import com.codexhub.auth.user.User;
import com.codexhub.auth.user.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

    private final UserRepository users;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Value("${jwt.secret}")
    private String secret;

    public AuthController(UserRepository users) {
        this.users = users;
    }

    record RegisterRequest(@Email String email, @NotBlank String password, @NotBlank String role) {}
    record LoginRequest(@Email String email, @NotBlank String password) {}
    record TokenResponse(String token) {}

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse register(@RequestBody RegisterRequest req) {
        if (users.findByEmail(req.email()).isPresent()) {
            throw new RuntimeException("email already registered");
        }
        User u = new User();
        u.setEmail(req.email());
        u.setPasswordHash(encoder.encode(req.password()));
        u.setRole(req.role());
        users.save(u);
        return new TokenResponse(createToken(u.getEmail(), u.getRole()));
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest req) {
        User u = users.findByEmail(req.email()).orElseThrow(() -> new RuntimeException("invalid credentials"));
        if (!encoder.matches(req.password(), u.getPasswordHash())) {
            throw new RuntimeException("invalid credentials");
        }
        return new TokenResponse(createToken(u.getEmail(), u.getRole()));
    }

    private String createToken(String subject, String role) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(subject)
                .addClaims(Map.of("role", role))
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(3600)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
