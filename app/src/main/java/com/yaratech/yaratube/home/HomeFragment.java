package com.yaratech.yaratube.home;


import android.content.Context;
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
import android.view.Window;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.source.local.LocalDataSource;
import com.yaratech.yaratube.data.source.local.UserDatabase;
import com.yaratech.yaratube.home.category.CategoryFragment;
import com.yaratech.yaratube.home.store.StoreFragment;
import com.yaratech.yaratube.login.MainLoginDialogFragment;
import com.yaratech.yaratube.login.ProfileFragment;
import com.yaratech.yaratube.productdetail.ProductDetailFragment;

public class HomeFragment extends Fragment {

    private BottomNavigationView mBottomNavigationView;
    private FragmentManager mManager;
    private StoreFragment mStoreFragment;
    private CategoryFragment mCategoryFragment;
    private ProfileFragment mProfileFragment;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBottomNavigationView = view.findViewById(R.id.bottom_navigation);
        mManager = getFragmentManager();

        //for the first time that app is launched
        mStoreFragment = new StoreFragment();
        mManager.beginTransaction().
                add(R.id.home_fragment_container, mStoreFragment).commit();

        mBottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case (R.id.bottom_nav_home_item):
                                if (mStoreFragment == null) {
                                    mStoreFragment = new StoreFragment();
                                    mManager.beginTransaction().
                                            add(R.id.home_fragment_container, mStoreFragment).commit();
                                } else if (!mStoreFragment.isVisible()) {
                                    mManager.beginTransaction().hide(mCategoryFragment).commit();
                                    if (mProfileFragment != null) {
                                        mManager.beginTransaction().hide(mProfileFragment).commit();
                                    }
                                    mManager.beginTransaction().show(mStoreFragment).commit();
                                }
                                return true;

                            case (R.id.bottom_nav_category_item):
                                if (mCategoryFragment == null) {
                                    if (mStoreFragment != null && mStoreFragment.isVisible()) {
                                        mManager.beginTransaction().hide(mStoreFragment).commit();
                                    }
                                    mCategoryFragment = new CategoryFragment();
                                    mManager.beginTransaction().
                                            add(R.id.home_fragment_container, mCategoryFragment).commit();
                                } else if (!mCategoryFragment.isVisible()) {
                                    mManager.beginTransaction().hide(mStoreFragment).commit();
                                    if (mProfileFragment != null) {
                                        mManager.beginTransaction().hide(mProfileFragment).commit();
                                    }
                                    mManager.beginTransaction().show(mCategoryFragment).commit();
                                }
                                return true;
                            case (R.id.bottom_nav_more_item):
                                if (mProfileFragment == null) {
                                    if (mStoreFragment != null && mStoreFragment.isVisible()) {
                                        mManager.beginTransaction().hide(mStoreFragment).commit();
                                    }
                                    isLogin(getContext(), mManager);
                                    // mProfileFragment = new ProfileFragment();
                                    //mManager.beginTransaction().
                                    //  add(R.id.home_fragment_container, mProfileFragment).commit();
                                } else if (!mProfileFragment.isVisible()) {
                                    mManager.beginTransaction().hide(mStoreFragment).commit();
                                    mManager.beginTransaction().hide(mCategoryFragment).commit();
                                    mManager.beginTransaction().show(mProfileFragment).commit();
                                }

                        }
                        return true;
                    }
                });
    }

    public void isLogin(Context context, FragmentManager manager) {

        LocalDataSource mLocalDataSource;
        UserDatabase db;
        boolean isLogin = false;
        MainLoginDialogFragment mLoginDialogFragment;

        db = UserDatabase.getUserDatabase(context);
        mLocalDataSource = new LocalDataSource(context);

        isLogin = mLocalDataSource.isLogin(db);

        if (isLogin) {
            mProfileFragment = new ProfileFragment();
            // actionBar.setTitle(R.string.title_profile);
            manager.beginTransaction().addToBackStack("profile").
                    add(R.id.home_fragment_container, mProfileFragment).commit();
        } else {
            mLoginDialogFragment = new MainLoginDialogFragment();

            mLoginDialogFragment.show(manager.beginTransaction(), "dialog");
            //when user click in other point of page, dialog shouldn't
            //be closed
            mLoginDialogFragment.setCancelable(false);
        }
    }
}
