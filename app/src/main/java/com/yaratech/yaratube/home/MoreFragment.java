package com.yaratech.yaratube.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.source.local.LocalDataSource;
import com.yaratech.yaratube.data.source.local.UserDatabase;


public class MoreFragment extends Fragment {

    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more, container, false);
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
