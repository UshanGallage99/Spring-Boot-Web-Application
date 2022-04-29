package com.example.springbootwebapplication.controller;

import com.example.springbootwebapplication.dto.UserDTO;
import com.example.springbootwebapplication.models.AuthenticationRequest;
import com.example.springbootwebapplication.models.AuthenticationResponse;
import com.example.springbootwebapplication.service.UserService;
import com.example.springbootwebapplication.service.impl.UserDataService;
import com.example.springbootwebapplication.util.JwtUtil;
import com.example.springbootwebapplication.util.StandardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserDetailsController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDataService userDataService;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final Logger LOGGER = LoggerFactory.getLogger(UserDetailsController.class);

//
    @PostMapping(
            path = "/authenticate",
            consumes = {MediaType.APPLICATION_JSON_VALUE}


    )
    public ResponseEntity addUser(@RequestBody UserDTO dto) {
        String id = userService.addUser(dto);
        return new ResponseEntity(
                new StandardResponse(201, id + " success added", dto),
                HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<StandardResponse> updateUser(@Valid @RequestBody UserDTO dto) {
        String id = userService.updateUserData(dto);
        LOGGER.info("updated User Details, Id:" + dto.getUsername());
        return new ResponseEntity(
                new StandardResponse(204, "success", dto),
                HttpStatus.OK
        );

    }

    @GetMapping(
            params = {"userName"})
    public ResponseEntity<StandardResponse> searchUser(
            @RequestParam(value = "userName") String userName

    ) {

        UserDTO userDTO = null;

        userDTO = userService.searchUserById(userName);
        LOGGER.info("Get By userName");


        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "success", userDTO),
                HttpStatus.OK);
    }

    @GetMapping
    public String hello() {
        return "Hello World";
    }


    @GetMapping(
            path = "/authenticate",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity createAuthenticationToken(

            @RequestBody AuthenticationRequest authenticationRequest
            )throws Exception{


        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            ));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect User name or password", e);
        }
//        System.out.println("authenticationRequest.getUserName() = " + authenticationRequest.getUserName());
        UserDetails userDetails = userDataService.loadUserByUsername(authenticationRequest.getUsername());
      final String jwt = jwtUtil.generateToken(userDetails);
      return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
