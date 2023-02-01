package com.example.sprngbootstore.service.impl;

import com.example.sprngbootstore.dao.UserDao;
import com.example.sprngbootstore.dto.UserLoginRequest;
import com.example.sprngbootstore.dto.UserRegisterRequest;
import com.example.sprngbootstore.model.User;
import com.example.sprngbootstore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService
{
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        if(user != null){
            log.warn("該email{}已被註冊",userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);

        return userDao.register(userRegisterRequest);
    }
    public User login(UserLoginRequest userLoginRequest){
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());
        //檢查使用者
        if(user==null){
            log.warn("該email {} 尚未住測",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String hashPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());

        if(user.getPassword().equals(hashPassword)){
            return  user;
        }else{
            log.warn("該email {} 密碼不正確",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
}
