package com.yaratech.yaratube.login.mobilelogin;


import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.User;
import com.yaratech.yaratube.data.source.local.UserDatabase;
import com.yaratech.yaratube.util.Listener;

public class EnterPhoneNumberFragment extends Fragment
        implements LoginContract.View {

    private Button confirmPhoneNumberBtn;
    private EditText enterPhoneNumberEditText;
    private Listener.onConfirmPhoneNumberListener mConfirmBtnClickListener;
    private PhoneNumberPresenter mPresenter;


    //information of device to be sent to server
    private String deviceId;
    private String deviceModel = Build.MODEL;
    private String deviceOs = Build.VERSION.RELEASE;
    private String mPhoneNumber;


    public EnterPhoneNumberFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone_number, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        confirmPhoneNumberBtn = view.findViewById(R.id.confirm_phonenumber_btn);
        enterPhoneNumberEditText = view.findViewById(R.id.phone_number_et);

        deviceId = Settings.Secure.getString(getContext()
                .getContentResolver(), Settings.Secure.ANDROID_ID);

        mPresenter = new PhoneNumberPresenter(view.getContext());

        mConfirmBtnClickListener = (Listener.onConfirmPhoneNumberListener) getParentFragment();

        confirmPhoneNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhoneNumber = enterPhoneNumberEditText.getText().toString();

                mConfirmBtnClickListener.goToVerificationDialog(mPhoneNumber);

                mPresenter.savePhoneNumberToDb(mPhoneNumber);

                mPresenter.postPhoneNumber(mPhoneNumber,
                        deviceId, deviceModel, deviceOs);
            }
        });
    }

    @Override
    public void show(String token) {

    }
}
