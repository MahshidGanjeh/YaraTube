package com.yaratech.yaratube.data;


import android.util.Log;

import com.yaratech.yaratube.data.ApiResult;
import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.remote.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepo {
    private ApiResult mApiResultListener;

    public CategoryRepo(ApiResult mApiResultListener) {
        this.mApiResultListener = mApiResultListener;
    }

    public static void fetchCategory() {
        RetrofitClient.getClient().create(ApiService.class).getCategories()
                .enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                        if (response.isSuccessful()) {
                            Log.i("tt", "onResponse: " + response.body().get(0).getTitle());
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
