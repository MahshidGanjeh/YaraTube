package com.yaratech.yaratube.home.store;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yaratech.yaratube.data.model.Headeritem;
import com.yaratech.yaratube.util.onProductClickListener;

import java.util.List;


public class CustomPagerAdapter extends FragmentStatePagerAdapter implements onProductClickListener {

    private List<Headeritem> mHeaderitems;


    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return HeaderItemFragment.newInstance(mHeaderitems.get(position));
    }

    @Override
    public int getCount() {
        if (mHeaderitems != null) {
            return mHeaderitems.size();
        } else return 0;
    }

    public void setHeaderitems(List<Headeritem> mHeaderitems) {
        notifyDataSetChanged();
        this.mHeaderitems = mHeaderitems;
    }

    @Override
    public void goToProductDetail(int pid) {

    }
}

