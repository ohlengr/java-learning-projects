package com.ohlengr.restapi.service;

import com.ohlengr.restapi.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    public List<Book> getAllBooks(){
        List<Book> books = new ArrayList<>();
        books.add(new Book(1,"Clean Code","Robert C. Martin"));
        books.add(new Book(2,"Effective Java","Joshua Bloch"));
        return books;
    }
}
