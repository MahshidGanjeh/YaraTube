package com.yaratech.yaratube.data.source.remote;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.DetailedProduct;
import com.yaratech.yaratube.data.model.Login;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.data.model.User;
import com.yaratech.yaratube.data.source.DataSource;
import com.yaratech.yaratube.data.source.WebService;
import com.yaratech.yaratube.data.source.local.UserDatabase;
import com.yaratech.yaratube.util.Network;

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

    public void toastInternetConnection(Context context) {
        Toast.makeText(context, R.string.checking_internet, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fetchHomeItems(final WebService.ApiResultCallBack listener) {
        if (Network.isOnline(mContext)) {
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
        } else toastInternetConnection(mContext);

    }

    @Override
    public void fetchCategory(final WebService.ApiResultCallBack callBack) {
        if (Network.isOnline(mContext)) {
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
        } else toastInternetConnection(mContext);
    }

    @Override
    public void fetchProductsByCategoryId(
            final WebService.ApiResultCallBack apiResultCallBack, int cid) {
        if (Network.isOnline(mContext)) {
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
        } else toastInternetConnection(mContext);
    }

    @Override
    public void fetchCommentByProductId(final WebService.ApiResultCallBack apiResultCallBack,
                                        int pid) {
        if (Network.isOnline(mContext)) {
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
        } else toastInternetConnection(mContext);
    }

    @Override
    public void fetchDetailedProduct(final WebService.ApiResultCallBack apiResultCallBack,
                                     int pid) {
        if (Network.isOnline(mContext)) {
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
        } else toastInternetConnection(mContext);
    }

    @Override
    public void postPhoneNumber(final WebService.ApiResultCallBack callBack,
                                String phoneNumber, String device_id, String device_model,
                                String device_os) {
        if (Network.isOnline(mContext)) {
            mApiService.postPhoneNumber(phoneNumber, device_id, device_model, device_os).enqueue(new Callback<Login>() {
                                                                                                     @Override public void onResponse(Call<Login> call, Response<Login> response) {
                                                                                                         if (response.isSuccessful()) {
                                                                                                             callBack.onSuccess(response.body());
                                                                                                             Log.d("you receive sms", "sms sent" + response.body().getNickname());
                                                                                                         } else if (response.code() == 400) {
                                                                                                             Log.d("404", "Store id is not valid");
                                                                                                         } else if (response.code() == 406) {
                                                                                                             Log.d("406", "incomplete parameters");
                                                                                                         } else if (response.code() == 504) {
                                                                                                             Log.d("504", "sms send failed");
                                                                                                         }
                                                                                                     }

                                                                                                     @Override
                                                                                                     public void onFailure(Call<Login> call, Throwable t) {
                                                                                                         callBack.onFail(t.getMessage());
                                                                                                     }
                                                                                                 }
            );
        } else toastInternetConnection(mContext);
    }

    @Override
    public void
    postVerificationCode(final WebService.ApiResultCallBack callBack,
                         String phoneNumber, String device_id,
                         String verificationCode, String nickName) {

        if (Network.isOnline(mContext)) {
            mApiService.postVerificationCode(phoneNumber, device_id, verificationCode, nickName)
                    .enqueue(new Callback<User>() {
                                 @Override
                                 public void onResponse(Call<User> call, Response<User> response) {
                                     if (response.isSuccessful()) {
                                         callBack.onSuccess(response.body());
                                         Toast.makeText(mContext, response.code(), Toast.LENGTH_SHORT).show();
                                         Log.d("code posted", "code posted");
                                     } else if (response.code() == 401) {
                                         Log.d("401", "mobile number is not valid");
                                     } else if (response.code() == 406) {
                                         Log.d("406", "incomplete parameters");
                                     }
                                 }

                                 @Override
                                 public void onFailure(Call<User> call, Throwable t) {
                                     callBack.onFail(t.getMessage());
                                     Log.d("errrr" , t.getMessage());
                                 }
                             }
                    );
        } else toastInternetConnection(mContext);

    }

    @Override
    public Boolean isLogin(UserDatabase db) {
        return null;
    }
}

