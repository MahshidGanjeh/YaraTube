package com.yaratech.yaratube.gridproduct;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.util.Listener;

import java.util.ArrayList;
import java.util.List;

public class GridProductAdapter extends RecyclerView.Adapter<GridProductViewHolder> {

    private List<Product> mProductList = new ArrayList<>();
    private Listener.onProductClickListener mProductClickListener;
    private Context context;

    public GridProductAdapter(Context context, Listener.onProductClickListener listener) {
        this.context = context;
        mProductClickListener = listener;
    }

    @NonNull
    @Override
    public GridProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_product_item, parent, false);
        GridProductViewHolder viewHolder = new GridProductViewHolder(rootView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GridProductViewHolder holder, final int position) {
        holder.onBind(context, mProductList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProductClickListener.goToProductDetail(mProductList.get(position).getId());
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
