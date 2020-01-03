package com.example.milktea.controller;

import com.example.milktea.entity.User;
import com.example.milktea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
   @Autowired
    private UserService userService;
    @RequestMapping("/save")
    @ResponseBody
    public User save(HttpServletRequest request){
        User user = new User();
        String user_id=request.getParameter("user_id");
        String user_name=request.getParameter("user_name");
        String user_number=request.getParameter("user_number");
        String user_password=request.getParameter("user_password");
        String user_position=request.getParameter("user_position");
        Integer id = Integer.valueOf(user_id);
        user.setUserId(id);
        user.setUserName(user_name);
        user.setUserNumber(user_number);
        user.setUserPassword(user_password);
        user.setUserPost(user_position);
        int result = this.userService.insert(user);
        System.out.print(result);
        return user;
    }
    @RequestMapping("/login")
    @ResponseBody
    public User login(HttpServletRequest request){
        User user = new User();
        String user_number=request.getParameter("user_number");
        String user_password=request.getParameter("user_password");
        user=this.userService.login(user_number,user_password);
        return user;
    }
    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUserById(HttpServletRequest request){
        User user = new User();
        String user_id=request.getParameter("user_id");
        Integer id = Integer.valueOf(user_id);
        user = this.userService.getUserById(id);
        System.out.print(user.getUserName());
        return user;
    }
    @RequestMapping("/getUserAll")
    @ResponseBody
    public List<Map<String, Object>> getUserAll(){
        List<Map<String, Object>> list =  this.userService.getUserAll();
        return list;
    }
}
