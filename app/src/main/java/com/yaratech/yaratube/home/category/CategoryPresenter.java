package com.yaratech.yaratube.home.category;

import com.yaratech.yaratube.data.ApiResult;
import com.yaratech.yaratube.data.CategoryRepo;
import com.yaratech.yaratube.data.model.Category;

import java.util.List;

public class CategoryPresenter implements CategoryContract.Presenter, ApiResult {

    private CategoryContract.View mView;
    private CategoryRepo categoryRepo;

    public CategoryPresenter(CategoryContract.View mView) {
        this.mView = mView;
        categoryRepo = new CategoryRepo(this);
    }

    @Override

    public void loadCategories() {
        categoryRepo.fetchCategory();
    }

    @Override
    public void onSuccess(List<Category> list) {
        mView.showCategories(list);
    }

    @Override
    public void onFail() {
        // Toast.makeText(,"مشکل اتصال به اینترنت"   , Toast.LENGTH_SHORT).show();
    }
}
