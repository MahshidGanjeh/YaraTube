package com.yaratech.yaratube.util;

public interface Listener {

    interface onConfirmVerificationCodeListener {
        void saveTokenToDatabase(String token);
    }

    interface onCategoryClickListener {
        void onCategoryClicked(int categoryId);
    }

    interface onConfirmPhoneNumberListener {
        void goToVerificationDialog(String phoneNumber);
    }

    interface onMobileBtnClickListener {
        void goToPhoneNumberDialog();
    }

    interface onProductClickListener {
        void goToProductDetail(int pid);
    }

    interface onProfileClickListener{
        void goToProfile();
    }
}
