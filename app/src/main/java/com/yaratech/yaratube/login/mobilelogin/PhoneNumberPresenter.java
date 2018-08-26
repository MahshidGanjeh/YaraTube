package com.yaratech.yaratube.login.mobilelogin;

import android.content.Context;
import android.util.Log;

import com.yaratech.yaratube.data.model.Login;
import com.yaratech.yaratube.data.source.WebService;
import com.yaratech.yaratube.data.source.remote.RemoteDataSource;

public class PhoneNumberPresenter implements PhoneNumberContract.Presenter {

    private RemoteDataSource mRemoteDataSource;
    private PhoneNumberContract.View mView;
    private Context mContext;

    public PhoneNumberPresenter(Context context) {
        this.mRemoteDataSource = new RemoteDataSource(context);
    }

    @Override
    public void postPhoneNumber(String phoneNumber, String id, String deviceModel, String os) {

        mRemoteDataSource.postPhoneNumber(new WebService.ApiResultCallBack() {
            @Override
            public void onSuccess(Object response) {
                Log.d("succes", ((Login) response).getMessage());
            }

            @Override
            public void onFail(Object message) {

            }
        }, phoneNumber, id, deviceModel, os);

    }
}