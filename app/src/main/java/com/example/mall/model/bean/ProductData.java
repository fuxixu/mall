package com.example.mall.model.bean;

public class ProductData {
    private float price;
    private String param;
    private int imageId;
    private int number;



    private String name;
    public ProductData() {

    }
    public ProductData(String name,int imageId, String param, float price, int number) {
        this.name = name;
        this.imageId = imageId;
        this.param = param;
        this.price = price;
        this.number = number;
    }
    public ProductData(String name ,int imageId, String param, float price) {
        this.name = name;
        this.imageId = imageId;
        this.param = param;
        this.price = price;
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


}
