package com.yaratech.yaratube.productdetail.commentdialog;

import com.yaratech.yaratube.data.model.Comment;

import java.util.List;

public interface CommentContract {

    interface View {
        void show();
    }

    interface Presenter {
        void sendComment(String title,
                         int score,
                         String Text,
                         int productId,
                         String token);
    }
}
