package com.yaratech.yaratube.data.source.remote;

import android.content.Context;
import android.util.Log;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.DetailedProduct;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.data.source.DataSource;
import com.yaratech.yaratube.data.source.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource implements DataSource {

    private ApiService mApiService;
    private Context mContext;

    public RemoteDataSource(Context context) {
        mApiService = ApiClient.getClient().create(ApiService.class);
        mContext = context;
    }

    @Override
    public void fetchHomeItems(final WebService.ApiResultCallBack listener) {
        mApiService.getStore()
                .enqueue(new Callback<Store>() {
                    @Override
                    public void onResponse(Call<Store> call, Response<Store> response) {
                        if (response.isSuccessful()) {
                            listener.onSuccess(response.body());
                        } else {
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

    @Override
    public void fetchCategory(final WebService.ApiResultCallBack callBack) {
        mApiService.getCategories()
                .enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                        if (response.isSuccessful()) {
                            callBack.onSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t) {
                        callBack.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void fetchProductsByCategoryId(final WebService.ApiResultCallBack apiResultCallBack, int cid) {
        mApiService.getProductsByCategoryId(cid)
                .enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        if (response.isSuccessful()) {
                            apiResultCallBack.onSuccess(response.body());
                        } else apiResultCallBack.onFail(response.message());
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        apiResultCallBack.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void fetchCommentByProductId(final WebService.ApiResultCallBack apiResultCallBack, int pid) {
        mApiService.getProductCommentByProductId(pid)
                .enqueue(new Callback<List<Comment>>() {
                    @Override
                    public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                        if (response.isSuccessful()) {
                            apiResultCallBack.onSuccess(response.body());
                        } else apiResultCallBack.onFail(response.message());
                    }

                    @Override
                    public void onFailure(Call<List<Comment>> call, Throwable t) {
                        apiResultCallBack.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void fetchDetailedProduct(final WebService.ApiResultCallBack apiResultCallBack, int pid) {
        mApiService.getDetailedProductByProductId(pid)
                .enqueue(new Callback<DetailedProduct>() {
                    @Override
                    public void onResponse(Call<DetailedProduct> call, Response<DetailedProduct> response) {
                        if (response.isSuccessful()) {
                            apiResultCallBack.onSuccess(response.body());
                        }
                        apiResultCallBack.onFail(response.message());
                    }

                    @Override
                    public void onFailure(Call<DetailedProduct> call, Throwable t) {
                        apiResultCallBack.onFail(t.getMessage());
                    }
                });
    }
}

