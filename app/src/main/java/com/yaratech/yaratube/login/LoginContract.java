package com.yaratech.yaratube.login;

public interface LoginContract {

    interface View{
        void show();
    }

    interface Presenter{
        void present(String phoneNumber,String id,
                     String deviceModel, String os );
    }
}
