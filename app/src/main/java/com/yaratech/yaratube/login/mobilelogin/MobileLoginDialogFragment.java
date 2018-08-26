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
import android.widget.Toast;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = getChildFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_mobile_login_dialog, container, false);

        int id = root.findViewById(R.id.child).getId();
        manager.beginTransaction().add(id, new MobileLoginFragment())
                .commit();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


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
        manager.beginTransaction().add(R.id.child, new EnterVerificationCodeFragment()).commit();
       /* if (mVerificationCodeDialogFragment != null) {
            manager.beginTransaction().remove(mVerificationCodeDialogFragment);
        }
        manager.beginTransaction().addToBackStack(null);

        mVerificationCodeDialogFragment = EnterVerificationCodeFragment.newInstance(phoneNumber);*/
    }

    @Override
    public void goToPhoneNumberDialog() {
        manager.beginTransaction().add(R.id.child, new EnterPhoneNumberFragment()).commit();

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                db.userDao().insertUserToDb(new User(token));
                isLogin = mLocalDataSource.isLogin(db);
            }
        }).start();*/
    }


}
