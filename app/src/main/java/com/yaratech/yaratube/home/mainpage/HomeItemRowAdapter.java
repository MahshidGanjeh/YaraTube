package com.yaratech.yaratube.home.mainpage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.HomeItem;

import java.util.ArrayList;
import java.util.List;

public class HomeItemRowAdapter extends RecyclerView.Adapter<HomeItemRowViewHolder> {

    private List<HomeItem> itemList = new ArrayList<>();
    private Context context;

    public HomeItemRowAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HomeItemRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item_row, parent, false);
        HomeItemRowViewHolder viewHolder = new HomeItemRowViewHolder(rootView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeItemRowViewHolder holder, int position) {
        holder.onBind(itemList.get(position),context,itemList);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemList(List<HomeItem> itemList) {
        notifyDataSetChanged();
        this.itemList = itemList;
    }
}
