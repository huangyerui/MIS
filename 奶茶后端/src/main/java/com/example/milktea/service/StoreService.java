package com.example.milktea.service;

import com.example.milktea.entity.Store;

import java.util.List;
import java.util.Map;

public interface StoreService {

    int update(Store store);
    List<Map<String,Object>> getAll();

}
