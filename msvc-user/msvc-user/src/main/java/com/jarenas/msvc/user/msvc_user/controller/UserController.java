package com.jarenas.msvc.user.msvc_user.controller;

import com.jarenas.msvc.user.msvc_user.dto.UserCreationDTO;
import com.jarenas.msvc.user.msvc_user.dto.UserDTO;
import com.jarenas.msvc.user.msvc_user.exceptions.UserExceptions;
import com.jarenas.msvc.user.msvc_user.model.entity.User;
import com.jarenas.msvc.user.msvc_user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService uService;


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(uService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> userById(@PathVariable Long id) {
        try {
            UserDTO userDTO = uService.findUserById(id);
            return ResponseEntity.ok(userDTO);
        } catch (UserExceptions e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreationDTO userCreationDTO, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        if (!userCreationDTO.getEmail().isBlank() && uService.existsByEmail(userCreationDTO.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("Error", "Is al ready user with that email"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(uService.createUser(userCreationDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            uService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (UserExceptions e) {
            return ResponseEntity.status(e.getHttpStatus()).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> EditUser(@Valid @RequestBody UserCreationDTO userCreationDTO, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validar(result);
        }
        try {
            UserDTO userDTO = uService.updateUSer(userCreationDTO, id);
            return ResponseEntity.ok(userDTO);
        } catch (UserExceptions e) {
            return ResponseEntity.status(e.getHttpStatus()).body(null);
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
