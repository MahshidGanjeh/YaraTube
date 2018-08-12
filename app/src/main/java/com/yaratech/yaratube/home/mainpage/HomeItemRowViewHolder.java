package com.yaratech.yaratube.home.mainpage;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.HomeItem;

public class HomeItemRowViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private RecyclerView recyclerView;

    public HomeItemRowViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.home_item_row_title);
        recyclerView = itemView.findViewById(R.id.home_item_row_recycler);
    }

    public void onBind(HomeItem homeItem, Context context) {
        title.setText(homeItem.getTitle());
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        HomeItemProductAdapter adapter = new HomeItemProductAdapter(context);
        recyclerView.setAdapter(adapter);
    }
}
