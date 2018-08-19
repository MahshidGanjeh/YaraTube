package com.yaratech.yaratube.productdetail;

import android.content.Context;

import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.WebService;
import com.yaratech.yaratube.data.source.remote.RemoteDataSource;

import java.util.List;

public class CommentPresenter implements CommentContract.Presenter {

    private Repository commentRepo;
    private CommentContract.View mView;

    public CommentPresenter(CommentContract.View mView, Context context) {
        commentRepo = new Repository(new RemoteDataSource(context));
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
