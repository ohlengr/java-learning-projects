package com.ohlengr.postgresqlcrudapi;

import com.ohlengr.postgresqlcrudapi.model.Book;
import com.ohlengr.postgresqlcrudapi.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepository) {
        return args -> {
            Book book = new Book("Effective Java", "Joshua Bloch");

            Book savedBook = bookRepository.save(book);

            System.out.println(savedBook);
        };
    }
}
