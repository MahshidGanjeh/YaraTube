package com.yaratech.yaratube.gridproduct;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.util.Listener;

import java.util.List;

public class GridProductFragment extends Fragment implements GridProductContract.View,
        Listener.onProductClickListener {

    private GridProductContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private GridProductAdapter adapter;
    private ProgressBar mProgressBar;
    private Listener.onProductClickListener mOnProductClickListener;
    private static int mCategoryId;

    public GridProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grid_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new GridProductPresenter(this, getActivity().getApplicationContext());

        mProgressBar = view.findViewById(R.id.grid_product_progress_bar);
        mRecyclerView = view.findViewById(R.id.product_of_category_recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new GridProductAdapter(getContext(), mOnProductClickListener);

        mRecyclerView.setAdapter(adapter);

        mPresenter.loadProducts(mCategoryId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mOnProductClickListener = (Listener.onProductClickListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoryId = getArguments().getInt("categoryId");

    }

    public static GridProductFragment newInstance(int categoryId) {

        Bundle args = new Bundle();
        args.putInt("categoryId", categoryId);

        GridProductFragment fragment = new GridProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showProducts(List<Product> list) {
        adapter.setProductList(list);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void goToProductDetail(int pid) {
        mOnProductClickListener.goToProductDetail(pid);
    }
}
