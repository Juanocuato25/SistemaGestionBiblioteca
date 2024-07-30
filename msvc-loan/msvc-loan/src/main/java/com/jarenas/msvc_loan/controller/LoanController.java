package com.jarenas.msvc_loan.controller;

import com.jarenas.msvc_loan.dto.LoanCreationDTO;
import com.jarenas.msvc_loan.dto.LoanDTO;
import com.jarenas.msvc_loan.exceptions.LoanExceptions;
import com.jarenas.msvc_loan.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanDTO>> getAllLoans(){
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> loanDetail(@PathVariable Long id){
        try{
            LoanDTO loanDTO = loanService.findLoanById(id);
            return ResponseEntity.ok(loanDTO);
        }catch (LoanExceptions l){
            return ResponseEntity.status(l.getHttpStatus()).body(l.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createLoan(@Valid @RequestBody LoanCreationDTO loanCreationDTO, BindingResult result){
        if (result.hasErrors()){
            return  validar(result);
        }
        try {
            LoanDTO loanDTO = loanService.createLoan(loanCreationDTO);
            return ResponseEntity.ok(loanDTO);
        }catch (LoanExceptions l){
            return ResponseEntity.status(l.getHttpStatus()).body(l.getMessage());
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
