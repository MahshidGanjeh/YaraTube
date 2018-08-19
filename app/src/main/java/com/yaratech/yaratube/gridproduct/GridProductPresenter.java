package com.yaratech.yaratube.gridproduct;

import android.content.Context;
import android.util.Log;

import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.WebService;
import com.yaratech.yaratube.data.source.remote.RemoteDataSource;

import java.util.List;

public class GridProductPresenter implements GridProductContract.Presenter {

    private Repository productsRepo;
    private GridProductContract.View mView;

    public GridProductPresenter(GridProductContract.View mView , Context context) {
        this.productsRepo = new Repository(new RemoteDataSource(context));
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
