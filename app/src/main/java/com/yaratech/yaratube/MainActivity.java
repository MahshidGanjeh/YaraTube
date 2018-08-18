package com.yaratech.yaratube;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.gridproduct.ProductFragment;
import com.yaratech.yaratube.home.HomeFragment;
import com.yaratech.yaratube.home.category.CategoryFragment;
import com.yaratech.yaratube.productdetail.ProductDetailFragment;

public class MainActivity extends AppCompatActivity implements
        onCategoryClickListener, onProductClickListener {

    FragmentManager manager = getSupportFragmentManager();
    private ProductFragment mProductFragment;
    private ProductDetailFragment productDetailFragment;

    //@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the app to rtl
        // getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        manager.beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();

    }

    @Override
    public void onCategoryClicked(int categoryId) {
        mProductFragment = ProductFragment.newInstance(categoryId);
        Toast.makeText(getApplicationContext(), String.valueOf(categoryId), Toast.LENGTH_SHORT).show();
        manager.beginTransaction().addToBackStack("P").add(R.id.main_container, mProductFragment).commit();
    }

    @Override
    public void onProductClicked(Product p) {
        productDetailFragment = ProductDetailFragment.newInstance(p);
        Toast.makeText(getApplicationContext(), String.valueOf(p.getName()), Toast.LENGTH_SHORT).show();
        manager.beginTransaction().addToBackStack("P").add(R.id.main_container, productDetailFragment).commit();
    }
}
