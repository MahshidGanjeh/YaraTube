package com.yaratech.yaratube.login;

import android.content.Context;

import com.yaratech.yaratube.data.model.Profile;
import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.WebService;
import com.yaratech.yaratube.data.source.remote.RemoteDataSource;

public class ProfilePresenter implements ProfileContract.Presenter {

    private Repository mProfileRepository;
    private ProfileContract.View mView;

    public ProfilePresenter(ProfileContract.View mView, Context context) {
        mProfileRepository = new Repository(new RemoteDataSource(context));
        this.mView = mView;
    }

    @Override
    public void sendProfileFields(String name, String gender, String birthday, String token) {
        mProfileRepository.postProfileFields(name, gender, birthday, token,
                new WebService.ApiResultCallBack() {
                    @Override
                    public void onSuccess(Object response) {
                        mView.showProfileFields((Profile) response);
                    }

                    @Override
                    public void onFail(Object message) {

                    }
                });
    }
}
