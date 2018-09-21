package com.yaratech.yaratube;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import com.yaratech.yaratube.data.source.local.LocalDataSource;
import com.yaratech.yaratube.data.source.local.UserDatabase;
import com.yaratech.yaratube.gridproduct.GridProductFragment;
import com.yaratech.yaratube.home.HomeFragment;
import com.yaratech.yaratube.login.MainLoginDialogFragment;
import com.yaratech.yaratube.profile.ProfileFragment;
import com.yaratech.yaratube.productdetail.ProductDetailFragment;
import com.yaratech.yaratube.util.Listener;

public class MainActivity extends AppCompatActivity implements
        Listener.onCategoryClickListener, Listener.onProductClickListener, Listener.onProfileClickListener {

    private GridProductFragment mGridProductFragment;
    private ProductDetailFragment mProductDetailFragment;
    private ProfileFragment mProfileFragment;
    private MainLoginDialogFragment mMainLoginDialogFragment;
    private boolean isLogin;

    FragmentManager manager = getSupportFragmentManager();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the app alignment to rtl
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        manager.beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //actionBar.setTitle(R.string.app_name);
    }

    @Override
    public void onCategoryClicked(int categoryId) {
        mGridProductFragment = GridProductFragment.newInstance(categoryId);
        manager.beginTransaction().addToBackStack("products")
                .add(R.id.main_container, mGridProductFragment).commit();
    }

    @Override
    public void goToProductDetail(int pid) {
        //  actionBar.setTitle(R.string.product_detail);
        mProductDetailFragment = ProductDetailFragment.newInstance(pid);
        manager.beginTransaction().addToBackStack("detail")
                .add(R.id.main_container, mProductDetailFragment).commit();
    }

    @Override
    public void goToProfile() {
        isLogin = isLogin(getApplicationContext());

        //if user has logged in before, the profile fragment is shown,
        //if not he should first log in
        if (!isLogin) {
            mMainLoginDialogFragment = new MainLoginDialogFragment();
            mMainLoginDialogFragment.show(manager.beginTransaction(), "login");
            mMainLoginDialogFragment.setCancelable(false);
        } else {
            mProfileFragment = new ProfileFragment();
            manager.beginTransaction().addToBackStack("profile")
                    .add(R.id.main_container, mProfileFragment).commit();
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
