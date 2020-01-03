package com.example.milktea.service;

import com.example.milktea.entity.User;

import java.util.List;
import java.util.Map;

public interface  UserService {
    int insert(User user);
    User login(String user_number,String user_password);
    User getUserById(Integer id);
    List<Map<String, Object>> getUserAll();
}
