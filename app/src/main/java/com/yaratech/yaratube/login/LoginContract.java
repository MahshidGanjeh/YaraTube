package com.yaratech.yaratube.login;

import com.yaratech.yaratube.data.source.local.UserDatabase;

public interface LoginContract {

    interface View {
        void show(String token);
    }

    interface PhoneNumberPresenter {
        void present(String phoneNumber, String id,
                     String deviceModel, String os);
    }

    interface CodePresenter {
        void presentVerificationCode(String phoneNumber, String id,
                                     String verificationCode, String nickName);
    }
}
