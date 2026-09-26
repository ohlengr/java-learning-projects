package com.ohlengr.restapi.controller;

import com.ohlengr.restapi.model.Book;
import com.ohlengr.restapi.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
    
    @GetMapping("/message")
    public Message message(){
        return new Message("Hello World", "Ohlengr");
    }

//    @GetMapping("/books")
//    public List<Book> books(){
//        List<Book> books = new ArrayList<>();
//        books.add(new Book(1,"Clean Code C++","Robert C. Martin"));
//        books.add(new Book(2,"Effective Java","Joshua Bloch"));
//        return books;
//    }
}