package com.yaratech.yaratube.data.util;

import com.yaratech.yaratube.data.model.Store;

public interface StoreApiResult {

    void onSuccess(Store list);

    void onFail(String errorMessage);
}
