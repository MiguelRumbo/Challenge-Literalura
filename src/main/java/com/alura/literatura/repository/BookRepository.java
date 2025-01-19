package com.alura.literatura.repository;

import com.alura.literatura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitle(String title);
}