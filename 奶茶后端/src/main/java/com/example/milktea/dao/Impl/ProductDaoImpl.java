package com.example.milktea.dao.Impl;

import com.example.milktea.dao.ProductDao;
import com.example.milktea.entity.Product;
import com.example.milktea.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
@Repository
public class ProductDaoImpl implements ProductDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<Map<String, Object>> getProAll(){
        String sql = "select * from product";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    };
    public Product getProductById(Integer id) {
        String sql = "select * from product where product_id = ?";
        return this.jdbcTemplate.queryForObject(sql, new RowMapper<Product>() {

            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductDesc(rs.getString("product_descripe"));
                product.setProductSrc(rs.getString("product_src"));
                product.setProductPrice(rs.getString("product_price"));
                return product;
            }
        }, id);
    }
}
