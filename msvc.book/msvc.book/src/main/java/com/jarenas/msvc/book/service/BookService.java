package com.jarenas.msvc.book.service;

import com.jarenas.msvc.book.dto.BookCreationDTO;
import com.jarenas.msvc.book.dto.BookDTO;

import java.util.List;

public interface BookService {

    List<BookDTO> getAllBooks();

    BookDTO createBook(BookCreationDTO bookCreationDTO);

    BookDTO findBookById(Long id);

    BookDTO updateBook(BookCreationDTO bookCreationDTO, Long id);

    void deleteBook(Long id);

    boolean existByIsbn(String isbn);

}
