package com.yaratech.yaratube.home.store;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Headeritem;
import com.yaratech.yaratube.util.Listener;

import org.parceler.Parcels;

import static com.yaratech.yaratube.util.AppConstants.BASE_URL;

public class HeaderItemFragment extends Fragment {

    private Headeritem mHeaderitem;
    private ImageView mHeaderImageView;
    private static Listener.onProductClickListener mListener;

    public HeaderItemFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.header_item, container, false);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHeaderitem = Parcels.unwrap(getArguments().getParcelable("headeritem"));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "tttt", Toast.LENGTH_SHORT).show();
                mListener.goToProductDetail(mHeaderitem.getId());
            }
        });

        mHeaderImageView = view.findViewById(R.id.header_imgView);
        Glide.with(view.getContext()).load(BASE_URL +
                mHeaderitem.getFeatureAvatar().getHdpi()).into(mHeaderImageView);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (Listener.onProductClickListener) context;
    }

    public static HeaderItemFragment newInstance(Headeritem headeritem) {

        Bundle args = new Bundle();
        args.putParcelable("headeritem", Parcels.wrap(headeritem));

        HeaderItemFragment fragment = new HeaderItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
