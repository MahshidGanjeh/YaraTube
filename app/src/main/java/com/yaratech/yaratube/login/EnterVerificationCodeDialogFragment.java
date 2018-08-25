package com.yaratech.yaratube.login;


import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.User;
import com.yaratech.yaratube.data.source.WebService;
import com.yaratech.yaratube.data.source.remote.RemoteDataSource;
import com.yaratech.yaratube.util.Listener;

public class EnterVerificationCodeDialogFragment extends DialogFragment
        implements LoginContract.View {

    private Button mConfirmVerificationCodeBtn;
    private EditText mEnterVerificationCodeEditText;
    private String mVerificationCode;
    private String mPhoneNumber;
    private LoginContract.CodePresenter mPresenter;
    private Listener.onConfirmVerificationCodeListener verificationCodeListener;


    public EnterVerificationCodeDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        verificationCodeListener = (Listener.onConfirmVerificationCodeListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhoneNumber = getArguments().getString("mobile");
    }

    public static EnterVerificationCodeDialogFragment newInstance(String phone) {

        Bundle args = new Bundle();
        args.putString("mobile", phone);
        EnterVerificationCodeDialogFragment fragment = new EnterVerificationCodeDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verification_code_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new LoginPresenter(this, view.getContext());
        final String deviceId = Settings.Secure.getString(getContext()
                .getContentResolver(), Settings.Secure.ANDROID_ID);

        mConfirmVerificationCodeBtn = view.findViewById(R.id.confirm_verificationcode_btn);
        mEnterVerificationCodeEditText = view.findViewById(R.id.enter_verification_code_et);

        mConfirmVerificationCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVerificationCode = mEnterVerificationCodeEditText.getText().toString().trim();
                String nickname;

                mPresenter.presentVerificationCode(mPhoneNumber, deviceId, mVerificationCode,
                        "nickname");


            }
        });

    }

    @Override
    public void show(String token) {
        verificationCodeListener.saveTokenToDatabase(token);
    }
}
