package com.ohlengr.restapi.controller;

import com.ohlengr.restapi.dto.CreateBookRequest;
import com.ohlengr.restapi.model.Book;
import com.ohlengr.restapi.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody CreateBookRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody CreateBookRequest request){
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
