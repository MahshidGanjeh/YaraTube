package com.yaratech.yaratube.data.source;

public interface WebService {

    interface ApiResultCallBack<T> {

        void onSuccess(T response);

        void onFail(T message);

    }

}
