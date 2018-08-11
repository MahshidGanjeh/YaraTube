package com.yaratech.yaratube.data.model;

public class Category {

    private String title;
    private String avatar;

    public Category(String name, String avatar) {
        this.title = name;
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        title = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
