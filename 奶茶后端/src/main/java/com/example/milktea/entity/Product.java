package com.example.milktea.entity;

public class Product {
    private Integer product_id;
    private  String product_name;
    private  String product_descripe;
    private String product_src;
    private String product_price;
    public void setProductId(Integer product_id){
        this.product_id=product_id;
    }
    public Integer getProductId(){
        return this.product_id;
    }
    public void setProductName(String product_name){
        this.product_name=product_name;
    }
    public String getProductName(){
        return this.product_name;
    }
    public void setProductDesc(String  product_descripe){
        this.product_descripe= product_descripe;
    }
    public String getProductDesc(){
        return this.product_descripe;
    }
    public void setProductSrc(String product_src){
        this.product_src=product_src;
    }
    public String getProductSrc(){
        return  this.product_src;
    }
    public void setProductPrice(String product_price){
        this.product_price=product_price;
    }
    public String getProductPrice(){
        return this.product_price;
    }
}
