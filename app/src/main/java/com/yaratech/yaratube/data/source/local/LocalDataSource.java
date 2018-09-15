package com.yaratech.yaratube.data.source.local;

import android.content.Context;

import com.yaratech.yaratube.data.source.DataSource;
import com.yaratech.yaratube.data.source.WebService;

import okhttp3.MultipartBody;

public class LocalDataSource implements DataSource {

    private Context mContext;

    public LocalDataSource(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void toastApiResponseFail(Context context, String message) {

    }

    @Override
    public void fetchHomeItems(WebService.ApiResultCallBack callBack) {

    }

    @Override
    public void fetchCategory(WebService.ApiResultCallBack callBack) {

    }

    @Override
    public void fetchProductsByCategoryId(WebService.ApiResultCallBack callBack, int cid
            , int offset) {

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

    @Override
    public void postComment(WebService.ApiResultCallBack callBack,
                            String title, int score,
                            String commentText, int productId, String token) {

    }

    @Override
    public void postGoogleLoginResult(String token, String deviceId, String deviceOs, String deviceModel,
                                      WebService.ApiResultCallBack callBack) {

    }

    @Override
    public void postProfileFields(String name, String gender, String birthday, String token,
                                  WebService.ApiResultCallBack callBack) {

    }

    @Override
    public void uploadProfileImage(MultipartBody.Part multipart, String token,
                                   WebService.ApiResultCallBack callBack) {

    }

}
