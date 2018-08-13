package com.yaratech.yaratube.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.home.category.CategoryFragment;
import com.yaratech.yaratube.home.mainpage.MainPageFragment;

public class HomeFragment extends Fragment {

    private BottomNavigationView mBottomNavigationView;

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

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case (R.id.bottom_nav_home_item):
                        getFragmentManager().beginTransaction().
                                replace(R.id.home_fragment_container, new MainPageFragment()).commit();
                        return true;
                    case (R.id.bottom_nav_category_item):
                        getFragmentManager().beginTransaction()
                                .replace(R.id.home_fragment_container, new CategoryFragment()).commit();
                        return true;
                }
                return true;
            }
        });

    }
}