package com.codexhub.inventory.reservation;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="reservations")
public class Reservation {
    @Id
    private UUID id;
    @Column(nullable=false)
    private UUID copyId;
    @Column(nullable=false,length=200)
    private String userEmail;
    @Column(nullable=false)
    private Instant reservedAt;

    @PrePersist
    public void prePersist(){
        if (id == null) id = UUID.randomUUID();
        if (reservedAt == null) reservedAt = Instant.now();
    }

    public UUID getId(){ return id; }
    public void setId(UUID id){ this.id = id; }
    public UUID getCopyId(){ return copyId; }
    public void setCopyId(UUID copyId){ this.copyId = copyId; }
    public String getUserEmail(){ return userEmail; }
    public void setUserEmail(String userEmail){ this.userEmail = userEmail; }
    public Instant getReservedAt(){ return reservedAt; }
    public void setReservedAt(Instant reservedAt){ this.reservedAt = reservedAt; }
}
