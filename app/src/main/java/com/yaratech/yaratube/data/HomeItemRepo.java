package com.yaratech.yaratube.data;

import android.util.Log;

import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.data.remote.ApiService;
import com.yaratech.yaratube.data.remote.RetrofitClient;
import com.yaratech.yaratube.data.util.StoreApiResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeItemRepo {

    private StoreApiResult listener;

    public HomeItemRepo(StoreApiResult listener) {
        this.listener = listener;
    }

    public void fetchHomeItems() {
        RetrofitClient.getClient().create(ApiService.class).getStore()
                .enqueue(new Callback<Store>() {
                    @Override
                    public void onResponse(Call<Store> call, Response<Store> response) {
                        if (response.isSuccessful()) {
                            listener.onSuccess(response.body());
                        }else {
                            listener.onFail(response.message());
                        }
                    }
                    @Override
                    public void onFailure(Call<Store> call, Throwable t) {
                        listener.onFail(t.getMessage());
                        Log.i("fail", t.getMessage());
                    }
                });
    }
}
