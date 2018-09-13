package com.yaratech.yaratube.productdetail;

import android.content.Context;
import android.util.Log;

import com.yaratech.yaratube.data.model.DetailedProduct;
import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.WebService;
import com.yaratech.yaratube.data.source.remote.RemoteDataSource;

public class DetailPresenter implements DetailContract.Presenter {

    private Repository repository;
    private DetailContract.View mView;

    public DetailPresenter(DetailContract.View mView, Context context) {
        this.mView = mView;
        repository = new Repository(new RemoteDataSource(context));
    }

    @Override
    public void loadDetail(int pid) {
        mView.showProgressbar();
        repository.fetchDetailedProduct(new WebService.ApiResultCallBack() {

            @Override
            public void onSuccess(Object response) {
                mView.showDetail((DetailedProduct) response);
                mView.hideProgressbar();
            }

            @Override
            public void onFail(Object message) {

            }
        }, pid);
    }
}
