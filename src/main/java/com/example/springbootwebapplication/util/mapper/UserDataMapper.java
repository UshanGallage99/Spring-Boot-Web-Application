package com.example.springbootwebapplication.util.mapper;

import com.example.springbootwebapplication.dto.UserDTO;
import com.example.springbootwebapplication.entity.User;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDataMapper {
    User toUser(UserDTO userDTO);
    UserDTO toUserDTO(User user);
    List<UserDTO> pageToUserDTO(Page<User> users);
    List<User> toUserAll(List<UserDTO> userDTOS);
}
