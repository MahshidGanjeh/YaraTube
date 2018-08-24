package com.yaratech.yaratube.login;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Login;
import com.yaratech.yaratube.data.source.WebService;
import com.yaratech.yaratube.data.source.remote.RemoteDataSource;
import com.yaratech.yaratube.util.onConfirmBtnClickListener;

public class PhoneNumberDialogFragment extends DialogFragment {

    private Button confirm;
    private onConfirmBtnClickListener mConfirmBtnClickListener;
    private EditText getNumberEditText;

    private RemoteDataSource remoteDataSource;

    //information of device to be sent to server
    private String deviceId;
    private final String deviceModel = Build.MODEL;
    private final String deviceOs = Build.VERSION.RELEASE;


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
        getNumberEditText = view.findViewById(R.id.phone_number_et);

        deviceId = Settings.Secure.getString(getContext()
                .getContentResolver(), Settings.Secure.ANDROID_ID);
        remoteDataSource = new RemoteDataSource(view.getContext());

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConfirmBtnClickListener.goToVerificationDialog();
                remoteDataSource.postPhoneNumber(new WebService.ApiResultCallBack() {
                                                     @Override
                                                     public void onSuccess(Object response) {
                                                         /*Toast.makeText(getContext(),
                                                                 ((Login) response).getMessage(),
                                                                 Toast.LENGTH_SHORT).show();*/
                                                     }

                                                     @Override
                                                     public void onFail(Object message) {

                                                     }
                                                 }, getNumberEditText.getText().toString().trim()
                        , deviceId, deviceModel, deviceOs);
            }
        });
    }
}
