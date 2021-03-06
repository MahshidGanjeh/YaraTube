package com.yaratech.yaratube.gridproduct;

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
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
    // flag for footer ProgressBar
    private boolean isLoadingAdded;

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

    public void add(Product products) {
        mProductList.add(products);
        notifyItemInserted(mProductList.size() - 1);
    }

    public void addAll(List<Product> productsList) {
        for (Product product : productsList) {
            add(product);
            //notifyDataSetChanged();
        }
    }

    public void updateProductsWithDiffUtil(List<Product> products) {
        List<Product> newList = new ArrayList<>(mProductList);
        newList.addAll(products);
        final ProductDiffCallback diffCallback =
                new ProductDiffCallback(mProductList, newList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        diffResult.dispatchUpdatesTo(this);
        this.mProductList.addAll(products);
    }

    public void remove(Product product) {
        int position = mProductList.indexOf(product);
        if (position > -1) {
            mProductList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = mProductList.size() - 1;
        Product item = getItem(position);

        if (item != null) {
            mProductList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Product getItem(int position) {
        return mProductList.get(position);
    }
}
