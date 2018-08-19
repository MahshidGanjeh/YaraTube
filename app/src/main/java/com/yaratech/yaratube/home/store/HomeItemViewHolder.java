package com.yaratech.yaratube.home.store;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.HomeItem;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.onProductClickListener;

import java.util.List;

public class HomeItemViewHolder extends RecyclerView.ViewHolder
        implements onProductClickListener {

    private TextView title;
    private RecyclerView recyclerView;
    private onProductClickListener listener;

    public HomeItemViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.home_item_row_title);
        recyclerView = itemView.findViewById(R.id.home_item_row_recycler);
    }

    public void onBind(HomeItem homeItem, Context context, List<HomeItem> list,
                       int pos, onProductClickListener listener) {
        this.listener = listener;
        title.setText(homeItem.getTitle());
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        HomeItemProductAdapter adapter = new HomeItemProductAdapter(context, this);
        recyclerView.setAdapter(adapter);
        adapter.setProductList(list.get(pos).getProducts());
    }

    @Override
    public void goToProductDetail(int p) {
        listener.goToProductDetail(p);
    }
}
