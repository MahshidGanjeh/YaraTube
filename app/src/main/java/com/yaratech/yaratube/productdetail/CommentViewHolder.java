package com.yaratech.yaratube.productdetail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Comment;

public class CommentViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private TextView text;

    public CommentViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.comment_title_tv);
        text = itemView.findViewById(R.id.comment_text_tv);
    }

    public void onBind(Comment comment) {
        title.setText(comment.getTitle());
        text.setText(comment.getCommentText());
    }
}
