package com.yaratech.yaratube.login;


import android.content.Intent;
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

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.source.WebService;
import com.yaratech.yaratube.data.source.remote.RemoteDataSource;
import com.yaratech.yaratube.util.Listener;

public class LoginOptionsFragment extends Fragment
        implements GoogleApiClient.OnConnectionFailedListener {

    private Button mPhoneNumberBtn;
    private Button mGoogleBtn;
    private Listener.onMobileBtnClickListener mNumberBtnListener;

    private GoogleApiClient mGoogleApiClient;
    private final int REQUEST_CODE = 9001;


    public LoginOptionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {

            Log.d("already signed in", "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {

            //showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    //hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_options, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNumberBtnListener = (Listener.onMobileBtnClickListener) getParentFragment();

        mGoogleBtn = view.findViewById(R.id.via_google_btn);
        mPhoneNumberBtn = view.findViewById(R.id.via_phone_number_btn);

        mPhoneNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumberBtnListener.goToPhoneNumberDialog();
            }
        });

        mGoogleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, REQUEST_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == REQUEST_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("signnn", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfolly, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            final String Device_id = Settings.Secure.getString(getContext().getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            new RemoteDataSource(getContext()).postGoogleLoginResult(
                    acct.getIdToken(), Device_id, Build.MODEL, Build.VERSION.RELEASE,
                    new WebService.ApiResultCallBack() {
                        @Override
                        public void onSuccess(Object response) {
                            Log.d("succcess", "onSuccess: we are there");
                        }

                        @Override
                        public void onFail(Object message) {

                        }
                    }
            );
            //mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            //Similarly you can get the email and photourl using acct.getEmail() and  acct.getPhotoUrl()

            if (acct.getPhotoUrl() != null) {
            }
            // new LoadProfileImage(imgProfilePic).execute(acct.getPhotoUrl().toString());

            //updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
