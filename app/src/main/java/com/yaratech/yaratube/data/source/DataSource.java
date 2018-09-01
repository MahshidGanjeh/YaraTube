package com.yaratech.yaratube.data.source;

import com.yaratech.yaratube.data.source.local.UserDatabase;

public interface DataSource {

    void fetchHomeItems(WebService.ApiResultCallBack callBack);

    void fetchCategory(WebService.ApiResultCallBack callBack);

    void fetchProductsByCategoryId(WebService.ApiResultCallBack callBack, int cid, int offset);

    void fetchCommentByProductId(WebService.ApiResultCallBack callBack, int pid);

    void fetchDetailedProduct(WebService.ApiResultCallBack callBack, int pid);

    void postPhoneNumber(WebService.ApiResultCallBack callBack,
                         String phoneNumber, String device_id, String device_model,
                         String device_os);

    void postVerificationCode(WebService.ApiResultCallBack callBack,
                              String phoneNumber, String device_id, String verificationCode,
                              String nickName);

    boolean isLogin(UserDatabase db);

    void postComment(WebService.ApiResultCallBack callBack,
                     String title, int score, String commentText, int productId,
                     String token);
}
