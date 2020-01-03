package com.example.milktea.dao.Impl;

import com.example.milktea.dao.StoreDao;
import com.example.milktea.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class StoreDaoImpl implements StoreDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int update(Store store){
        String sql = "update store set store_number = ? where store_id = ?";
        return this.jdbcTemplate.update(
                sql,
                store.getStoreNumber(),
                store.getStoreId()
        );
    }

    public List<Map<String,Object>> getAll(){
        String sql = "select * from store";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
}
