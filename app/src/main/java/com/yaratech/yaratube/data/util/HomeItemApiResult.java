package com.yaratech.yaratube.data.util;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.HomeItem;

import java.util.List;

public interface HomeItemApiResult {

    void onSuccess(List<HomeItem> list);

    void onFail();
}
