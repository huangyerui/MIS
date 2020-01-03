package com.example.milktea.service;

import com.example.milktea.dao.UserDao;
import com.example.milktea.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
public interface ProductService {
   List<Map<String, Object>> getProAll();
   Product getProductById(Integer id);
}
