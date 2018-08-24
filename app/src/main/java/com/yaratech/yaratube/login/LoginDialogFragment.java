package com.yaratech.yaratube.login;


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
import com.yaratech.yaratube.util.onPhoneNumberBtnListener;

public class LoginDialogFragment extends DialogFragment {

    private Button phoneNumber;
    private onPhoneNumberBtnListener numberBtnListener;


    public LoginDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        numberBtnListener = (onPhoneNumberBtnListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_dialoug, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        phoneNumber = view.findViewById(R.id.via_phone_number_btn);
        phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberBtnListener.goToPhoneNumberDialog();
            }
        });
    }
}
