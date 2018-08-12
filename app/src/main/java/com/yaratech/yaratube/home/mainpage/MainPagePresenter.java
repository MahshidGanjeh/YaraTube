package com.yaratech.yaratube.home.mainpage;

import com.yaratech.yaratube.data.util.HomeItemApiResult;
import com.yaratech.yaratube.data.HomeItemRepo;
import com.yaratech.yaratube.data.model.HomeItem;

import java.util.List;

public class MainPagePresenter implements MainPageContract.Presenter, HomeItemApiResult {

    private MainPageContract.View mView;
    private HomeItemRepo homeItemRepo;

    public MainPagePresenter(MainPageContract.View mView) {
        this.mView = mView;
        homeItemRepo = new HomeItemRepo(this);
    }

    @Override
    public void onSuccess(List<HomeItem> list) {
        mView.showHomeItems(list);
    }

    @Override
    public void onFail() {
        // Toast.makeText(,"مشکل اتصال به اینترنت"   , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadHomeItems() {
        homeItemRepo.fetchCategory();
    }
}