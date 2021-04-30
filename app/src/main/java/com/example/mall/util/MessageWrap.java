package com.example.mall.util;

public class MessageWrap {

    public final int message;

    public float getAllPrice() {
        return allPrice;
    }
    public void setAllPrice(float allPrice) {
        this.allPrice = allPrice;
    }

    public  float allPrice;
    public MessageWrap(int message) {
        this.message = message;
    }

    public int getMessage() {
        return message;
    }




}
