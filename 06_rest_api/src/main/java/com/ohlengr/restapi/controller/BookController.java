package com.ohlengr.restapi.controller;

import com.ohlengr.restapi.dto.CreateBookRequest;
import com.ohlengr.restapi.model.Book;
import com.ohlengr.restapi.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/all-books")
    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id){
        Book book = bookService.getBookById(id);
        if(book == null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok().body(book);
        }
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody CreateBookRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(request));
    }
}
