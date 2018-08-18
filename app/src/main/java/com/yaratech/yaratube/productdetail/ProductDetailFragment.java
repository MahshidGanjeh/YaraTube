package com.yaratech.yaratube.productdetail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Product;

import org.parceler.Parcels;

import static com.yaratech.yaratube.util.AppConstants.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailFragment extends Fragment {

    private ImageView mImageView;
    private TextView title;
    private TextView description;
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

        mImageView = view.findViewById(R.id.imageView);
        title = view.findViewById(R.id.product_detail_title_tv);
        description = view.findViewById(R.id.product_detail_description_tv);

        Glide.with(getContext()).load(BASE_URL + product.getAvatar())
                .into(mImageView);
        title.setText(product.getName());
        description.setText(product.getShortDescription());
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
}
