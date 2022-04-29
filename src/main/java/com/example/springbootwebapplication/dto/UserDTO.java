package com.example.springbootwebapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements SuperDTO {

    private String username;
    private String password;
    public String nic;
    public String firstName;
    public String lastName;
}
