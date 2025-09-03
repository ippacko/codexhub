package com.codexhub.auth.user;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    private UUID id;
    @Column(nullable = false, unique = true, length = 200)
    private String email;
    @Column(name="password_hash", nullable=false, length=200)
    private String passwordHash;
    @Column(nullable=false, length=50)
    private String role;

    @PrePersist
    public void prePersist() { if (id == null) id = UUID.randomUUID(); }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
