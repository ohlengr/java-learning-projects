package com.ohlengr.postgresqlcrudapi.repository;

import com.ohlengr.postgresqlcrudapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
