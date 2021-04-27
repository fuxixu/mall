package com.example.mall.model.bean;

public class MCartData {

    private float price;
    private String param;
    private int imageId;
    private int number;

    public MCartData(int imageId, String param, float price, int number) {
        this.imageId = imageId;
        this.param = param;
        this.price = price;
        this.number = number;
    }

    public float getPrice() {
        return price;
    }

    public int getImageId() {
        return imageId;
    }
    public String getParam() {
        return param;
    }

    public int getNumber() {
        return number;
    }
}
