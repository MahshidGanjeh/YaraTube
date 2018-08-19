package com.yaratech.yaratube.home.store;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.onProductClickListener;

public class StoreFragment extends Fragment implements StoreContract.View,
        onProductClickListener {

    private StoreContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private StoreAdapter adapter;
    private ProgressBar mProgressBar;
    onProductClickListener listener;

    public StoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (onProductClickListener) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new StorePresenter(this);

        mProgressBar = view.findViewById(R.id.store_progress_bar);
        mRecyclerView = view.findViewById(R.id.store_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new StoreAdapter(getContext(), getChildFragmentManager(), this);

        mRecyclerView.setAdapter(adapter);

        mPresenter.loadHomeItems();
    }

    @Override
    public void showHomeItems(Store store) {
        Log.d("name", store.getHomeitem().get(0).getTitle());
        adapter.setItemList(store);
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
    public void goToProductDetail(int p) {
        listener.goToProductDetail(p);
    }
}
