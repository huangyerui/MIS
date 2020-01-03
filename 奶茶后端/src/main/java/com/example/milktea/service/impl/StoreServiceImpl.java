package com.example.milktea.service.impl;

import com.example.milktea.dao.StoreDao;
import com.example.milktea.entity.Store;
import com.example.milktea.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreDao storeDao;

    @Override
    public int update(Store store) {
        return storeDao.update(store);
    }

    public List<Map<String,Object>> getAll(){
        return storeDao.getAll();
    }

}
