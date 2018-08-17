package com.yaratech.yaratube.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.home.category.CategoryFragment;
import com.yaratech.yaratube.home.category.product.ProductFragment;
import com.yaratech.yaratube.home.store.StoreFragment;

public class HomeFragment extends Fragment implements onCategoryClickListener {

    private BottomNavigationView mBottomNavigationView;
    private FragmentManager mManager;
    private int counter = 0;
    private StoreFragment mStoreFragment;
    private CategoryFragment mCategoryFragment;
    private ProductFragment mProductFragment;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        counter++;
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBottomNavigationView = view.findViewById(R.id.bottom_navigation);
        mManager = getFragmentManager();

        //for the first time load the pages
        if (counter == 1) {
            mStoreFragment = new StoreFragment();
            mCategoryFragment = new CategoryFragment();
            mManager.beginTransaction().
                    replace(R.id.home_fragment_container, mStoreFragment).commit();
        }

        mBottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case (R.id.bottom_nav_home_item):
                                if (mStoreFragment != null) {
                                    if (mCategoryFragment.isVisible()) {
                                        mManager.beginTransaction().hide(mCategoryFragment).commit();
                                        mManager.beginTransaction().show(mStoreFragment).commit();
                                    } else
                                        mManager.beginTransaction().show(mStoreFragment).commit();
                                }
                                return true;
                            case (R.id.bottom_nav_category_item):
                                if (mManager.getFragments().contains(mCategoryFragment)) {
                                } else {
                                    mManager.beginTransaction().
                                            add(R.id.home_fragment_container, mCategoryFragment).commit();
                                }
                                if (mStoreFragment.isVisible()) {
                                    mManager.beginTransaction().hide(mStoreFragment).commit();
                                    mManager.beginTransaction().show(mCategoryFragment).commit();
                                } else
                                    mManager.beginTransaction().show(mCategoryFragment).commit();
                                return true;
                        }
                        return true;
                    }
                });

    }

    @Override
    public void onCategoryClicked(int categoryId) {
        mProductFragment = ProductFragment.newInstance(categoryId);
    }
}
