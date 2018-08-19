package com.yaratech.yaratube.productdetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    private List<Comment> mCommentList;

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        CommentViewHolder vh = new CommentViewHolder(rootView);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.onBind(mCommentList.get(position));
        if (mCommentList != null) {
            Log.d("hey", mCommentList.get(0).getCommentText());
        }
    }

    @Override
    public int getItemCount() {
        if (mCommentList != null) {
            return mCommentList.size();
        }
        return 0;
    }

    public void setCommentList(List<Comment> CommentList) {
        notifyDataSetChanged();
        this.mCommentList = CommentList;
    }
}
