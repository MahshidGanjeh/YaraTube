package com.yaratech.yaratube.home.store;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.yaratech.yaratube.data.model.Headeritem;
import java.util.List;


public class CustomPagerAdapter extends FragmentStatePagerAdapter {

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
        this.mHeaderitems = mHeaderitems;
        notifyDataSetChanged();
    }
}

