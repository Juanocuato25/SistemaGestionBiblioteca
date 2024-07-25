package com.jarenas.msvc.user.msvc_user.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name can´t be blank or empty")
    @Size(min = 3, max = 40, message = "Name must be between 2 and 40 characters")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Column(unique = true)
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 40, message = "Username must be between 2 and 40 characters")
    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotBlank @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email String email) {
        this.email = email;
    }

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }
}
