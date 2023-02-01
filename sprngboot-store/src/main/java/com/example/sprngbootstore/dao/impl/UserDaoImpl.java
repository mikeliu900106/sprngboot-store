package com.example.sprngbootstore.dao.impl;

import com.example.sprngbootstore.dao.UserDao;
import com.example.sprngbootstore.dto.UserRegisterRequest;
import com.example.sprngbootstore.model.User;
import com.example.sprngbootstore.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public User getUserById(Integer userId){
        String sql = "Select user_id, email,password,created_date,last_modified_date " +
                     "FROM java_store.user WHERE user_id = :user_id";

        Map<String,Object> map = new HashMap<>();
        map.put("user_id",userId);
        List<User> user = namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());

        if(user.size()>0){
            return user.get(0);
        }else{
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "Select user_id, email,password,created_date,last_modified_date " +
                "FROM java_store.user WHERE email = :email";

        Map<String,Object> map = new HashMap<>();
        map.put("email",email);
        List<User> user = namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());

        if(user.size()>0){
            return user.get(0);
        }else{
            return null;
        }
    }

    public Integer register(UserRegisterRequest userRegisterRequest){
        String sql = "Insert INTO java_store.user(email,password,created_date,last_modified_date)value(:email,:password,:created_date,:last_modified_date)";
        Map<String,Object> map = new HashMap<>();
        map.put("email",userRegisterRequest.getEmail());
        map.put("password",userRegisterRequest.getPassword());
        Date now = new Date();
        map.put("created_date",now);
        map.put("last_modified_date", now);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        int userId = keyHolder.getKey().intValue();
        return userId;
    }
}
