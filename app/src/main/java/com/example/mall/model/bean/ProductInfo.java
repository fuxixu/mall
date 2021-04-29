package com.example.mall.model.bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ProductInfo {
    private String name;
    private int imageId;
    private String param;
    private float price;
    private int number;
    @Id(autoincrement = true)//设置自增长
    private Long id;




    public ProductInfo(String name, int imageId, String param, float price, int number) {
        this.name = name;
        this.imageId = imageId;
        this.param = param;
        this.price = price;
        this.number = number;
    }
    public ProductInfo(String name , int imageId, String param, float price) {
        this.name = name;
        this.imageId = imageId;
        this.param = param;
        this.price = price;
    }
    @Generated(hash = 309635516)
    public ProductInfo(String name, int imageId, String param, float price, int number,
            Long id) {
        this.name = name;
        this.imageId = imageId;
        this.param = param;
        this.price = price;
        this.number = number;
        this.id = id;
    }
    @Generated(hash = 49329718)
    public ProductInfo() {
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }


}
