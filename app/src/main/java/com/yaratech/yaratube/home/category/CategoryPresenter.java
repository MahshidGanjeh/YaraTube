package com.yaratech.yaratube.home.category;

import android.content.Context;

import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.WebService;
import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.source.remote.RemoteDataSource;

import java.util.List;

public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryContract.View mView;
    private Repository categoryRepo;
    private Context mContext;

    public CategoryPresenter(CategoryContract.View mView , Context context) {
        this.mView = mView;
        categoryRepo = new Repository(new RemoteDataSource(context));
    }

    @Override
    public void loadCategories() {
        categoryRepo.fetchCategory(new WebService.ApiResultCallBack() {
            @Override
            public void onSuccess(Object response) {
                mView.showProgress();
                mView.showCategories((List<Category>) response);
                mView.hideProgress();
            }

            @Override
            public void onFail(Object message) {

            }
        });
    }
}
