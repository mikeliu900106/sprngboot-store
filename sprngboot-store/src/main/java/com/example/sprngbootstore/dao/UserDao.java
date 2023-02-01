package com.example.sprngbootstore.dao;

import com.example.sprngbootstore.dto.UserRegisterRequest;
import com.example.sprngbootstore.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {
    User getUserById(Integer userId);
    User getUserByEmail(String email);

    Integer register(UserRegisterRequest userRegisterRequest);
//    List<User> getUser();

}
