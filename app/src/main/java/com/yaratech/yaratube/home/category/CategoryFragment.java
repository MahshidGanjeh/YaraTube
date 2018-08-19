package com.yaratech.yaratube.home.category;

import android.content.Context;
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
import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.onCategoryClickListener;

import java.util.List;

public class CategoryFragment extends Fragment implements
        CategoryContract.View, onCategoryClickListener {

    private CategoryContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private CategoryAdapter adapter;
    private onCategoryClickListener mCategoryClickListener;
    private ProgressBar mProgressBar;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCategoryClickListener = (onCategoryClickListener) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new CategoryPresenter(this, getActivity().getApplicationContext());

        mProgressBar = view.findViewById(R.id.category_progress_bar);
        mRecyclerView = view.findViewById(R.id.category_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CategoryAdapter(getContext(), this, getFragmentManager());
        mRecyclerView.setAdapter(adapter);

        mPresenter.loadCategories();
    }


    @Override
    public void showCategories(List<Category> list) {
        adapter.setCategoryList(list);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCategoryClicked(int id) {
        mCategoryClickListener.onCategoryClicked(id);
    }
}
