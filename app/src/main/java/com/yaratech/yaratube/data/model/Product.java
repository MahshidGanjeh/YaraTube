package com.yaratech.yaratube.data.model;

public class Product {

    private String name;
    private String avatar;
    private String shortDescription;

    public Product(String name, String avatar, String description) {
        this.name = name;
        this.avatar = avatar;
        this.shortDescription = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return shortDescription;
    }

    public void setDescription(String description) {
        this.shortDescription = description;
    }
}
