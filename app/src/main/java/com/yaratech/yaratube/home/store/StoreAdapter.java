package com.yaratech.yaratube.home.store;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Store;

public class StoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HOMEITEM_VIEWTYPE = 1;
    private static final int HEADERITEM_VIEWTYPE = 0;
    private Store mStore = new Store();
    private Context context;

    public StoreAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADERITEM_VIEWTYPE:
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.header_item, parent, false);
                HeaderViewHolder hViewHolder = new HeaderViewHolder(view);
                return hViewHolder;
            case HOMEITEM_VIEWTYPE:
                View rootView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.home_item_row, parent, false);
                StoreViewHolder viewHolder = new StoreViewHolder(rootView);
                return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ( (HomeItemProductViewHolder) holder).onBind
                (mStore.getHomeitem().get(position), context, mStore.getHomeitem(), position);
    }

    @Override
    public int getItemCount() {
        if (mStore.getHomeitem() != null) return mStore.getHomeitem().size();
        else return 0;
    }

    public void setItemList(Store store) {
        notifyDataSetChanged();
        this.mStore = store;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADERITEM_VIEWTYPE;
        } else
            return HOMEITEM_VIEWTYPE;
    }
}
