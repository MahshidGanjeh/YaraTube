package com.yaratech.yaratube.home.category;

import com.yaratech.yaratube.data.model.Category;

import java.util.List;

public interface CategoryContract {

    interface View{
        void showCategories(List<Category> list);
    }

    interface Presenter{
        void loadCategories();
    }
}
