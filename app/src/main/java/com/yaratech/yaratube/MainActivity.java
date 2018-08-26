package com.yaratech.yaratube;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;


import com.yaratech.yaratube.data.source.local.LocalDataSource;
import com.yaratech.yaratube.data.source.local.UserDatabase;
import com.yaratech.yaratube.gridproduct.GridProductFragment;
import com.yaratech.yaratube.home.HomeFragment;
import com.yaratech.yaratube.login.mobilelogin.MainLoginDialogFragment;
import com.yaratech.yaratube.login.ProfileFragment;
import com.yaratech.yaratube.productdetail.ProductDetailFragment;
import com.yaratech.yaratube.util.Listener;

public class MainActivity extends AppCompatActivity implements
        Listener.onCategoryClickListener, Listener.onProductClickListener {


    private GridProductFragment mGridProductFragment;
    private ProductDetailFragment mProductDetailFragment;

    private MainLoginDialogFragment mLoginDialogFragment;
    private ProfileFragment mProfileFragment;


    private LocalDataSource mLocalDataSource;
    private UserDatabase db;
    boolean isLogin = false;

    private NavigationView mDrawerNavigationView;
    private DrawerLayout drawer;

    FragmentManager manager = getSupportFragmentManager();

    //@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the app to rtl
        // getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        manager.beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();

        mDrawerNavigationView = findViewById(R.id.drawer_navigation_view);
        drawer = findViewById(R.id.drawer);

        db = UserDatabase.getUserDatabase(getApplicationContext());
        mLocalDataSource = new LocalDataSource(getApplicationContext());

        isLogin = mLocalDataSource.isLogin(db);

        mDrawerNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.drawer_profile:
                                Log.d("flage", String.valueOf(isLogin));
                                if (isLogin) {
                                    mProfileFragment = new ProfileFragment();
                                    manager.beginTransaction().
                                            add(R.id.main_container, mProfileFragment).commit();
                                } else {
                                    mLoginDialogFragment = new MainLoginDialogFragment();
                                    //manager.beginTransaction().add(
                                    //   R.id.main_container,mLoginDialogFragment).commit();
                                    mLoginDialogFragment.show(manager.beginTransaction(), "dialog");

                                    drawer.closeDrawers();
                                    return true;
                                }

                            case R.id.drawer_aboutous:
                        }
                        return false;
                    }
                });

    }

    @Override
    public void onCategoryClicked(int categoryId) {
        mGridProductFragment = GridProductFragment.newInstance(categoryId);
        //Toast.makeText(getApplicationContext(), String.valueOf(categoryId), Toast.LENGTH_SHORT).show();
        manager.beginTransaction().addToBackStack("products")
                .add(R.id.main_container, mGridProductFragment).commit();
    }

    @Override
    public void goToProductDetail(int pid) {
        mProductDetailFragment = ProductDetailFragment.newInstance(pid);
        //Toast.makeText(getApplicationContext(), String.valueOf(p.getName()), Toast.LENGTH_SHORT).show();
        manager.beginTransaction().addToBackStack("detail")
                .add(R.id.main_container, mProductDetailFragment).commit();
    }

}
