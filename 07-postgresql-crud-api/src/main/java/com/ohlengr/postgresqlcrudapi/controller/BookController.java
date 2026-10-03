package com.ohlengr.postgresqlcrudapi.controller;

import com.ohlengr.postgresqlcrudapi.dto.CreateBookRequest;
import com.ohlengr.postgresqlcrudapi.dto.UpdateBookRequest;
import com.ohlengr.postgresqlcrudapi.model.Book;
import com.ohlengr.postgresqlcrudapi.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody @Valid CreateBookRequest createBookRequest, UriComponentsBuilder ubc) {
        Book createdBook = bookService.createBook(createBookRequest);
        URI location = ubc.path("/api/books/{createdId}").buildAndExpand(createdBook.getId()).toUri();
        return ResponseEntity.created(location).body(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody @Valid UpdateBookRequest updateBookRequest) {
        return ResponseEntity.ok(bookService.updateBookById(id, updateBookRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
