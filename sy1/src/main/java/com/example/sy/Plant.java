package com.example.sy;
public class Plant {
    private String name;
    private String context;
    private String imageViewR;
    public Plant(String name, String context, String imageViewR){
        this.name = name;
        this.context = context;
        this.imageViewR = imageViewR;
    }
    public Plant(){}
    public String getImageViewR() {
        return imageViewR;
    }
    public String getContext() {
        return context;
    }
    public String getName() {
        return name;
    }
    public void setImageViewR(String imageViewR) {
        this.imageViewR = imageViewR;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setContext(String context) {
        this.context = context;
    }
}
