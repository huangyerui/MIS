package com.example.milktea.dao.Impl;

import com.example.milktea.dao.OrderDao;
import com.example.milktea.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int insert(Order order){
        String sql = "insert into new_order(order_id,order_number,order_productNumber,product_id,order_price,user_id,order_date) values(?,?,?,?,?,?,?)";
        return this.jdbcTemplate.update(
                sql,
                order.getOrderId(),
                order.getOrderNumber(),
                order.getOrderProductNum(),
                order.getProductName(),
                order.getOrderPrice(),
                order.getUserName(),
                order.getProductDate());
    }
//    public List<Map<String,Object>> getTotalSale(String date){
//
//        String sql = "select order_date,order_userName,sum(order_price) as order_userPrice from new_order where order_date like '"+ date + "%' group by order_date,order_userName order by order_date;";
//
//        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
//        return list;
//    }
    public List<Map<String,Object>> getUserSale(String date){

        String sql = "select date_format(order_date,'%Y%m') as order_date,user_name,sum(order_price) as order_userPrice from new_order natural join personinfor where order_date like '"+ date + "%' group by date_format(order_date,'%Y%m'),user_id order by order_date,user_id,order_userPrice";

        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
    public List<Map<String,Object>> getProductSale(String date){
        String sql = "select date_format(order_date,'%Y%m') as order_date,product_name,sum(order_price) as order_productPrice from new_order natural join product where order_date like '"+ date + "%' group by date_format(order_date,'%Y%m'),product_id order by order_date,product_id,order_productPrice";

        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
    public List<Map<String,Object>> getSale(){
        String sql = "select date_format(order_date, '%Y-%m-%d %H:%i:%s') as order_date,order_number,group_concat(concat_ws('*',product_name,order_productNumber)) as order_name,sum(order_price) as total_price,user_name from new_order natural join personinfor natural join product group by order_number,order_date,user_name order by order_date DESC;";

        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

//    public Order getById(Integer id){
//        String sql ="select * from order_table where order_id = ?";
//        return  this.jdbcTemplate.queryForObject(sql, new RowMapper<Order>() {
//
//            @Override
//            public Order mapRow(ResultSet rs, int i) throws SQLException {
//                Order order = new Order();
//                order.setOrderId(rs.getInt("order_id"));
//                order.setOrderNumber(rs.getString("order_number"));
//                order.setOrderProductNum(rs.getString("order_productNumber"));
//                order.setUserName(rs.getString("order_userName"));
//                order.setOrderPrice(rs.getString("order_price"));
//                order.setProductDate(rs.getString("order_date"));
//                return order;
//            }
//        },id);
//    }

}
