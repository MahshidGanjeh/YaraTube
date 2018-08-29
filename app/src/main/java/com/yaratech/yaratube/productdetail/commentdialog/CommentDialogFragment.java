package com.yaratech.yaratube.productdetail.commentdialog;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.source.local.UserDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentDialogFragment extends DialogFragment
        implements CommentContract.View {

    private int mRelatedCommentProductId;

    private EditText mEnterCommentEditText;
    private Button mSubmitCommentBtn;
    private RatingBar mRatingBar;

    private String mCommentText;
    private int mCommentRate;

    private CommentPresenter mPresenter;


    public CommentDialogFragment() {
        // Required empty public constructor
    }

    public static CommentDialogFragment newInstance(int productId) {

        Bundle args = new Bundle();
        args.putInt("pid", productId);
        CommentDialogFragment fragment = new CommentDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRelatedCommentProductId = getArguments().getInt("pid");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEnterCommentEditText = view.findViewById(R.id.enter_comment_et);
        mSubmitCommentBtn = view.findViewById(R.id.send_comment_btn);
        mRatingBar = view.findViewById(R.id.rate_comment_ratingBar);

        final String token = UserDatabase.getUserDatabase(getContext())
                .userDao().getUserTokenFromDatabase();

        mPresenter = new CommentPresenter(this, getContext());

        mSubmitCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommentText = mEnterCommentEditText.getText().toString();
                mCommentRate = (int) mRatingBar.getRating();
                mPresenter.sendComment("", mCommentRate, mCommentText,
                        mRelatedCommentProductId,
                        token
                );

                getDialog().dismiss();
            }
        });
    }

    @Override
    public void show() {

    }
}
