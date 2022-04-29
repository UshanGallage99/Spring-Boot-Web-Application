package com.example.springbootwebapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_data")
public class User implements SuperEntity{

    @Id
    @Column(nullable = false, length = 45,name = "user_name")
    @NotNull(message = "user Name is mandatory")
    private String username;

    @Column(nullable = false, length = 25,name = "password")
    @NotNull(message = " password is mandatory")
    private String password;

    @Column(nullable = false, length = 15,name = "nic")
    @NotNull(message = "nic is mandatory")
    public String nic;

    @Column(nullable = false, length = 45,name = "first_name")
    @NotNull(message = "first Name is mandatory")
    public String firstName;

    @Column(nullable = false, length = 45,name = "last_name")
    @NotNull(message = "last Name is mandatory")
    public String lastName;
}
