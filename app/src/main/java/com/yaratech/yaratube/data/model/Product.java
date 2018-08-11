package com.yaratech.yaratube.data.model;

public class Product {

    private String name;
    private String avatarImageUrl;
    private String description;

    public Product(String name, String avatarImageUrl, String description) {
        this.name = name;
        this.avatarImageUrl = avatarImageUrl;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarImageUrl() {
        return avatarImageUrl;
    }

    public void setAvatarImageUrl(String avatarImageUrl) {
        this.avatarImageUrl = avatarImageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
