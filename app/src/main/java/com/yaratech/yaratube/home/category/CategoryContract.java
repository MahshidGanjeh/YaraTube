package com.yaratech.yaratube.home.category;

public interface CategoryContract {

    interface View{
        void showCategories();
    }

    interface Presenter{
        void loadCategories();
    }
}
