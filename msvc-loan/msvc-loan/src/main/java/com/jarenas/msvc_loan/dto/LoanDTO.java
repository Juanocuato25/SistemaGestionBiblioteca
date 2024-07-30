package com.jarenas.msvc_loan.dto;

import com.jarenas.msvc_loan.model.entity.LoanStatus;

import java.time.LocalDate;

public class LoanDTO {


    private Long id;
    private Long user_id;
    private Long book_id;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private LoanStatus loanStatus;

    public LoanDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LoanStatus getLoanStatus(LoanStatus loanStatus) {
        return this.loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }
}
