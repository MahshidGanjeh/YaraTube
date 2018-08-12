package com.yaratech.yaratube.data.model;

public class Product {

    private String name;
    private String avatar;
    private String description;

    public Product(String name, String avatar, String description) {
        this.name = name;
        this.avatar = avatar;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getavatar() {
        return avatar;
    }

    public void setavatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
