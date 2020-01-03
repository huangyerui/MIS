package com.example.milktea.service.impl;

import com.example.milktea.dao.ProductDao;
import com.example.milktea.dao.UserDao;
import com.example.milktea.entity.Product;
import com.example.milktea.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDao productDao;
    public List<Map<String, Object>> getProAll(){
        return  productDao.getProAll();
    };
    public Product getProductById(Integer id){
        return productDao.getProductById(id);
    };
}
