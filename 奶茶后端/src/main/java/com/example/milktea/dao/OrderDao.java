package com.example.milktea.dao;

import com.example.milktea.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    int insert(Order order);
//    Order getById(Integer id);
    List<Map<String,Object>> getSale();
    List<Map<String,Object>> getProductSale(String date);
    List<Map<String,Object>> getUserSale(String date);
}
