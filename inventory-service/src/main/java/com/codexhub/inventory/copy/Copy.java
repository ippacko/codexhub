package com.codexhub.inventory.copy;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name="copies")
public class Copy {
    @Id
    private UUID id;
    @Column(nullable=false)
    private UUID bookId;
    @Column(nullable=false,length=20)
    private String status;

    @PrePersist
    public void prePersist(){ if (id == null) id = UUID.randomUUID(); }

    public UUID getId(){ return id; }
    public void setId(UUID id){ this.id = id; }
    public UUID getBookId(){ return bookId; }
    public void setBookId(UUID bookId){ this.bookId = bookId; }
    public String getStatus(){ return status; }
    public void setStatus(String status){ this.status = status; }
}
