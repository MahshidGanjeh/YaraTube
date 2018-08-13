package com.yaratech.yaratube.home.store;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Product;

public class HomeItemProductViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private ImageView imageView;
    private TextView description;
    public static final String BASE_URL = "https://api.vasapi.click/";

    public HomeItemProductViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.home_item_name_tv);
        description = itemView.findViewById(R.id.home_item_des_tv);
        imageView = itemView.findViewById(R.id.home_item_avatar_imgView);
    }

    public void onBind(Context context, Product product) {
        title.setText(product.getName());
        description.setText(product.getShortDescription());
        Glide.with(context).load(BASE_URL+product.getAvatar().getHdpi())
                .into(imageView);

    }
}
