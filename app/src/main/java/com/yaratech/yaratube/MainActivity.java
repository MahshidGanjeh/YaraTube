package com.yaratech.yaratube;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yaratech.yaratube.home.HomeFragment;
import com.yaratech.yaratube.home.category.CategoryFragment;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager.beginTransaction().replace(R.id.main_container,new HomeFragment()).commit();

    }
}
