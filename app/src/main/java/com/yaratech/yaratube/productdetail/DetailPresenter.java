package com.yaratech.yaratube.productdetail;

import com.yaratech.yaratube.data.model.DetailedProduct;
import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.WebService;

public class DetailPresenter implements DetailContract.Presenter {

    private Repository repository;
    private DetailContract.View mView;

    public DetailPresenter(DetailContract.View mView) {
        this.mView = mView;
        repository = new Repository();
    }

    @Override
    public void loadDetail(int pid) {
        repository.fetchDetailedProduct(new WebService.ApiResultCallBack() {
            @Override
            public void onSuccess(Object response) {
                mView.showDetail((DetailedProduct) response);
            }

            @Override
            public void onFail(Object message) {

            }
        }, pid);
    }
}
