package com.yaratech.yaratube.login.mobilelogin;

public interface PhoneNumberContract {


    interface View{

    }
    interface Presenter {
        void postPhoneNumber(String phoneNumber, String id,
                     String deviceModel, String os);
    }
}
