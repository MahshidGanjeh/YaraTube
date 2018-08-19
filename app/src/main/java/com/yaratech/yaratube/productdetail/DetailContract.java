package com.yaratech.yaratube.productdetail;

import com.yaratech.yaratube.data.model.DetailedProduct;

public class DetailContract {

    interface View{
        void showDetail(DetailedProduct product);
    }

    interface Presenter{
        void loadDetail(int pid);
    }
}
