package com.example.mall.model.bean;

public class MainRecyclerViewItemData {

    private String name;

    private int imageId;

    public MainRecyclerViewItemData(String name, int imageId) {
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
