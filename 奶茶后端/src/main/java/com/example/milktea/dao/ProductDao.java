package com.example.milktea.dao;

import com.example.milktea.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductDao {
    List<Map<String, Object>> getProAll();
    Product getProductById (Integer id);
}
