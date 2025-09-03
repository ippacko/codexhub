package com.codexhub.catalog.web;

import com.codexhub.catalog.author.Author;
import com.codexhub.catalog.author.AuthorRepository;
import com.codexhub.catalog.book.Book;
import com.codexhub.catalog.book.BookRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final AuthorRepository authors;
    private final BookRepository books;

    public CatalogController(AuthorRepository authors, BookRepository books) {
        this.authors = authors;
        this.books = books;
    }

    record AuthorReq(@NotBlank String name) {}
    record BookReq(@NotBlank String title, UUID authorId, Integer publishedYear) {}

    // Authors
    @PostMapping("/authors")
    public Author createAuthor(@RequestBody AuthorReq req){
        Author a = new Author();
        a.setName(req.name());
        return authors.save(a);
    }
    @GetMapping("/authors")
    public Page<Author> listAuthors(Pageable pageable){ return authors.findAll(pageable); }

    // Books
    @PostMapping("/books")
    public Book createBook(@RequestBody BookReq req){
        Book b = new Book();
        b.setTitle(req.title());
        b.setAuthor(authors.findById(req.authorId()).orElseThrow());
        b.setPublishedYear(req.publishedYear());
        return books.save(b);
    }

    @GetMapping("/books")
    public Page<Book> listBooks(@RequestParam(required=false) String q, Pageable pageable){
        if (q == null || q.isEmpty()) return books.findAll(pageable);
        return books.findByTitleContainingIgnoreCase(q, pageable);
    }
    @GetMapping("/books/{id}")
    public Book get(@PathVariable UUID id){ return books.findById(id).orElseThrow(); }
}
