package com.jarenas.msvc.book.controller;


import com.jarenas.msvc.book.dto.BookCreationDTO;
import com.jarenas.msvc.book.dto.BookDTO;
import com.jarenas.msvc.book.exceptions.BookExceptions;
import com.jarenas.msvc.book.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;


    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> bookById(@PathVariable Long id){
        try {
            BookDTO userDTO = bookService.findBookById(id);
            return ResponseEntity.ok(userDTO);
        } catch (BookExceptions e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createBook(@Valid @RequestBody BookCreationDTO bookCreationDTO, BindingResult result){
        if (result.hasErrors()){
            return validar(result);
        }
        if (!bookCreationDTO.getIsbn().isBlank() && bookService.existByIsbn(bookCreationDTO.getIsbn())){
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("Error", "Is al ready user with that email"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(bookCreationDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@Valid @RequestBody BookCreationDTO bookCreationDTO, BindingResult result, @PathVariable Long id){
        if (result.hasErrors()){
            return validar(result);
        }
        try{
            BookDTO bookDTO = bookService.updateBook(bookCreationDTO, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(bookDTO);
        }catch (BookExceptions e){
            return ResponseEntity.status(e.getHttpStatus()).body(null);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        try{
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        }catch (BookExceptions e){
            return ResponseEntity.status(e.getHttpStatus()).body(null);
        }
    }


    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errores.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
