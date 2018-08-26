package com.yaratech.yaratube.login.mobilelogin;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.util.Listener;

public class MobileLoginFragment extends Fragment {

    private Button mPhoneNumberBtn;
    private Listener.onPhoneNumberBtnListener mNumberBtnListener;


    public MobileLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNumberBtnListener = (Listener.onPhoneNumberBtnListener) getParentFragment();
        mPhoneNumberBtn = view.findViewById(R.id.via_phone_number_btn);
        mPhoneNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mNumberBtnListener.goToPhoneNumberDialog();
            }
        });
    }
}
