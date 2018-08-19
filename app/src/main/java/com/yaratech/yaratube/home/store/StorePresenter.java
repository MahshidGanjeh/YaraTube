package com.yaratech.yaratube.home.store;

import android.content.Context;

import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.WebService;
import com.yaratech.yaratube.data.source.remote.RemoteDataSource;

public class StorePresenter implements StoreContract.Presenter {

    private StoreContract.View mView;
    private Repository homeItemRepo;

    public StorePresenter(final StoreContract.View mView , Context context) {
        this.mView = mView;
        homeItemRepo = new Repository(new RemoteDataSource(context));
    }

    @Override
    public void loadHomeItems() {
        homeItemRepo.fetchHomeItems(new WebService.ApiResultCallBack() {
            @Override
            public void onSuccess(Object response) {
                mView.showProgressBar();
                mView.showHomeItems((Store) response);
                mView.hideProgressBar();
            }

            @Override
            public void onFail(Object message) {

            }
        });
    }
}