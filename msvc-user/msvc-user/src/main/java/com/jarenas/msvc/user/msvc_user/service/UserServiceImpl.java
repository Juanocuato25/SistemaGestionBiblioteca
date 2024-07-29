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
    public UserDTO createUser(UserCreationDTO userCreationDTO) {//Recibe por parametro el obj userCreationDto
        User user = UserMapper.INSTANCE.toUser(userCreationDTO);//Aqui lo mapea de uCDto a user
        return UserMapper.INSTANCE.toUserDTO(uRepository.save(user));//aqui lo mapea de user a userdto
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
        User user = uRepository.findById(id)
                .orElseThrow(() -> new UserExceptions("User not found", HttpStatus.NOT_FOUND));
        uRepository.deleteById(user.getId());
    }

    @Override
    @Transactional
    public boolean existsByEmail(String email) {
        return uRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public UserDTO updateUSer(UserCreationDTO userCreationDTO, Long id) {
        User user = uRepository.findById(id)
                .orElseThrow(() -> new UserExceptions("User not found", HttpStatus.NOT_FOUND));

        user.setName(userCreationDTO.getName());
        user.setUsername(userCreationDTO.getUsername());
        user.setEmail(userCreationDTO.getEmail());
        user.setAddress(userCreationDTO.getAddress());
        user.setPhoneNumber(userCreationDTO.getPhoneNumber());
        user.setPassword(userCreationDTO.getPassword());

        return userMapper.toUserDTO(uRepository.save(user));
    }


}
