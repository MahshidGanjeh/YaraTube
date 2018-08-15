package com.yaratech.yaratube.home.store;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Headeritem;

import java.util.List;

public class HeaderAdapter extends RecyclerView.Adapter<HeaderViewHolder> {

    private List<Headeritem> mHeaderList;
    private Context mContext;

    public HeaderAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public HeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.header_item, parent, false);

        HeaderViewHolder viewHolder = new HeaderViewHolder(rootView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HeaderViewHolder holder, int position) {
        holder.onBind(mHeaderList.get(position), mContext);
    }

    @Override
    public int getItemCount() {
        if (mHeaderList != null) {
            //Log.d("number" ,String.valueOf(mHeaderList.size()));
            return mHeaderList.size();
        } else
            return 0;
    }

    public void setHeaderList(List<Headeritem> mHeaderList) {
        this.mHeaderList = mHeaderList;
        notifyDataSetChanged();
    }
}
