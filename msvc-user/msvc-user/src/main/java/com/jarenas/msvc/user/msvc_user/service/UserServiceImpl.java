package com.jarenas.msvc.user.msvc_user.service;

import com.jarenas.msvc.user.msvc_user.dto.UserCreationDTO;
import com.jarenas.msvc.user.msvc_user.dto.UserDTO;
import com.jarenas.msvc.user.msvc_user.mapper.UserMapper;
import com.jarenas.msvc.user.msvc_user.model.entity.User;
import com.jarenas.msvc.user.msvc_user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository uRepository;
    private final UserDTO userDTO;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    public UserServiceImpl(UserRepository uRepository, UserDTO userDTO) {
        this.uRepository = uRepository;
        this.userDTO = userDTO;
    }


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
    public Optional<UserDTO> findUserById(Long id) {
        User user = uRepository.findById(id).orElse(null);
        if (user == null){
            return Optional.empty();
        }
        return Optional.of(UserMapper.INSTANCE.toUserDTO(user));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        Optional<User> ou = this.uRepository.findById(id);
        if (ou.isPresent()){
            System.out.println("Error");
        }
        uRepository.deleteById(id);
    }


}
