package com.jarenas.msvc.book.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookCreationDTO {

    @NotBlank(message = "Title can not be blank or empty")
    private String title;

    @NotBlank(message = "Author can not be empty")
    private String author;

    @NotBlank(message = "isbn can not be empty")
    @Column(unique = true)
    private String isbn;

    @NotNull(message = "Published year can not be null")
    private Integer publishedYear;


    public @NotBlank(message = "Title can not be blank or empty") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title can not be blank or empty") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Author can not be empty") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank(message = "Author can not be empty") String author) {
        this.author = author;
    }

    public @NotBlank(message = "isbn can not be empty") String getIsbn() {
        return isbn;
    }

    public void setIsbn(@NotBlank(message = "isbn can not be empty") String isbn) {
        this.isbn = isbn;
    }

    public @NotNull(message = "Published year can not be empty") Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(@NotNull(message = "Published year can not be empty") Integer publishedYear) {
        this.publishedYear = publishedYear;
    }
}
