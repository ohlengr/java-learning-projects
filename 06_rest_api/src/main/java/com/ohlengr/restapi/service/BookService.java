package com.ohlengr.restapi.service;

import com.ohlengr.restapi.dto.CreateBookRequest;
import com.ohlengr.restapi.exception.BookNotFoundException;
import com.ohlengr.restapi.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();
    private long nextId = 3;

    public BookService(){
        books.add(new Book(1,"Clean Code","Robert C. Martin"));
        books.add(new Book(2,"Effective Java","Joshua Bloch"));
    }

    public List<Book> getAllBooks() {
        return Collections.unmodifiableList(books);
    }

    public Book getBookById(long id){
        for(Book book: books){
            if(book.getId()==id) {
                return book;
            }
        }
        throw new BookNotFoundException("Book with id " + id + " not found");
    }

    public Book createBook(CreateBookRequest request){
        long id = nextId;
        String title = request.getTitle();
        String author = request.getAuthor();
        Book book = new Book(id,title,author);
        books.add(book);
        nextId++;
        return book;
    }

    public Book updateBook(long id, CreateBookRequest request){
        for (Book book : books) {
            if (book.getId() == id) {
                book.setTitle(request.getTitle());
                book.setAuthor(request.getAuthor());

                return book;
            }
        }
        throw new BookNotFoundException("Book with id " + id + " not found");
    }

    public void deleteBook(long id){
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()){
            if(iterator.next().getId()==id){
                iterator.remove();
                return;
            }
        }
        throw new BookNotFoundException("Book with id " + id + " not found");
    }
}
