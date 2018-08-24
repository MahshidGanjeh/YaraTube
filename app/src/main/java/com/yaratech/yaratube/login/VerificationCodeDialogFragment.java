package com.yaratech.yaratube.login;


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

public class VerificationCodeDialogFragment extends DialogFragment {

    private Button mConfirmVerificationCodeBtn;
    private EditText mConfirmVerificationCodeEditText;
    private RemoteDataSource mRemoteDataSource;


    public VerificationCodeDialogFragment() {
        // Required empty public constructor
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

        mRemoteDataSource = new RemoteDataSource(getContext());
        final String deviceId = Settings.Secure.getString(getContext()
                .getContentResolver(), Settings.Secure.ANDROID_ID);

        mConfirmVerificationCodeBtn = view.findViewById(R.id.confirm_verificationcode_btn);
        mConfirmVerificationCodeEditText = view.findViewById(R.id.enter_verification_code_et);

        mConfirmVerificationCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRemoteDataSource.postVerificationCode(new WebService.ApiResultCallBack() {
                                                           @Override
                                                           public void onSuccess(Object response) {
                                                               Log.d("mahshid", ((User) response).getToken());
                                                           }

                                                           @Override
                                                           public void onFail(Object message) {

                                                           }
                                                       }, "09211740885", deviceId,
                        mConfirmVerificationCodeEditText.getText().toString().trim(), "D");
            }
        });

    }
}
