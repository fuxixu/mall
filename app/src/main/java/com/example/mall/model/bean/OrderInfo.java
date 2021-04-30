package com.example.mall.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class OrderInfo {
    @Id(autoincrement = true)//设置自增长
    private Long id;
    private String time;
    private String name;
    private String number;
    private String allPrice;
    private Boolean status;
    @Generated(hash = 1495214430)
    public OrderInfo(Long id, String time, String name, String number,
            String allPrice, Boolean status) {
        this.id = id;
        this.time = time;
        this.name = name;
        this.number = number;
        this.allPrice = allPrice;
        this.status = status;
    }
    @Generated(hash = 1695813404)
    public OrderInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getAllPrice() {
        return this.allPrice;
    }
    public void setAllPrice(String allPrice) {
        this.allPrice = allPrice;
    }
    public Boolean getStatus() {
        return this.status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }


}
