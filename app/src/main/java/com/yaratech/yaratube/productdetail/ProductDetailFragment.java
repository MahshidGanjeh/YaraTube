package com.yaratech.yaratube.productdetail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.DetailedProduct;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.WebService;

import org.parceler.Parcels;

import java.util.List;

import static android.widget.LinearLayout.VERTICAL;
import static com.yaratech.yaratube.util.AppConstants.BASE_URL;

public class ProductDetailFragment extends Fragment implements
        CommentContract.View, DetailContract.View {

    private ImageView mImageView;
    private TextView title;
    private TextView description;
    private RecyclerView mCommentRecycler;
    private ProgressBar mProgressBar;


    private CommentAdapter adapter;
    private CommentContract.Presenter mPresenter;
    private DetailContract.Presenter mDetailPresenter;

    private int mProductId;
    private DetailedProduct mDetailedProduct;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new CommentPresenter(this, getActivity().getApplicationContext());
        mDetailPresenter = new DetailPresenter(this, getActivity().getApplicationContext());

        mImageView = view.findViewById(R.id.imageView);
        title = view.findViewById(R.id.product_detail_title_tv);
        description = view.findViewById(R.id.product_detail_description_tv);
        mProgressBar = view.findViewById(R.id.product_detail_progress_bar);

        mCommentRecycler = view.findViewById(R.id.product_detail_comment_recycler);
        adapter = new CommentAdapter();


        mCommentRecycler.setAdapter(adapter);
        DividerItemDecoration itemDecor = new
                DividerItemDecoration(mCommentRecycler.getContext(), VERTICAL);
        mCommentRecycler.addItemDecoration(itemDecor);
        mCommentRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mCommentRecycler.setNestedScrollingEnabled(false);

        mDetailPresenter.loadDetail(mProductId);
        mPresenter.loadComments(mProductId);
    }

    public static ProductDetailFragment newInstance(int p) {
        Bundle args = new Bundle();
        args.putInt("pid", p);
        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductId = getArguments().getInt("pid");
    }

    @Override
    public void showComments(List<Comment> list) {
        adapter.setCommentList(list);
    }

    @Override
    public void showProgressbar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDetail(DetailedProduct product) {
        mDetailedProduct = product;

        Glide.with(getContext()).load(BASE_URL + mDetailedProduct.getAvatar().getHdpi())
                .into(mImageView);
        title.setText(mDetailedProduct.getName());
        description.setText(mDetailedProduct.getDescription());
    }

    @Override
    public void hideProgressbar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
