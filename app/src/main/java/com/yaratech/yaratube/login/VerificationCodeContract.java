package com.yaratech.yaratube.login;

public interface VerificationCodeContract {

     interface View {
        void saveTokenToDatabase(String token);
    }

    interface Presenter {
        void Present();
    }
}
