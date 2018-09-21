package com.yaratech.yaratube.login;


import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.User;
import com.yaratech.yaratube.data.source.local.LocalDataSource;
import com.yaratech.yaratube.data.source.local.UserDatabase;
import com.yaratech.yaratube.login.mobilelogin.EnterPhoneNumberFragment;
import com.yaratech.yaratube.login.mobilelogin.EnterVerificationCodeFragment;
import com.yaratech.yaratube.util.Listener;

import java.util.List;

public class MainLoginDialogFragment extends DialogFragment implements
        Listener.onMobileBtnClickListener,
        Listener.onConfirmPhoneNumberListener,
        Listener.onConfirmVerificationCodeListener,
        Listener.onConfirmGoogleLoginListener {

    private LoginOptionsFragment mLoginOptionsFragment;
    private EnterPhoneNumberFragment mPhoneNumberFragment;
    private EnterVerificationCodeFragment mVerificationCodeFragment;
    private FragmentManager manager;

    private LocalDataSource mLocalDataSource;
    private UserDatabase db;

    private static int LOGIN_STEP;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public MainLoginDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //because all other fragments will be children of this dialog
        //and will be replaced with the container in this view
        // we get the childFragmentManager instead of FragmentManager
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        manager = getChildFragmentManager();
        db = UserDatabase.getUserDatabase(getContext());

        pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = pref.edit();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main_login_dialog, container, false);


        //getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        mLoginOptionsFragment = new LoginOptionsFragment();
        mPhoneNumberFragment = new EnterPhoneNumberFragment();
        mVerificationCodeFragment = new EnterVerificationCodeFragment();

        mLocalDataSource = new LocalDataSource(getContext());

        User user = db.userDao().getUser();
        if (db.userDao().getUserPhoneNumberFromDb() != null) {
            manager.beginTransaction().add(R.id.child, mVerificationCodeFragment)
                    .commit();
        } else {
            manager.beginTransaction().add(R.id.child, mLoginOptionsFragment)
                    .commit();
        }
        return root;
    }

    /* @Override
     public Dialog onCreateDialog(Bundle savedInstanceState) {
         Dialog dialog = super.onCreateDialog(savedInstanceState);

         // request a window without the title
         dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
         return dialog;
     }
 */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button dismiss = view.findViewById(R.id.dialog_dismiss_btn);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }

    @Override
    public void goToPhoneNumberDialog() {
        LOGIN_STEP = 1;
        editor.putInt("loginStep", LOGIN_STEP).commit();
        manager.beginTransaction().add(R.id.child, mPhoneNumberFragment).commit();
    }

    @Override
    public void goToVerificationDialog(String phoneNumber) {
        LOGIN_STEP = 2;
        editor.putInt("loginStep", LOGIN_STEP).commit();
        mVerificationCodeFragment = new EnterVerificationCodeFragment();
        manager.beginTransaction().add(R.id.child,
                mVerificationCodeFragment).commit();
    }

    @Override
    public void saveTokenToDatabase(final String token) {
        LOGIN_STEP = 3;
        editor.putInt("loginStep", LOGIN_STEP).commit();
        //get the existing user from database and set this token for it
        User user = db.userDao().getUser();
        user.setToken(token);
        db.userDao().updateUser(user);
        Toast.makeText(getContext(), "خوش آمدید", Toast.LENGTH_SHORT).show();
        this.dismiss();
    }

    @Override
    public void saveGoogleTokenToDatabase(String token) {
        User user = new User();
        user.setToken(token);
        db.userDao().insertUserToDb(user);
        Toast.makeText(getContext(), "خوش آمدید", Toast.LENGTH_SHORT).show();
        this.dismiss();
    }
}
