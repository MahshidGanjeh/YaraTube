package com.yaratech.yaratube.data.source;

public interface DataSource {

    void fetchHomeItems(WebService.ApiResultCallBack callBack);

    void fetchCategory(WebService.ApiResultCallBack callBack);

    void fetchProductsByCategoryId(WebService.ApiResultCallBack callBack , int cid);

    void fetchCommentByProductId(WebService.ApiResultCallBack callBack , int cmid);

    void fetchDetailedProduct(WebService.ApiResultCallBack callBack , int pid);
}
