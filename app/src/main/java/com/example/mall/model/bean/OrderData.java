package com.example.mall.model.bean;

public class OrderData {
    private String time;
    private String name;
    private String allPrice;
    private String status;


    public OrderData(String time, String name, String allPrice, String status) {
        this.time = time;
        this.name = name;
        this.allPrice = allPrice;
        this.status = status;
    }




    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(String allPrice) {
        this.allPrice = allPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
