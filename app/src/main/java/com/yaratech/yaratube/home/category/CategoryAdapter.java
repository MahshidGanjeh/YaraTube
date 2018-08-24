package com.yaratech.yaratube.home.category;

import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.util.onCategoryClickListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private List<Category> mCategoryList = new ArrayList<>();
    private onCategoryClickListener mCategoryClickListener;
    FragmentManager manager;
    private Context context;

    public CategoryAdapter(Context context, onCategoryClickListener listener, FragmentManager fm) {
        mCategoryClickListener = listener;
        manager = fm;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.category_item, parent, false);

        CategoryViewHolder viewHolder = new CategoryViewHolder(rootView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position) {
        holder.onBind(mCategoryList.get(position), context);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCategoryClickListener.onCategoryClicked(mCategoryList.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    public void setCategoryList(List<Category> mCategoryList) {
        notifyDataSetChanged();
        this.mCategoryList = mCategoryList;
    }
}
