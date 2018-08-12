package com.yaratech.yaratube.home.mainpage;

import com.yaratech.yaratube.data.model.HomeItem;

import java.util.List;

public interface MainPageContract {

    interface View{
        void showHomeItems(List<HomeItem> list);
    }
    interface Presenter {
        void loadHomeItems();
    }
}
