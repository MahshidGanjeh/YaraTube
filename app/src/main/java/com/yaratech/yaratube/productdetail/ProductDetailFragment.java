package com.yaratech.yaratube.productdetail;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.DetailedProduct;
import com.yaratech.yaratube.data.source.local.LocalDataSource;
import com.yaratech.yaratube.data.source.local.UserDatabase;
import com.yaratech.yaratube.playvideo.PlayActivity;
import com.yaratech.yaratube.productdetail.commentdialog.CommentDialogFragment;
import com.yaratech.yaratube.util.Listener;

import java.util.List;

import static android.widget.LinearLayout.VERTICAL;
import static com.yaratech.yaratube.util.AppConstants.BASE_URL;

public class ProductDetailFragment extends Fragment implements
        CommentContract.View, DetailContract.View {

    private ImageView mHeaderImageView;
    private TextView title;
    private TextView description;
    private Button mSendCommentBtn;
    private RecyclerView mCommentRecycler;
    private Button mPlayButton;
    private ProgressBar mProgressBar;

    private CommentAdapter commentAdapter;
    private CommentPresenter mCommentPresenter;
    private DetailPresenter mDetailPresenter;

    private int mProductId;
    private String mPlayVideoUrl;
    Intent intentToPlayActivity;
    private DetailedProduct mDetailedProduct;

    private Listener.onProfileClickListener mProfileClickListener;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductId = getArguments().getInt("pid");
    }

    public static ProductDetailFragment newInstance(int p) {
        Bundle args = new Bundle();
        args.putInt("pid", p);
        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mProfileClickListener = (Listener.onProfileClickListener) context;
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

        mCommentPresenter = new CommentPresenter(this, getActivity().getApplicationContext());
        mDetailPresenter = new DetailPresenter(this, getActivity().getApplicationContext());

        mHeaderImageView = view.findViewById(R.id.header_imageView);
        title = view.findViewById(R.id.product_detail_title_tv);
        description = view.findViewById(R.id.product_detail_description_tv);
        mSendCommentBtn = view.findViewById(R.id.comment_btn);
        mPlayButton = view.findViewById(R.id.play_video_btn);
        mProgressBar = view.findViewById(R.id.product_detail_progress_bar);
        intentToPlayActivity = new Intent(getContext(), PlayActivity.class);
        mCommentRecycler = view.findViewById(R.id.product_detail_comment_recycler);

        commentAdapter = new CommentAdapter();
        mCommentRecycler.setAdapter(commentAdapter);
        //to show the line in the bottom of each item
        DividerItemDecoration itemDecor = new
                DividerItemDecoration(mCommentRecycler.getContext(), VERTICAL);
        mCommentRecycler.addItemDecoration(itemDecor);
        mCommentRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        //to cancel the scrolling mode of recycler so it just scroll with nested scroll
        mCommentRecycler.setNestedScrollingEnabled(false);

        mSendCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentDialogFragment.newInstance(mProductId).show(getFragmentManager(), "cm");
            }
        });

        mPlayButton.setOnClickListener(new MyOnClickListener());

        mHeaderImageView.setOnClickListener(new MyOnClickListener());

        mDetailPresenter.loadDetail(mProductId);
        mCommentPresenter.loadComments(mProductId);
    }


    @Override
    public void showComments(List<Comment> list) {
        commentAdapter.setCommentList(list);
    }

    @Override
    public void showProgressbar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDetail(DetailedProduct product) {
        mDetailedProduct = product;

        //send the url to play video
        mPlayVideoUrl = mDetailedProduct.getFiles().get(0).getFile();
        if (mPlayVideoUrl != "") {
            intentToPlayActivity.putExtra("url", mPlayVideoUrl);
        }

        Glide.with(getContext()).load(BASE_URL + mDetailedProduct.getFeatureAvatar().getXhdpi())
                .into(mHeaderImageView);
        title.setText(mDetailedProduct.getName());
        description.setText(mDetailedProduct.getDescription());
    }

    @Override
    public void hideProgressbar() {
        mProgressBar.setVisibility(View.GONE);
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (isLogin(getContext())) {
                startActivity(intentToPlayActivity);
            } else {
                mProfileClickListener.goToProfile();
            }
        }
    }

    public boolean isLogin(Context context) {
        LocalDataSource mLocalDataSource;
        UserDatabase db;
        boolean isLogin = false;

        db = UserDatabase.getUserDatabase(context);
        mLocalDataSource = new LocalDataSource(context);

        isLogin = mLocalDataSource.isLogin(db);
        return isLogin;
    }
}
