package com.yaratech.yaratube.home.store;

import android.support.v4.app.FragmentManager;
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
    private Context mContext;
    private FragmentManager manager;

    public StoreAdapter(Context context, FragmentManager manager) {
        this.mContext = context;
        this.manager = manager;
    }

    public StoreAdapter(FragmentManager childFragmentManager) {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADERITEM_VIEWTYPE:
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.header_container, parent, false);
                HeaderContainerViewHolder hViewHolder = new HeaderContainerViewHolder(view);
                return hViewHolder;
            case HOMEITEM_VIEWTYPE:
                View rootView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.home_item_row, parent, false);
                HomeItemViewHolder viewHolder = new HomeItemViewHolder(rootView);
                return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderContainerViewHolder) {
            HeaderContainerViewHolder vh2 = (HeaderContainerViewHolder) holder;
            vh2.onBind(manager, mStore.getHeaderitem());

        } else if (holder instanceof HomeItemViewHolder) {
            HomeItemViewHolder vh = (HomeItemViewHolder) holder;
            vh.onBind(mStore.getHomeitem().get(position- 1), mContext,
                    mStore.getHomeitem(), position - 1);
        }
    }

    @Override
    public int getItemCount() {
        if (mStore.getHomeitem() != null && mStore.getHeaderitem() != null)
            return mStore.getHomeitem().size() + 1;
        else return 0;
    }

    public void setItemList(Store store) {
        this.mStore = store;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADERITEM_VIEWTYPE;
        } else
            return HOMEITEM_VIEWTYPE;
    }
}
