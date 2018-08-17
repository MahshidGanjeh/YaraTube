package com.yaratech.yaratube.home.store;

import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.WebService;

public class StorePresenter implements StoreContract.Presenter {

    private StoreContract.View mView;
    private Repository homeItemRepo;

    public StorePresenter(final StoreContract.View mView) {
        this.mView = mView;
        homeItemRepo = new Repository();
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