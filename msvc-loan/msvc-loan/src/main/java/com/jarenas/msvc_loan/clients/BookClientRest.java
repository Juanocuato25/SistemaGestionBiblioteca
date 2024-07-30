package com.jarenas.msvc_loan.clients;

import com.jarenas.msvc_loan.model.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.print.Book;

@FeignClient(name = "msvc.book", url = "localhost:8002/books")
public interface BookClientRest {

    @GetMapping("/{id}")
    BookDTO bookById(@PathVariable Long id);


}
