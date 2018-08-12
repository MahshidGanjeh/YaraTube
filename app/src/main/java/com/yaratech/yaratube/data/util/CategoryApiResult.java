package com.yaratech.yaratube.data.util;

import java.util.List;
import com.yaratech.yaratube.data.model.Category;

public interface CategoryApiResult {

    void onSuccess(List<Category> list);

    void onFail();
}
