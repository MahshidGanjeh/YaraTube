package com.yaratech.yaratube.home.store;

import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.data.util.StoreApiResult;
import com.yaratech.yaratube.data.HomeItemRepo;

public class StorePresenter implements StoreContract.Presenter, StoreApiResult {

    private StoreContract.View mView;
    private HomeItemRepo homeItemRepo;

    public StorePresenter(StoreContract.View mView) {
        this.mView = mView;
        homeItemRepo = new HomeItemRepo(this);
    }

    @Override
    public void onSuccess(Store list) {
        mView.showHomeItems(list);
    }


    @Override
    public void onFail(String error) {
       // Toast.makeText(error , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadHomeItems() {
        homeItemRepo.fetchHomeItems();
    }
}