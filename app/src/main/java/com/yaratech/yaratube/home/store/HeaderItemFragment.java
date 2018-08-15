package com.yaratech.yaratube.home.store;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Headeritem;

import org.parceler.Parcels;

public class HeaderItemFragment extends Fragment {

    private Headeritem mHeaderitem;
    private ImageView mHeaderImageView;

    public HeaderItemFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHeaderitem = Parcels.unwrap(getArguments().getParcelable("headeritem"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.header_item, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mHeaderImageView = view.findViewById(R.id.header_imgView);
        Glide.with(view.getContext()).load(mHeaderitem.getAvatar().getHdpi()).into(mHeaderImageView);
    }

    public static HeaderItemFragment newInstance(Headeritem headeritem) {

        Bundle args = new Bundle();
        args.putParcelable("headeritem", Parcels.wrap(headeritem));

        HeaderItemFragment fragment = new HeaderItemFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
