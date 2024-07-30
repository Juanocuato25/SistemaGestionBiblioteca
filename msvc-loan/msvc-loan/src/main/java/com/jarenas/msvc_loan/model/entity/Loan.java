package com.jarenas.msvc_loan.model.entity;


import com.jarenas.msvc_loan.model.BookDTO;
import com.jarenas.msvc_loan.model.UserDTO;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;
    private Long book_id;
    private LocalDate loanDate;
    private LocalDate returnDate;

    @Transient
    private BookDTO bookDTO;
    @Transient
    private UserDTO userDTO;


    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;

    public Loan() {
    }

    public Loan(Long id, Long user_id, Long book_id, LocalDate loanDate, LocalDate returnDate, LoanStatus loanStatus) {
        this.id = id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.loanStatus = loanStatus;
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


    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @PrePersist
    @PreUpdate
    private void updateStatusLoan(){
        this.loanStatus = calculateStatus();
    }

    public LoanStatus calculateStatus(){
        LocalDate today = LocalDate.now();

        if (today.isBefore(returnDate) || today.isEqual(returnDate)) {
            return LoanStatus.ON_TIME; // Aún no se ha pasado la fecha de devolución
        } else {
            return LoanStatus.LATE; // Ya se ha pasado la fecha de devolución
        }
    }
}
