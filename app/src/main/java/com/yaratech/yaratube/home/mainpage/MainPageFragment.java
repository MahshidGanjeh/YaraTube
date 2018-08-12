package com.yaratech.yaratube.home.mainpage;


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

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.HomeItem;
import com.yaratech.yaratube.home.category.CategoryAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPageFragment extends Fragment implements MainPageContract.View {

    private MainPageContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    HomeItemRowAdapter adapter;

    public MainPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new MainPagePresenter(this);

        mRecyclerView = view.findViewById(R.id.main_page_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HomeItemRowAdapter(getContext());

        mRecyclerView.setAdapter(adapter);

        mPresenter.loadHomeItems();
    }

    @Override
    public void showHomeItems(List<HomeItem> list) {
        adapter.setItemList(list);
    }
}
