package com.jarenas.msvc.user.msvc_user.mapper;

import com.jarenas.msvc.user.msvc_user.dto.UserCreationDTO;
import com.jarenas.msvc.user.msvc_user.dto.UserDTO;
import com.jarenas.msvc.user.msvc_user.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserCreationDTO userCreationDTO);

    UserDTO toUserDTO(User user);
}
