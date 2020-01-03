package com.example.milktea.entity;
import java.io.Serializable;
public class User implements Serializable{
    private static final long serialVersionUID=-624939791156631813L;
    private Integer user_id;
    private  String user_name;
    private  String user_password;
    private String user_number;
    private String user_position;
    public void setUserId(Integer user_id){
        this.user_id=user_id;
    }
    public Integer getUserId(){
        return this.user_id;
    }
    public void setUserName(String user_name){
        this.user_name=user_name;
    }
    public String getUserName(){
        return this.user_name;
    }
    public void setUserPassword(String  user_password){
        this.user_password= user_password;
    }
    public String getUserPassword(){
        return this.user_password;
    }
    public void setUserNumber(String user_number){
        this.user_number=user_number;
    }
    public String getUserNumber(){
        return  this.user_number;
    }
    public void setUserPost(String user_position){
        this.user_position=user_position;
    }
    public String getUserPost(){
        return this.user_position;
    }
}
