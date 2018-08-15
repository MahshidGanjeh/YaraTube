package com.yaratech.yaratube.home.store;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Store;

public class StoreFragment extends Fragment implements StoreContract.View {

    private StoreContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private StoreAdapter adapter;
    private ProgressBar mProgressBar;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new StorePresenter(this);

        mProgressBar = view.findViewById(R.id.store_progress_bar);
        mRecyclerView = view.findViewById(R.id.store_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new StoreAdapter(getContext());

        mRecyclerView.setAdapter(adapter);

        mPresenter.loadHomeItems();
    }

    @Override
    public void showHomeItems(Store store) {
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
}
