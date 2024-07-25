package com.jarenas.msvc.user.msvc_user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserCreationDTO {

    @NotBlank(message = "Name can´t be blank or empty")
    @Size(min = 3, max = 40, message = "Name must be between 2 and 40 characters")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Column(unique = true)
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Addres is mandatory")
    private String addres;

    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 40, message = "Username must be between 2 and 40 characters")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 6 characters")
    private String password;

    public @NotBlank(message = "Name can´t be blank or empty") @Size(min = 3, max = 40, message = "Name must be between 2 and 40 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name can´t be blank or empty") @Size(min = 3, max = 40, message = "Name must be between 2 and 40 characters") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Email is mandatory") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is mandatory") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Addres is mandatory") String getAddres() {
        return addres;
    }

    public void setAddres(@NotBlank(message = "Addres is mandatory") String addres) {
        this.addres = addres;
    }

    public @NotBlank(message = "Phone number is mandatory") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "Phone number is mandatory") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @NotBlank(message = "Username is mandatory") @Size(min = 3, max = 40, message = "Username must be between 2 and 40 characters") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username is mandatory") @Size(min = 3, max = 40, message = "Username must be between 2 and 40 characters") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Password is mandatory") @Size(min = 8, message = "Password must be at least 6 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is mandatory") @Size(min = 8, message = "Password must be at least 6 characters") String password) {
        this.password = password;
    }
}
