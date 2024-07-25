package com.jarenas.msvc.user.msvc_user.controller;

import com.jarenas.msvc.user.msvc_user.dto.UserDTO;
import com.jarenas.msvc.user.msvc_user.model.entity.User;
import com.jarenas.msvc.user.msvc_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService uService;


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(uService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> userById(@PathVariable Long id){
        try{
            UserDTO userDTO = uService.findUserById(id);
            return ResponseEntity.ok(userDTO);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
}
