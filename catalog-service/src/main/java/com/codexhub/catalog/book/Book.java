package com.codexhub.catalog.book;

import com.codexhub.catalog.author.Author;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name="books")
public class Book {
    @Id
    private UUID id;
    @Column(nullable=false,length=200)
    private String title;
    @ManyToOne(optional=false)
    @JoinColumn(name="author_id")
    private Author author;
    private Integer publishedYear;

    @PrePersist
    public void prePersist(){ if (id == null) id = UUID.randomUUID(); }

    public UUID getId(){ return id; }
    public void setId(UUID id){ this.id = id; }
    public String getTitle(){ return title; }
    public void setTitle(String title){ this.title = title; }
    public Author getAuthor(){ return author; }
    public void setAuthor(Author author){ this.author = author; }
    public Integer getPublishedYear(){ return publishedYear; }
    public void setPublishedYear(Integer y){ this.publishedYear = y; }
}
