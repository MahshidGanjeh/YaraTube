package com.yaratech.yaratube.login.mobilelogin;

import com.yaratech.yaratube.data.source.local.UserDatabase;

public interface LoginContract {

    interface View {
        void show(String token);
    }

    interface CodePresenter {
        void presentVerificationCode(String phoneNumber, String id,
                                     String verificationCode, String nickName);
    }
}
