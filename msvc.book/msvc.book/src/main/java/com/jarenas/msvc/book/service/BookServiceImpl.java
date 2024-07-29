package com.jarenas.msvc.book.service;

import com.jarenas.msvc.book.dto.BookCreationDTO;
import com.jarenas.msvc.book.dto.BookDTO;
import com.jarenas.msvc.book.exceptions.BookExceptions;
import com.jarenas.msvc.book.mapper.BookMapper;
import com.jarenas.msvc.book.model.entity.Book;
import com.jarenas.msvc.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepository;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(bookMapper::toBookDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookDTO createBook(BookCreationDTO bookCreationDTO) {
        Book book = BookMapper.INSTANCE.toBook(bookCreationDTO);
        return BookMapper.INSTANCE.toBookDTO(bookRepository.save(book));
    }

    @Override
    @Transactional(readOnly = true)
    public BookDTO findBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookExceptions("Book does not exist", HttpStatus.NOT_FOUND));
        return BookMapper.INSTANCE.toBookDTO(book);
    }

    @Override
    @Transactional
    public BookDTO updateBook(BookCreationDTO bookCreationDTO, Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()
                 -> new BookExceptions("Book does not exist", HttpStatus.NOT_FOUND));
        book.setTitle(bookCreationDTO.getTitle());
        book.setAuthor(bookCreationDTO.getAuthor());
        book.setIsbn(bookCreationDTO.getIsbn());
        book.setPublishedYear(bookCreationDTO.getPublishedYear());

        return bookMapper.toBookDTO(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new BookExceptions("Book does not exist",
                        HttpStatus.NOT_FOUND));
        bookRepository.deleteById(book.getId());

    }

    @Override
    public boolean existByIsbn(String isbn) {
        return bookRepository.existsByIsbn(isbn);
    }
}
