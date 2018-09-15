package com.yaratech.yaratube.login;

import com.yaratech.yaratube.data.model.Profile;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;

public interface ProfileContract {

    interface View {

        void showProfileFields(Profile profile);

        void showError(String error);

        void showProfilePhoto(String avatar);

    }

    interface Presenter {

        void sendProfileFields(String name, String gender, String birthday, String token);

        void uploadProfilePhoto(MultipartBody.Part multipartBody, String token);

    }
}
