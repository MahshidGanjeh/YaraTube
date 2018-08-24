package com.yaratech.yaratube;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.yaratech.yaratube.gridproduct.GridProductFragment;
import com.yaratech.yaratube.home.HomeFragment;
import com.yaratech.yaratube.login.LoginDialogFragment;
import com.yaratech.yaratube.login.PhoneNumberDialogFragment;
import com.yaratech.yaratube.login.VerificationCodeDialogFragment;
import com.yaratech.yaratube.productdetail.ProductDetailFragment;
import com.yaratech.yaratube.util.onCategoryClickListener;
import com.yaratech.yaratube.util.onConfirmBtnClickListener;
import com.yaratech.yaratube.util.onPhoneNumberBtnListener;
import com.yaratech.yaratube.util.onProductClickListener;

public class MainActivity extends AppCompatActivity implements
        onCategoryClickListener, onProductClickListener, onPhoneNumberBtnListener,
        onConfirmBtnClickListener {


    private GridProductFragment mGridProductFragment;
    private ProductDetailFragment productDetailFragment;
    private LoginDialogFragment mLoginDialogFragment;
    private PhoneNumberDialogFragment mPhoneNumberDialogFragment;
    private VerificationCodeDialogFragment mVerificationCodeDialogFragment;


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

        mDrawerNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_profile:
                        if (mLoginDialogFragment != null) {
                            manager.beginTransaction().remove(mLoginDialogFragment);
                        }
                        manager.beginTransaction().addToBackStack(null);

                        mLoginDialogFragment = new LoginDialogFragment();
                        mLoginDialogFragment.show(manager.beginTransaction(), "dialog");

                        drawer.closeDrawers();
                        return true;
                    case R.id.drawer_aboutous:
                }
                return false;
            }
        });

    }

    @Override
    public void onCategoryClicked(int categoryId) {
        mGridProductFragment = GridProductFragment.newInstance(categoryId);
        Toast.makeText(getApplicationContext(), String.valueOf(categoryId), Toast.LENGTH_SHORT).show();
        manager.beginTransaction().addToBackStack("products")
                .add(R.id.main_container, mGridProductFragment).commit();
    }

    @Override
    public void goToProductDetail(int pid) {
        productDetailFragment = ProductDetailFragment.newInstance(pid);
        //Toast.makeText(getApplicationContext(), String.valueOf(p.getName()), Toast.LENGTH_SHORT).show();
        manager.beginTransaction().addToBackStack("detail")
                .add(R.id.main_container, productDetailFragment).commit();
    }

    @Override
    public void goToPhoneNumberDialog() {

        mLoginDialogFragment.dismiss();

        if (mPhoneNumberDialogFragment != null) {
            manager.beginTransaction().remove(mPhoneNumberDialogFragment);
        }
        manager.beginTransaction().addToBackStack(null).commit();
        mPhoneNumberDialogFragment = new PhoneNumberDialogFragment();

        mPhoneNumberDialogFragment.show(manager.beginTransaction(), "phonenumber");
    }

    @Override
    public void goToVerificationDialog() {
        mPhoneNumberDialogFragment.dismiss();

        if (mVerificationCodeDialogFragment != null) {
            manager.beginTransaction().remove(mVerificationCodeDialogFragment);
        }
        manager.beginTransaction().addToBackStack(null);
        
        mVerificationCodeDialogFragment = new VerificationCodeDialogFragment();
        mVerificationCodeDialogFragment.show(manager.beginTransaction(), "verificationcode");
    }
}
