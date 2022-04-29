package com.example.springbootwebapplication.dto.paginatedDto;

import com.example.springbootwebapplication.dto.SuperDTO;
import com.example.springbootwebapplication.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedUserDTO implements SuperDTO {
    private List<UserDTO> list;
    private long dataCount;
}
