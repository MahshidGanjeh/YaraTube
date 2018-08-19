package com.yaratech.yaratube.productdetail;

import com.yaratech.yaratube.data.model.DetailedProduct;

public class DetailContract {

    interface View{

        void showProgressbar();

        void showDetail(DetailedProduct product);

        void hideProgressbar();
    }

    interface Presenter{
        void loadDetail(int pid);
    }
}
