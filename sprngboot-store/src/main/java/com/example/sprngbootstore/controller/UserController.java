package com.example.sprngbootstore.controller;

import com.example.sprngbootstore.dto.UserLoginRequest;
import com.example.sprngbootstore.dto.UserRegisterRequest;
import com.example.sprngbootstore.model.User;
import com.example.sprngbootstore.service.UserService;
import com.example.sprngbootstore.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    @Autowired
    private UserService userService;
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId){
            User user = userService.getUserById(userId);
            if(user != null){
                return  ResponseEntity.status(HttpStatus.OK).body(user);
            }else{
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

    }
    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        Integer userId = userService.register(userRegisterRequest);
        User user = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        User user = userService.login(userLoginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


}
