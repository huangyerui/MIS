package com.example.milktea.controller;

import com.example.milktea.entity.Order;
import com.example.milktea.entity.Product;
import com.example.milktea.service.ProductService;
import com.example.milktea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping("/getProAll")
    @ResponseBody
    public List<Map<String, Object>> getProAll(){
        List<Map<String, Object>> product = this.productService.getProAll();
        return product;
    }
    @RequestMapping("/getProductById")
    @ResponseBody
    public Product getProductById(HttpServletRequest request){
        Product product = new Product();
        String product_id=request.getParameter("product_id");
        Integer id = Integer.valueOf(product_id);
        product = this.productService.getProductById(id);
        return product;
    }
}
