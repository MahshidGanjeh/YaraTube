package com.yaratech.yaratube.data;


import android.util.Log;

import com.yaratech.yaratube.data.util.CategoryApiResult;
import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.remote.ApiService;
import com.yaratech.yaratube.data.remote.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepo {
    private CategoryApiResult mApiResultListener;

    public CategoryRepo(CategoryApiResult mApiResultListener) {
        this.mApiResultListener = mApiResultListener;
    }

    public void fetchCategory() {
        RetrofitClient.getClient().create(ApiService.class).getCategories()
                .enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                        if (response.isSuccessful()) {
                            mApiResultListener.onSuccess(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t) {
                        mApiResultListener.onFail();
                    }
                });
    }
}
