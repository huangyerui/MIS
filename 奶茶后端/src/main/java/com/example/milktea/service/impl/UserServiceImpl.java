package com.example.milktea.service.impl;

import com.example.milktea.dao.UserDao;
import com.example.milktea.entity.User;
import com.example.milktea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    public int insert(User user){
        return userDao.insert(user);
    }
    public User login(String user_number,String user_password){
        return userDao.login(user_number,user_password);
    };
    public User getUserById(Integer id){
        return userDao.getUserById(id);
    }
    public List<Map<String, Object>> getUserAll(){
        return userDao.getUserAll();
    }
}
