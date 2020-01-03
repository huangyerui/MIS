package com.example.milktea.entity;

public class Order {
    private Integer order_id;
    private  String order_number;
    private  String order_productNumber;
    private Integer product_id;
    private String order_price;
    private Integer user_id;
    private String  order_date;

    public void setOrderId(Integer order_id){ this.order_id=order_id; }
    public Integer getOrderId(){ return this.order_id; }
    public void setOrderNumber(String order_number){
        this.order_number=order_number;
    }
    public String getOrderNumber(){
        return this.order_number;
    }
    public String getOrderProductNum(){
        return this.order_productNumber;
    }
    public void setOrderProductNum(String order_productNumber){
        this.order_productNumber= order_productNumber;
    }
    public Integer getProductName(){
        return this.product_id;
    }
    public void setProductName(Integer product_id){
        this.product_id=product_id;
    }
    public String getOrderPrice(){
        return  this.order_price;
    }
    public void setOrderPrice(String order_price){ this.order_price=order_price; }
    public Integer getUserName(){
        return this.user_id;
    }
    public void setUserName(Integer  user_id){
        this. user_id= user_id;
    }
    public String getProductDate(){
        return this.order_date;
    }
    public void setProductDate(String order_date){
        this.order_date=order_date;
    }
}
