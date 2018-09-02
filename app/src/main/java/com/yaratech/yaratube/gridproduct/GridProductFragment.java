package com.yaratech.yaratube.gridproduct;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
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
    private ProgressBar mFooterProgress;
    int Counter = 0;
    private Listener.onProductClickListener mOnProductClickListener;
    private static int mCategoryId;

    // Index from which pagination should start (0 is 1st page in our case)
    private static final int PAGE_START = 0;
    // Indicates if footer ProgressBar is shown (i.e. next page is loading)
    private boolean isLoading = false;
    // If current page is the last page (Pagination will stop after this page load)
    private boolean isLastPage = false;
    // total no. of pages to load. Initial load is page 0, after which 2 more pages will load.
    private int TOTAL_PAGES = 20;
    // indicates the current page which Pagination is fetching.
    private int currentPage = PAGE_START;


    public GridProductFragment() {
        // Required empty public constructor
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
        //  mFooterProgress = view.findViewById(R.id.grid_product_pagination_progress_bar);
        mRecyclerView = view.findViewById(R.id.product_of_category_recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        adapter = new GridProductAdapter(getContext(), mOnProductClickListener);

        mRecyclerView.setAdapter(adapter);

        // mPresenter.loadProducts(mCategoryId, 0);
        mRecyclerView.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                //Increment page index to load the next one


                //to see how many items have already added
                int offset = adapter.getItemCount();
                Log.d("weee", String.valueOf(offset));

                adapter.removeLoadingFooter();  // 2
                isLoading = false;   // 3

                mPresenter.loadProducts(mCategoryId, offset);//4

                if (currentPage != TOTAL_PAGES) {
                    adapter.addLoadingFooter();
                }// 5
                else isLastPage = true;
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });


        mPresenter.loadProducts(mCategoryId, adapter.getItemCount());

        if (currentPage <= TOTAL_PAGES) {
            adapter.addLoadingFooter();
        } else isLastPage = true;
        // loadFirstPage();
    }

    @Override
    public void showProducts(List<Product> list) {
        //adapter.setProductList(list);
        adapter.addAll(list);
        //adapter.notifyItemInserted(adapter.getItemCount() - 1);
        //adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        //mFooterProgress.setVisibility(View.GONE);
    }

    @Override
    public void goToProductDetail(int pid) {
        mOnProductClickListener.goToProductDetail(pid);
    }
}
