package com.yaratech.yaratube.home.category.product;

import com.yaratech.yaratube.data.model.Product;

import java.util.List;

public interface ProductContract {

    interface View {

        void showProducts(List<Product> list);

        void showProgressBar();

        void hideProgressBar();
    }

    interface Presenter {

        void loadProducts(int categoryId);

    }
}
