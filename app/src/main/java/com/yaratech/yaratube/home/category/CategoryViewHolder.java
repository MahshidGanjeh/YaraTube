package com.yaratech.yaratube.home.category;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Category;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private ImageView avatar;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.cat_title_tv);
        avatar = itemView.findViewById(R.id.cat_avatar_imgView);
    }

    public void onBind(Category item){
        title.setText(item.getTitle());
        Glide.with(itemView.getContext()).load(item.getAvatar())
                .into(avatar);
    }
}
