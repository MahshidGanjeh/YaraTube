package com.yaratech.yaratube.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.util.Listener;


public class MoreFragment extends Fragment {

    private TextView mProfileTextView;
    private TextView mAboutUsTextView;
    private Listener.onProfileClickListener mOnProfileClickListener;
    private Listener.onAboutUsClickListener mOnAboutUsClickListener;

    public MoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mOnProfileClickListener = (Listener.onProfileClickListener) context;
        mOnAboutUsClickListener = (Listener.onAboutUsClickListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProfileTextView = view.findViewById(R.id.profile_tv);
        mAboutUsTextView = view.findViewById(R.id.aboutus_tv);

        mProfileTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnProfileClickListener.goToProfile();
            }
        });

        mAboutUsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnAboutUsClickListener.goToAboutUs();
            }
        });
    }


}
