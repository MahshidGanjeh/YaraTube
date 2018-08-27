package com.yaratech.yaratube.data.source;

import com.yaratech.yaratube.data.source.local.UserDatabase;

public interface DataSource {

    void fetchHomeItems(WebService.ApiResultCallBack callBack);

    void fetchCategory(WebService.ApiResultCallBack callBack);

    void fetchProductsByCategoryId(WebService.ApiResultCallBack callBack, int cid);

    void fetchCommentByProductId(WebService.ApiResultCallBack callBack, int pid);

    void fetchDetailedProduct(WebService.ApiResultCallBack callBack, int pid);

    void postPhoneNumber(WebService.ApiResultCallBack callBack,
                         String phoneNumber, String device_id, String device_model,
                         String device_os);

    void postVerificationCode(WebService.ApiResultCallBack callBack,
                              String phoneNumber, String device_id, String verificationCode,
                              String nickName);

    boolean isLogin(UserDatabase db);
}
