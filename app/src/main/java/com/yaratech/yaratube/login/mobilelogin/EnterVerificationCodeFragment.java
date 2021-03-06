package com.yaratech.yaratube.login.mobilelogin;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.yaratech.yaratube.MainActivity;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.source.local.UserDatabase;
import com.yaratech.yaratube.util.Listener;

import static android.support.v4.content.ContextCompat.checkSelfPermission;

public class EnterVerificationCodeFragment extends Fragment
        implements LoginContract.View {

    private Button mConfirmVerificationCodeBtn;
    private EditText mEnterVerificationCodeEditText;
    private Button mCorrectPhoneNumberBtn;
    private String mVerificationCode;
    private String mPhoneNumber;
    private LoginContract.CodePresenter mPresenter;
    private Listener.onConfirmVerificationCodeListener verificationCodeListener;

    private SmsReceiver smsReceiver;

    public EnterVerificationCodeFragment() {
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
        return inflater.inflate(R.layout.fragment_verification_code, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new LoginPresenter(this, view.getContext());
        //because parent Fragment implement the listener the act of casting is possible
        verificationCodeListener = (Listener.onConfirmVerificationCodeListener) getParentFragment();


        final String deviceId = Settings.Secure.getString(getContext()
                .getContentResolver(), Settings.Secure.ANDROID_ID);

        mConfirmVerificationCodeBtn = view.findViewById(R.id.confirm_verificationcode_btn);
        mEnterVerificationCodeEditText = view.findViewById(R.id.enter_verification_code_et);
        mCorrectPhoneNumberBtn = view.findViewById(R.id.correct_phonenumber_btn);

        //retrieve the phoneNumber from database
        mPhoneNumber = UserDatabase.getUserDatabase(getContext()).userDao().getUserPhoneNumberFromDb();

        //get the permission for reading sms if android version is higher than 6
        int permissionCheck = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            permissionCheck = checkSelfPermission(getContext(),
                    Manifest.permission.RECEIVE_SMS);
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            // User may have declined earlier, ask Android if we should show him a reason
            if (shouldShowRequestPermissionRationale(Manifest.permission.RECEIVE_SMS)) {
                // try again to request the permission.
            } else {
                // request the permission.
                requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS},
                        1);
            }
        } else {
            // got permission use it
            mEnterVerificationCodeEditText.setText(mVerificationCode);
        }

        //retrieve the sms when broadcasted
        smsReceiver = new SmsReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //get the data using the intent
                mVerificationCode = intent.getStringExtra("verificationCode");
                mEnterVerificationCodeEditText.setText(mVerificationCode);
            }
        };

        mConfirmVerificationCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.presentVerificationCode(mPhoneNumber, deviceId, mVerificationCode,
                        "nickname");
            }
        });

        mCorrectPhoneNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragment().getChildFragmentManager().beginTransaction()
                        .add(R.id.child, new EnterPhoneNumberFragment()).commit();
            }
        });
    }

    @Override
    public void show(String token) {
        verificationCodeListener.saveTokenToDatabase(token);
    }

    //override it for using smsReceiver
    @Override
    public void onPause() {
        super.onPause();
        try {
            getActivity().unregisterReceiver(smsReceiver);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("Receiver not registered")) {
                Log.i("TAG", "Tried to unregister the reciver when it's not registered");
            } else {
                throw e;
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.SmsReceiver");
        getActivity().registerReceiver(smsReceiver, filter);
        //the first parameter is the name of the inner class we created.
    }
}
