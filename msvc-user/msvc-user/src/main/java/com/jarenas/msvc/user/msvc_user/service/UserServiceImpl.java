package com.jarenas.msvc.user.msvc_user.service;

import com.jarenas.msvc.user.msvc_user.dto.UserCreationDTO;
import com.jarenas.msvc.user.msvc_user.dto.UserDTO;
import com.jarenas.msvc.user.msvc_user.exceptions.UserExceptions;
import com.jarenas.msvc.user.msvc_user.mapper.UserMapper;
import com.jarenas.msvc.user.msvc_user.model.entity.User;
import com.jarenas.msvc.user.msvc_user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository uRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        List<User> users = uRepository.findAll();
        return users.stream().map(userMapper::toUserDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTO createUser(UserCreationDTO userCreationDTO) {
        User user = UserMapper.INSTANCE.toUser(userCreationDTO);
        return UserMapper.INSTANCE.toUserDTO(uRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findUserById(Long id) {
        User user = uRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User does not exist"));
        return UserMapper.INSTANCE.toUserDTO(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        Optional<User> ou = this.uRepository.findById(id);
        if (ou.isPresent()){
            throw new UserExceptions("User not found", HttpStatus.NOT_FOUND);
        }
        uRepository.deleteById(id);
    }


}
