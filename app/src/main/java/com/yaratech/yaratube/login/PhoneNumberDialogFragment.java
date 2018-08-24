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
import android.widget.EditText;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.util.onConfirmBtnClickListener;

public class PhoneNumberDialogFragment extends DialogFragment {

    private Button confirm;
    private onConfirmBtnClickListener mConfirmBtnClickListener;


    public PhoneNumberDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mConfirmBtnClickListener = (onConfirmBtnClickListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone_number_login_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        confirm = view.findViewById(R.id.confirm_phonenumber_btn);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConfirmBtnClickListener.goToVerificationDialog();
            }
        });
    }
}
