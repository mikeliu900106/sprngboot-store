package com.example.sprngbootstore.service;

import com.example.sprngbootstore.dto.UserLoginRequest;
import com.example.sprngbootstore.dto.UserRegisterRequest;
import com.example.sprngbootstore.model.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);
}
