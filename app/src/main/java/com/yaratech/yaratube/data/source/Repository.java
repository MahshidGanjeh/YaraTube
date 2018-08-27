package com.yaratech.yaratube.data.source;

import com.yaratech.yaratube.data.source.local.LocalDataSource;
import com.yaratech.yaratube.data.source.local.UserDatabase;
import com.yaratech.yaratube.data.source.remote.RemoteDataSource;

public class Repository implements DataSource {

    private RemoteDataSource mRemoteDataSource;
    private LocalDataSource mLocalDataSource;

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

    @Override
    public boolean isLogin(UserDatabase db) {
        return mLocalDataSource.isLogin(db);
    }
}
