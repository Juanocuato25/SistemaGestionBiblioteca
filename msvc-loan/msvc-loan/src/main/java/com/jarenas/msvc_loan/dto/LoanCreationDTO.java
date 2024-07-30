package com.jarenas.msvc_loan.dto;

import com.jarenas.msvc_loan.model.BookDTO;
import com.jarenas.msvc_loan.model.UserDTO;
import com.jarenas.msvc_loan.model.entity.LoanStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class LoanCreationDTO {


    @NotNull(message = "User_Id can not be null")
    private Long user_id;

    @NotNull(message = "Book_Id can not be null")
    private Long book_id;

    @NotNull(message = "LoanDate can not be null")
    private LocalDate loanDate;

    @NotNull(message = "Return date can not be null")
    private LocalDate returndDate;


    private LoanStatus loanStatus;


    private BookDTO bookDTO;


    private UserDTO userDTO;


    public @NotNull Long getUser_id() {
        return user_id;
    }

    public void setUser_id(@NotNull Long user_id) {
        this.user_id = user_id;
    }

    public @NotNull Long getBook_id() {
        return book_id;
    }

    public void setBook_id(@NotNull Long book_id) {
        this.book_id = book_id;
    }

    public @NotNull LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(@NotNull LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public @NotNull LocalDate getReturndDate() {
        return returndDate;
    }

    public void setReturndDate(@NotNull LocalDate returndDate) {
        this.returndDate = returndDate;
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

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public LoanStatus setLoanStatus(LoanStatus loanStatus) {
        LocalDate today = LocalDate.now();
        if (today.isBefore(returndDate) || today.isEqual(returndDate)) {
            return LoanStatus.ON_TIME; // Aún no se ha pasado la fecha de devolución
        } else {
            return LoanStatus.LATE; // Ya se ha pasado la fecha de devolución
        }

    }


}
