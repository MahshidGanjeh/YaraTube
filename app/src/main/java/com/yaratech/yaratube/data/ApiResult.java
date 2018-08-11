package com.yaratech.yaratube.data;

import java.util.List;
import com.yaratech.yaratube.data.model.Category;

public interface ApiResult {

    void onSuccess(List<Category> list);

    void onFail();
}
