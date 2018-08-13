package com.yaratech.yaratube.home.store;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Headeritem;
import com.yaratech.yaratube.data.model.Product;

public class HeaderViewHolder extends RecyclerView.ViewHolder {

    private ImageView header;
    public static final String BASE_URL = "https://api.vasapi.click/";

    public HeaderViewHolder(View itemView) {
        super(itemView);
        header = itemView.findViewById(R.id.header_imgView);
    }

    public void onBind(Headeritem headeritem,Context context){
        Glide.with(context).load(BASE_URL+headeritem.getAvatar())
                .into(header);
    }
}
