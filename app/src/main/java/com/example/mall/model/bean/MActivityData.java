package com.example.mall.model.bean;

public class MActivityData {

    private String name;

    private int imageId;

    public MActivityData(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

}
