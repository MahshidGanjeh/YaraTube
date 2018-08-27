package com.yaratech.yaratube.login.mobilelogin;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.User;
import com.yaratech.yaratube.data.source.local.LocalDataSource;
import com.yaratech.yaratube.data.source.local.UserDatabase;
import com.yaratech.yaratube.util.Listener;

public class MainLoginDialogFragment extends DialogFragment implements
        Listener.onMobileBtnClickListener,
        Listener.onConfirmPhoneNumberListener,
        Listener.onConfirmVerificationCodeListener {

    private MobileLoginFragment mobileLoginFragment;
    private EnterPhoneNumberFragment mPhoneNumberDialogFragment;
    private EnterVerificationCodeFragment mVerificationCodeDialogFragment;
    private FragmentManager manager;

    private LocalDataSource mLocalDataSource;
    private UserDatabase db;
    boolean isLogin = false;

    public MainLoginDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //because all other fragments will be children of this dialog
        //and will be replaced with the container in this view
        // we get the childFragmentManager instead of FragmentManager
        manager = getChildFragmentManager();
        db = UserDatabase.getUserDatabase(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_mobile_login_dialog, container, false);

        mobileLoginFragment = new MobileLoginFragment();
        mPhoneNumberDialogFragment = new EnterPhoneNumberFragment();
        mVerificationCodeDialogFragment = new EnterVerificationCodeFragment();

        mLocalDataSource = new LocalDataSource(getContext());

        manager.beginTransaction().add(R.id.child, mobileLoginFragment)
                .commit();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void goToPhoneNumberDialog() {
        manager.beginTransaction().add(R.id.child, mPhoneNumberDialogFragment).commit();
    }

    @Override
    public void goToVerificationDialog(String phoneNumber) {
        mVerificationCodeDialogFragment = EnterVerificationCodeFragment.newInstance(phoneNumber);
        manager.beginTransaction().add(R.id.child,
                mVerificationCodeDialogFragment).commit();
    }

    @Override
    public void saveTokenToDatabase(final String token) {
        db.userDao().insertUserToDb(new User(token));
        isLogin = mLocalDataSource.isLogin(db);
    }
}
