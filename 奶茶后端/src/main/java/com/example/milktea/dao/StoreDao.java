package com.example.milktea.dao;

import com.example.milktea.entity.Store;

import java.util.List;
import java.util.Map;

public interface StoreDao {

    int update(Store store);
    List<Map<String,Object>> getAll();
}
