package com.yaratech.yaratube.gridproduct;

import android.util.Log;

import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.WebService;

import java.util.List;

public class ProductPresenter implements ProductContract.Presenter {

    private Repository productsRepo;
    private ProductContract.View mView;

    public ProductPresenter(ProductContract.View mView) {
        this.productsRepo = new Repository();
        this.mView = mView;
    }

    @Override
    public void loadProducts(int id) {
        productsRepo.fetchProductsByCategoryId(new WebService.ApiResultCallBack() {
            @Override
            public void onSuccess(Object response) {
                mView.showProgressBar();
                mView.showProducts((List) response);
                mView.hideProgressBar();
            }

            @Override
            public void onFail(Object message) {
                Log.d("hey", String.valueOf(message));
            }
        }, id);
    }
}
