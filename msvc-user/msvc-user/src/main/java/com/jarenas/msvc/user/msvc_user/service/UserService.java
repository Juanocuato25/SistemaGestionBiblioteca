package com.jarenas.msvc.user.msvc_user.service;

import com.jarenas.msvc.user.msvc_user.dto.UserCreationDTO;
import com.jarenas.msvc.user.msvc_user.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO createUser(UserCreationDTO userCreationDTO);
    Optional<UserDTO> findUserById(Long id);
    void deleteUser(Long id);
}
