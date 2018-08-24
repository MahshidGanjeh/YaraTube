package com.yaratech.yaratube.login;

import android.content.Context;

import com.yaratech.yaratube.data.source.WebService;
import com.yaratech.yaratube.data.source.remote.RemoteDataSource;

public class LoginPresenter implements LoginContract.Presenter {

    private RemoteDataSource mRemoteDataSource;
    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View mView, Context context) {
        mRemoteDataSource = new RemoteDataSource(context);
        this.mView = mView;
    }


    @Override
    public void present(String phoneNumber, String id, String deviceModel, String os) {
        mRemoteDataSource.postPhoneNumber(new WebService.ApiResultCallBack() {
            @Override
            public void onSuccess(Object response) {

            }

            @Override
            public void onFail(Object message) {

            }
        }, phoneNumber, id, deviceModel, os);
    }
}
