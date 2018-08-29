package com.yaratech.yaratube.productdetail.commentdialog;

import android.content.Context;

import com.yaratech.yaratube.data.source.WebService;
import com.yaratech.yaratube.data.source.remote.RemoteDataSource;

public class CommentPresenter implements CommentContract.Presenter {

    private RemoteDataSource mRemoteDataSource;
    private CommentContract.View mView;

    public CommentPresenter(CommentContract.View view, Context context) {
        mRemoteDataSource = new RemoteDataSource(context);
        mView = view;
    }

    @Override
    public void sendComment(String title, int score, String text,
                            int productId, String token) {
        mRemoteDataSource.postComment(new WebService.ApiResultCallBack() {
            @Override
            public void onSuccess(Object response) {
                mView.show();
            }

            @Override
            public void onFail(Object message) {

            }
        }, title, score, text, productId, token);

    }
}
