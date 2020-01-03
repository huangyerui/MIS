package com.example.milktea.dao;
import com.example.milktea.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    int insert(User user);
    User getUserById(Integer id);
    User login(String user_name,String user_password);
    List<Map<String, Object>> getUserAll();
}
