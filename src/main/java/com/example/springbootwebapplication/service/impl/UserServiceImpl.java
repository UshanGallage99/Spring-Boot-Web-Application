package com.example.springbootwebapplication.service.impl;

import com.example.springbootwebapplication.dto.UserDTO;
import com.example.springbootwebapplication.exception.EntryDuplicateException;
import com.example.springbootwebapplication.exception.NotFoundException;
import com.example.springbootwebapplication.repo.UserRepo;
import com.example.springbootwebapplication.service.UserService;
import com.example.springbootwebapplication.util.mapper.UserDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserDataMapper userDataMapper;

    @Override
    public String addUser(UserDTO dto) {
        if (!userRepo.existsById(dto.getUsername())) {
           return userRepo.save(userDataMapper.toUser(dto)).getUsername();
        } else {
            throw new EntryDuplicateException("User is already exists");

        }


    }

    @Override
    public UserDTO searchUserById(String userName) {
        UserDTO userDTO = userDataMapper.toUserDTO(userRepo.findByUsername(userName));

        if (userDTO!=null) {

            return userDTO;

        } else {
            throw new NotFoundException("User not exists");

        }



    }

    @Override
    public String updateUserData(UserDTO dto) {

        if (userRepo.existsById(dto.getUsername())) {
            return userRepo.save(userDataMapper.toUser(dto)).getUsername();
        } else {
            throw new EntryDuplicateException("no entry");
        }
    }


}
