package com.yaratech.yaratube.data.source;

import android.util.Log;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.DetailedProduct;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.data.source.remote.ApiService;
import com.yaratech.yaratube.data.source.remote.ApiClient;
import com.yaratech.yaratube.data.source.remote.RemoteDataSource;

import java.util.List;
import java.util.zip.CheckedOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository implements DataSource {

    private RemoteDataSource mRemoteDataSource;

    public Repository(DataSource dataSource) {
        mRemoteDataSource = (RemoteDataSource) dataSource;
    }

    public void fetchHomeItems(final WebService.ApiResultCallBack callBack) {
        mRemoteDataSource.fetchHomeItems(callBack);
    }

    public void fetchCategory(final WebService.ApiResultCallBack apiResultCallBack) {
        mRemoteDataSource.fetchCategory(apiResultCallBack);
    }

    @Override
    public void fetchProductsByCategoryId(WebService.ApiResultCallBack callBack, int cid) {
        mRemoteDataSource.fetchProductsByCategoryId(callBack, cid);
    }

    @Override
    public void fetchCommentByProductId(WebService.ApiResultCallBack callBack, int pid) {
        mRemoteDataSource.fetchCommentByProductId(callBack, pid);
    }

    @Override
    public void fetchDetailedProduct(WebService.ApiResultCallBack callBack, int pid) {
        mRemoteDataSource.fetchDetailedProduct(callBack, pid);
    }

    @Override
    public void postPhoneNumber(WebService.ApiResultCallBack callBack, String p
            , String id, String model, String os) {
        mRemoteDataSource.postPhoneNumber(callBack, p, id, model, os);
    }

    @Override
    public void postVerificationCode(WebService.ApiResultCallBack callBack,
                                     String phoneNumber, String device_id,
                                     String verificationCode, String nickName) {
        mRemoteDataSource.postVerificationCode(callBack, phoneNumber,
                device_id, verificationCode, nickName);

    }
}
