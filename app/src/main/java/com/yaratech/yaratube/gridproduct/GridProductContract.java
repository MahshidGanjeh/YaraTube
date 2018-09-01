package com.yaratech.yaratube.gridproduct;

import com.yaratech.yaratube.data.model.Product;

import java.util.List;

public interface GridProductContract {

    interface View {

        void showProducts(List<Product> list);

        void showProgressBar();

        void hideProgressBar();
    }

    interface Presenter {

        void loadProducts(int categoryId,int offset);

    }
}
