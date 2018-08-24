package com.yaratech.yaratube.home.store;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.util.onProductClickListener;

import java.util.ArrayList;
import java.util.List;

public class HomeItemProductAdapter extends RecyclerView.Adapter<HomeItemProductViewHolder> {

    private List<Product> mProductList = new ArrayList<>();
    private Context context;
    private onProductClickListener mOnProductClcikedListener;

    public HomeItemProductAdapter(Context context, onProductClickListener listener) {
        this.context = context;
        mOnProductClcikedListener = listener;
    }

    @NonNull
    @Override
    public HomeItemProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item, parent, false);
        HomeItemProductViewHolder viewHolder = new HomeItemProductViewHolder(rootView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeItemProductViewHolder holder, final int position) {
        holder.onBind(context, mProductList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnProductClcikedListener.goToProductDetail(
                        mProductList.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public void setProductList(List<Product> mProductList) {
        this.mProductList = mProductList;
        notifyDataSetChanged();
    }
}
