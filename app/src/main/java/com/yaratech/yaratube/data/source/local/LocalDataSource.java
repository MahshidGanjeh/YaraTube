package com.yaratech.yaratube.data.source.local;

import android.content.Context;

import com.yaratech.yaratube.data.source.DataSource;
import com.yaratech.yaratube.data.source.WebService;

public class LocalDataSource implements DataSource {

    private Context mContext;

    public LocalDataSource(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void fetchHomeItems(WebService.ApiResultCallBack callBack) {

    }

    @Override
    public void fetchCategory(WebService.ApiResultCallBack callBack) {

    }

    @Override
    public void fetchProductsByCategoryId(WebService.ApiResultCallBack callBack, int cid) {

    }

    @Override
    public void fetchCommentByProductId(WebService.ApiResultCallBack callBack, int pid) {

    }

    @Override
    public void fetchDetailedProduct(WebService.ApiResultCallBack callBack, int pid) {
    }

    @Override
    public void postPhoneNumber(WebService.ApiResultCallBack callBack, String phoneNumber, String device_id, String device_model, String device_os) {

    }

    @Override
    public void postVerificationCode(WebService.ApiResultCallBack callBack, String phoneNumber, String device_id, String verificationCode, String nickName) {

    }

    @Override
    public boolean isLogin(UserDatabase db) {
        if (db.userDao().getUserTokenFromDatabase() == null) {
            return false;
        } else return true;
    }


}
