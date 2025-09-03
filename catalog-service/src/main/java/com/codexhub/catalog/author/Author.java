package com.codexhub.catalog.author;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name="authors")
public class Author {
    @Id
    private UUID id;
    @Column(nullable=false,length=200)
    private String name;

    @PrePersist
    public void prePersist(){ if (id == null) id = UUID.randomUUID(); }

    public UUID getId(){ return id; }
    public void setId(UUID id){ this.id = id; }
    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }
}
