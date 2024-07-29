package com.jarenas.msvc.book.repository;

import com.jarenas.msvc.book.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
boolean existsByIsbn(String isbn);
}
