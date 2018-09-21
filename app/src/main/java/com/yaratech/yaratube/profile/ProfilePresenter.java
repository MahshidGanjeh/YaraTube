package com.yaratech.yaratube.profile;

import android.content.Context;

import com.yaratech.yaratube.data.model.Profile;
import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.WebService;
import com.yaratech.yaratube.data.source.remote.RemoteDataSource;
import com.yaratech.yaratube.profile.ProfileContract;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfilePresenter implements ProfileContract.Presenter {

    private Repository mProfileRepository;
    private ProfileContract.View mView;

    public ProfilePresenter(ProfileContract.View mView, Context context) {
        RemoteDataSource rm = new RemoteDataSource(context);
        mProfileRepository = new Repository(rm);
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
                        mView.showError((String) message);
                    }
                });
    }

    @Override
    public void uploadProfilePhoto(String filePath, String token) {

        File file = new File(filePath);
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("avatar",
                file.getName(), reqFile);

        mProfileRepository.uploadProfileImage(body, token,
                new WebService.ApiResultCallBack() {
                    @Override
                    public void onSuccess(Object response) {

                    }

                    @Override
                    public void onFail(Object message) {

                    }
                });
    }
}
