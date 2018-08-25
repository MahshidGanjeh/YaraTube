package com.yaratech.yaratube.util;

public interface Listener {

    interface onConfirmVerificationCodeListener {
        void saveTokenToDatabase(String token);
    }

    interface onCategoryClickListener {
        void onCategoryClicked(int categoryId);
    }

    interface onConfirmBtnClickListener {
        void goToVerificationDialog(String phoneNumber);
    }

    interface onPhoneNumberBtnListener {
        void goToPhoneNumberDialog();
    }

    interface onProductClickListener {
        void goToProductDetail(int pid);

    }
}
