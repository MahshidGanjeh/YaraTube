package com.yaratech.yaratube.productdetail;

import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.WebService;

import java.util.List;

public class CommentPresenter implements CommentContract.Presenter {

    private Repository commentRepo;
    private CommentContract.View mView;

    public CommentPresenter(CommentContract.View mView) {
        commentRepo = new Repository();
        this.mView = mView;
    }

    @Override
    public void loadComments(int productId) {
        commentRepo.fetchCommentByProductId(new WebService.ApiResultCallBack() {
            @Override
            public void onSuccess(Object response) {
                mView.showComments((List) response);
            }

            @Override
            public void onFail(Object message) {

            }
        }, productId);
    }
}
