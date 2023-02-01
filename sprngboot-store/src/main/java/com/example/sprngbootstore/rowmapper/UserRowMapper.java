package com.example.sprngbootstore.rowmapper;

import com.example.sprngbootstore.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUser_id(rs.getInt("user_id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setCreated_date(rs.getDate("created_date"));
        user.setLast_modified_date(rs.getDate("last_modified_date"));
        return user;
    }
}
