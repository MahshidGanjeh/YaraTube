package com.yaratech.yaratube.productdetail;


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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.Product;

import org.parceler.Parcels;

import java.util.List;

import static com.yaratech.yaratube.util.AppConstants.BASE_URL;

public class ProductDetailFragment extends Fragment implements CommentContract.View {

    private ImageView mImageView;
    private TextView title;
    private TextView description;
    private RecyclerView mCommentRecycler;
    private CommentAdapter adapter;
    private CommentContract.Presenter mPresenter;


    private Product product;

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

        mPresenter = new CommentPresenter(this);

        mImageView = view.findViewById(R.id.imageView);
        title = view.findViewById(R.id.product_detail_title_tv);
        description = view.findViewById(R.id.product_detail_description_tv);

        mCommentRecycler = view.findViewById(R.id.product_detail_comment_recycler);
        adapter = new CommentAdapter();

        Glide.with(getContext()).load(BASE_URL + product.getAvatar().getHdpi())
                .into(mImageView);
        title.setText(product.getName());
        description.setText(product.getShortDescription());

        mCommentRecycler.setAdapter(adapter);
        mCommentRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        mPresenter.loadComments(33229);
    }

    public static ProductDetailFragment newInstance(Product p) {
        Bundle args = new Bundle();
        args.putParcelable("product", Parcels.wrap(p));
        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        product = Parcels.unwrap(getArguments().getParcelable("product"));
    }

    @Override
    public void showComments(List<Comment> list) {
        Log.d("cm", list.get(2).getCommentText());
        adapter.setCommentList(list);
    }
}
