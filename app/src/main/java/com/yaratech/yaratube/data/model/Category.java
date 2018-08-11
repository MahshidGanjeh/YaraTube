package com.yaratech.yaratube.data.model;

public class Category {

    private String name;
    private String avatarImageUrl;

    public Category(String name, String avatarImageUrl) {
        this.name = name;
        this.avatarImageUrl = avatarImageUrl;
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
}
