package com.jarenas.msvc.user.msvc_user.service;

import com.jarenas.msvc.user.msvc_user.dto.UserCreationDTO;
import com.jarenas.msvc.user.msvc_user.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO createUser(UserCreationDTO userCreationDTO);
    UserDTO findUserById(Long id);
    void deleteUser(Long id);
    boolean existsByEmail(String email);
    UserDTO updateUSer(UserCreationDTO userCreationDTO, Long id);
}
