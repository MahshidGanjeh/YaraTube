package com.yaratech.yaratube.home.category.product;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.home.store.HomeItemProductAdapter;
import java.util.List;

public class ProductFragment extends Fragment implements ProductContract.View {

    private ProductContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private HomeItemProductAdapter adapter;
    private ProgressBar mProgressBar;

    public ProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new ProductPresenter(this);

        //mProgressBar = view.findViewById(R.id.store_progress_bar);
        mRecyclerView = view.findViewById(R.id.product_of_category_recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new HomeItemProductAdapter(getContext());

        mRecyclerView.setAdapter(adapter);

        mPresenter.loadProducts(464);
    }

    @Override
    public void showProducts(List<Product> list) {
        Log.d("name", list.get(0).getName());
        adapter.setProductList(list);
    }

    @Override
    public void showProgressBar() {
       // mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
       // mProgressBar.setVisibility(View.GONE);
    }
}
