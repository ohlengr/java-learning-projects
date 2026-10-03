package com.ohlengr.postgresqlcrudapi.service;

import com.ohlengr.postgresqlcrudapi.dto.CreateBookRequest;
import com.ohlengr.postgresqlcrudapi.dto.UpdateBookRequest;
import com.ohlengr.postgresqlcrudapi.exception.BookNotFoundException;
import com.ohlengr.postgresqlcrudapi.model.Book;
import com.ohlengr.postgresqlcrudapi.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book Not Found with id: " + id));
    }

    public Book createBook(CreateBookRequest createBookRequest) {
        Book book = new Book(createBookRequest.getTitle(), createBookRequest.getAuthor());
        return bookRepository.save(book);
    }

    public Book updateBookById(Long id, UpdateBookRequest updateBookRequest) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book Not Found with id: " + id));
        book.setTitle(updateBookRequest.getTitle());
        book.setAuthor(updateBookRequest.getAuthor());
        return bookRepository.save(book);
    }

    public void deleteBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book Not Found with id: " + id));
        bookRepository.delete(book);
    }
}
