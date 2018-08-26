package com.yaratech.yaratube.login.mobilelogin;

import android.content.Context;
import android.util.Log;

import com.yaratech.yaratube.data.model.Login;
import com.yaratech.yaratube.data.model.User;
import com.yaratech.yaratube.data.source.WebService;
import com.yaratech.yaratube.data.source.remote.RemoteDataSource;
import com.yaratech.yaratube.login.mobilelogin.LoginContract;

public class LoginPresenter implements LoginContract.CodePresenter {

    private RemoteDataSource mRemoteDataSource;
    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View mView, Context context) {
        mRemoteDataSource = new RemoteDataSource(context);
        this.mView = mView;
    }


    @Override
    public void presentVerificationCode(String phoneNumber, String id,
                                        String verificationCode, String nickName) {

        mRemoteDataSource.postVerificationCode(new WebService.ApiResultCallBack() {
            @Override
            public void onSuccess(Object response) {
                mView.show(((User) response).getToken());
            }

            @Override
            public void onFail(Object message) {

            }
        }, phoneNumber, id, verificationCode, nickName);

    }
}
