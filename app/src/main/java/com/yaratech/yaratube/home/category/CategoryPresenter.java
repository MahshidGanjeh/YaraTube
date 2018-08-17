package com.yaratech.yaratube.home.category;

import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.WebService;
import com.yaratech.yaratube.data.model.Category;

import java.util.List;

public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryContract.View mView;
    private Repository categoryRepo;

    public CategoryPresenter(CategoryContract.View mView) {
        this.mView = mView;
        categoryRepo = new Repository();
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
