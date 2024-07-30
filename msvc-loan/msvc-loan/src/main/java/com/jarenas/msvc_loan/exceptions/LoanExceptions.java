package com.jarenas.msvc_loan.exceptions;

import org.springframework.http.HttpStatus;


public class LoanExceptions extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public LoanExceptions(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}