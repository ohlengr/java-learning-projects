package com.ohlengr.restapi.service;

import com.ohlengr.restapi.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    List<Book> books = new ArrayList<>();
    public BookService(){
        books.add(new Book(1,"Clean Code","Robert C. Martin"));
        books.add(new Book(2,"Effective Java","Joshua Bloch"));
    }

    public List<Book> getAllBooks(){
        return books;
    }
    public Book getBookById(long id){
        for(Book book: books){
            if(book.getId()==id) {
                return book;
            }
        }
        return null;
    }
}
