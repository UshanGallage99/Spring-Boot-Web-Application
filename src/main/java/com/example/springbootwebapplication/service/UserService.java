package com.example.springbootwebapplication.service;

import com.example.springbootwebapplication.dto.UserDTO;

public interface UserService {
    public String addUser(UserDTO dto);
    UserDTO searchUserById(String userName);
    public String updateUserData(UserDTO dto);


}
