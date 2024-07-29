package com.jarenas.msvc.book.exceptions;

import org.springframework.http.HttpStatus;

public class BookExceptions extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    public BookExceptions(String message, HttpStatus httpStatus) {
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
