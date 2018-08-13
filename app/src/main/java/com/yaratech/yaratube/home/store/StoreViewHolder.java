package com.yaratech.yaratube.home.store;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.HomeItem;

import java.util.List;

public class StoreViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private RecyclerView recyclerView;

    public StoreViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.home_item_row_title);
        recyclerView = itemView.findViewById(R.id.home_item_row_recycler);
    }

    public void onBind(HomeItem homeItem, Context context, List<HomeItem> list ,int pos) {
        title.setText(homeItem.getTitle());
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        HomeItemProductAdapter adapter = new HomeItemProductAdapter(context);
        recyclerView.setAdapter(adapter);
        adapter.setProductList(list.get(pos).getProducts());
    }
}
