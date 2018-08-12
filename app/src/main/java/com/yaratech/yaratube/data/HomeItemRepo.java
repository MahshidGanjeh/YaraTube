package com.yaratech.yaratube.data;

import android.util.Log;

import com.yaratech.yaratube.data.model.HomeItem;
import com.yaratech.yaratube.data.remote.ApiService;
import com.yaratech.yaratube.data.remote.RetrofitClient;
import com.yaratech.yaratube.data.util.HomeItemApiResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeItemRepo {

    private HomeItemApiResult listener;

    public HomeItemRepo(HomeItemApiResult listener) {
        this.listener = listener;
    }

    public void fetchHomeItems() {
        RetrofitClient.getClient().create(ApiService.class).getHomeItems()
                .enqueue(new Callback<List<HomeItem>>() {
                    @Override
                    public void onResponse(Call<List<HomeItem>> call, Response<List<HomeItem>> response) {
                        if (response.isSuccessful()) {
                            Log.i("homeitem", "onResponse: " + response.body().get(0).getTitle());
                            listener.onSuccess(response.body());
                            Log.d("hey" ,"we");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<HomeItem>> call, Throwable t) {
                        listener.onFail();
                        Log.i("fail",t.getMessage());
                    }
                });
    }
}
