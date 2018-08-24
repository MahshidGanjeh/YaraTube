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

public class EnterPhoneNumberDialogFragment extends DialogFragment
        implements LoginContract.View {

    private Button confirmPhoneNumberBtn;
    private EditText enterPhoneNumberEditText;
    private onConfirmBtnClickListener mConfirmBtnClickListener;
    private LoginContract.Presenter mPresenter;

    //information of device to be sent to server
    private String deviceId;
    private String deviceModel = Build.MODEL;
    private String deviceOs = Build.VERSION.RELEASE;


    public EnterPhoneNumberDialogFragment() {
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

        confirmPhoneNumberBtn = view.findViewById(R.id.confirm_phonenumber_btn);
        enterPhoneNumberEditText = view.findViewById(R.id.phone_number_et);

        deviceId = Settings.Secure.getString(getContext()
                .getContentResolver(), Settings.Secure.ANDROID_ID);


        mPresenter = new LoginPresenter(this, view.getContext());

        confirmPhoneNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConfirmBtnClickListener.goToVerificationDialog();
                mPresenter.present(enterPhoneNumberEditText.getText().toString(),
                        deviceId, deviceModel, deviceOs);
            }
        });
    }

    @Override
    public void show() {

    }
}
