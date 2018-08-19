package com.yaratech.yaratube;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import com.yaratech.yaratube.gridproduct.GridProductFragment;
import com.yaratech.yaratube.home.HomeFragment;
import com.yaratech.yaratube.productdetail.ProductDetailFragment;

public class MainActivity extends AppCompatActivity implements
        onCategoryClickListener, onProductClickListener {

    FragmentManager manager = getSupportFragmentManager();
    private GridProductFragment mGridProductFragment;
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
}
