package com.yaratech.yaratube.productdetail;

import com.yaratech.yaratube.data.model.Comment;

import java.util.List;

public interface CommentContract {

    interface View {
        void showComments(List<Comment> list);
    }

    interface Presenter {
        void loadComments(int productId);
    }
}
