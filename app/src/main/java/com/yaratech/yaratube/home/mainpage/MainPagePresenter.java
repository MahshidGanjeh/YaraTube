package com.yaratech.yaratube.home.mainpage;

import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.data.util.StoreApiResult;
import com.yaratech.yaratube.data.HomeItemRepo;

public class MainPagePresenter implements MainPageContract.Presenter, StoreApiResult {

    private MainPageContract.View mView;
    private HomeItemRepo homeItemRepo;

    public MainPagePresenter(MainPageContract.View mView) {
        this.mView = mView;
        homeItemRepo = new HomeItemRepo(this);
    }

    @Override
    public void onSuccess(Store list) {
        mView.showHomeItems(list.getHomeitem());
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