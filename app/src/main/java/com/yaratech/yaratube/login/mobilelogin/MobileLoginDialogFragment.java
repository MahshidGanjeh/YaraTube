package com.yaratech.yaratube.login.mobilelogin;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.User;
import com.yaratech.yaratube.util.Listener;

public class MobileLoginDialogFragment extends DialogFragment implements
        Listener.onPhoneNumberBtnListener,
        Listener.onConfirmBtnClickListener,
        Listener.onConfirmVerificationCodeListener {

    private MobileLoginFragment mobileLoginFragment;
    private EnterPhoneNumberFragment mPhoneNumberDialogFragment;
    private EnterVerificationCodeFragment mVerificationCodeDialogFragment;
    private FragmentManager manager;

    public MobileLoginDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_mobile_login_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mobileLoginFragment = new MobileLoginFragment();
        manager = getFragmentManager();
       // int id = view.findViewById(R.id.child_dialog_container).getId();
        manager.beginTransaction().add(R.id.child_dialog_container,mobileLoginFragment)
                .commit();
    }

    @Override
    public void saveTokenToDatabase(String token) {
     /*   mLoginDialogFragment.dismiss();

        if (mPhoneNumberDialogFragment != null) {
            manager.beginTransaction().remove(mPhoneNumberDialogFragment);
        }
        manager.beginTransaction().addToBackStack(null).commit();
        mPhoneNumberDialogFragment = new EnterPhoneNumberFragment();*/
    }

    @Override
    public void goToVerificationDialog(String phoneNumber) {
       /* if (mVerificationCodeDialogFragment != null) {
            manager.beginTransaction().remove(mVerificationCodeDialogFragment);
        }
        manager.beginTransaction().addToBackStack(null);

        mVerificationCodeDialogFragment = EnterVerificationCodeFragment.newInstance(phoneNumber);*/
    }

    @Override
    public void goToPhoneNumberDialog() {
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                db.userDao().insertUserToDb(new User(token));
                isLogin = mLocalDataSource.isLogin(db);
            }
        }).start();*/
    }
}
