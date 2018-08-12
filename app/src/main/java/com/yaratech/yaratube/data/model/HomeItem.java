package com.yaratech.yaratube.data.model;

import java.util.ArrayList;

public class HomeItem {

    private String title;
    private int id;
    private ArrayList<Product> products;


    public HomeItem(String title, int id, ArrayList<Product> products) {
        this.title = title;
        this.id = id;
        this.products = products;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
