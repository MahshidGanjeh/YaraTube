package com.yaratech.yaratube.home.store;

import com.yaratech.yaratube.data.model.Store;

public interface StoreContract {

    interface View{
        void showHomeItems(Store store);
    }

    interface Presenter {
        void loadHomeItems();
    }
}
