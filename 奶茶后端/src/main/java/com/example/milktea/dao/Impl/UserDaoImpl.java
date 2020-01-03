package com.example.milktea.dao.Impl;

import com.example.milktea.dao.UserDao;
import com.example.milktea.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int insert(User user){
        String sql = "insert into personinfor(user_id,user_password,user_name,user_position,user_email) values(?,?,?,?,?)";
        return this.jdbcTemplate.update(
                sql,
                user.getUserId(),
                user.getUserPassword(),
                user.getUserName(),
                user.getUserPost(),
                user.getUserNumber()
        );
    }
    public User login(String user_number,String user_password){
        String sql = "select * from personinfor where user_number = ? and user_password=?";
        return this.jdbcTemplate.queryForObject(sql, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setUserPassword("加密");
                user.setUserNumber(rs.getString("user_number"));
                user.setUserPost(rs.getString("user_position"));
                return user;
            }
        }, user_number,user_password);
    }
    public User getUserById(Integer id) {
        String sql = "select * from personinfor where user_id = ?";
        return this.jdbcTemplate.queryForObject(sql, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setUserPassword(rs.getString("user_password"));
                        user.setUserNumber(rs.getString("user_number"));
                user.setUserPost(rs.getString("user_position"));
                return user;
            }
        }, id);
    }
        public List<Map<String, Object>> getUserAll() {
        String sql = "select * from personinfor";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
}

