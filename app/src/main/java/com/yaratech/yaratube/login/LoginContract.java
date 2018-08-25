package com.yaratech.yaratube.login;

public interface LoginContract {

    interface View {
        void show(String s);
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
