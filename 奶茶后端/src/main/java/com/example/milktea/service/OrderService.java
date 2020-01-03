package com.example.milktea.service;

import com.example.milktea.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    int insert(Order order);
    List<Map<String,Object>> getProductSale(String date);
    List<Map<String,Object>> getUserSale(String date);
    List<Map<String,Object>> getSale();
}
