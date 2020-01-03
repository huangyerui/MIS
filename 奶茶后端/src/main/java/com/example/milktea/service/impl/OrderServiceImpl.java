package com.example.milktea.service.impl;

import com.example.milktea.dao.OrderDao;
import com.example.milktea.entity.Order;
import com.example.milktea.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    public int insert(Order order){
        return orderDao.insert(order);
    }
    public List<Map<String,Object>> getUserSale(String date){

        return orderDao.getUserSale(date);
    }
    public List<Map<String,Object>> getProductSale(String date){
        return orderDao.getProductSale(date);
    }
    public List<Map<String,Object>> getSale(){ return orderDao.getSale();}
//    public Order getById(Integer id){
//        return orderDao.getById(id);
//    }
//
//    public List<Map<String,Object>> getAll(){
//        return orderDao.getAll();
//    }
}
