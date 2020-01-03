package com.example.milktea.controller;

import com.example.milktea.entity.Order;
import com.example.milktea.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
   @RequestMapping("/order")
public class OrderController {
       @Autowired
        private OrderService orderService;
       @RequestMapping("/save")
       @ResponseBody
        public Order save(HttpServletRequest request) {
            Order order = new Order();
            String order_id = request.getParameter("order_id");
            String order_number = request.getParameter("order_number");
            String order_productNumber = request.getParameter("order_productNumber");
            String product_id = request.getParameter("product_id");
            String order_price = request.getParameter("order_price");
            String user_id = request.getParameter("user_id");
            String order_date = request.getParameter("order_date");
            Integer o_id = Integer.valueOf(order_id);
            Integer p_id = Integer.valueOf(product_id);
            Integer u_id = Integer.valueOf(user_id);
            order.setOrderId(o_id);
            order.setOrderNumber(order_number);
            order.setOrderProductNum(order_productNumber);
            order.setProductName(p_id);
            order.setOrderPrice(order_price);
            order.setUserName(u_id);
            order.setProductDate(order_date);

            int result = this.orderService.insert(order);
            System.out.print(result);
            return order;
        }
    @RequestMapping("/getUserSale")
    @ResponseBody
    public List<Map<String, Object>> getUserSale(HttpServletRequest request){
        String date=request.getParameter("order_date");
        List<Map<String, Object>> order = this.orderService.getUserSale(date);
        return order;
    }

    @RequestMapping("/getProductSale")
    @ResponseBody
    public List<Map<String, Object>> getProductSale(HttpServletRequest request){
        String date=request.getParameter("order_date");

        List<Map<String, Object>> order = this.orderService.getProductSale(date);
        return order;
    }
    @RequestMapping("/getSale")
    @ResponseBody
    public List<Map<String, Object>> getSale(){
        List<Map<String, Object>> order = this.orderService.getSale();
        return order;
    }
}
