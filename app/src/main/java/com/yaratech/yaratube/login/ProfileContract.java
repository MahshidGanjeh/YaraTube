package com.yaratech.yaratube.login;

import com.yaratech.yaratube.data.model.Profile;

public interface ProfileContract {

    interface View {

        void showProfileFields(Profile profile);

        void showError(String error);

    }

    interface Presenter {

        void sendProfileFields(String name, String gender, String birthday, String token);

    }
}
