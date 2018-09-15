package com.yaratech.yaratube.login;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Profile;
import com.yaratech.yaratube.data.model.User;
import com.yaratech.yaratube.data.source.local.LocalDataSource;
import com.yaratech.yaratube.data.source.local.UserDatabase;

public class ProfileFragment extends Fragment
        implements ProfileContract.View {

    private EditText mNameEditText;
    private EditText mGenderEditText;
    private EditText mBirthdaytEditText;
    private Button mSaveChangeBtn;

    private UserDatabase db;

    private ProfilePresenter mPresenter;

    String name;
    String gender;
    String birthday;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNameEditText = view.findViewById(R.id.profile_name_et);
        mGenderEditText = view.findViewById(R.id.profile_gender_et);
        mBirthdaytEditText = view.findViewById(R.id.profile_birthday_et);
        mSaveChangeBtn = view.findViewById(R.id.save_change_btn);

        db = UserDatabase.getUserDatabase(getContext());

        mPresenter = new ProfilePresenter(this, getContext());

        LocalDataSource localDataSource = new LocalDataSource(getContext());

        if (localDataSource.isLogin(db)) {
            User user = db.userDao().getUser();
            if (user.getFirstName() != null) {
                mNameEditText.setText(user.getFirstName());
            }
            if (user.getGender() != null) {
                mGenderEditText.setText(user.getGender());
            }
            if (user.getBirthday() != null) {
                mBirthdaytEditText.setText(user.getBirthday());
            }
        }

        mSaveChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mNameEditText.getText().toString();
                gender = mGenderEditText.getText().toString();
                birthday = mBirthdaytEditText.getText().toString();

                User user = db.userDao().getUser();
                user.setFirstName(name);
                user.setGender(gender);
                user.setBirthday(birthday);

                //here we post the entered data to the server
                //in order to have them in the server in case the user wants to log out
                //gender string should be "male" or "female" not in farsi :))
                mPresenter.sendProfileFields(
                        name, "male",
                        birthday,
                        user.getToken()
                );

                Toast.makeText(getContext(), R.string.changes_saved, Toast.LENGTH_SHORT).show();

                db.userDao().updateUser(user);
            }
        });

    }

    public void postProfileFieldsToServer(User user) {

    }

    //when we post fields to the server,its response will be profile object too
    @Override
    public void showProfileFields(Profile profile) {
        //Log.i("shoo", "showProfileFields: " + profile.getNickname());
    }

    @Override
    public void showError(String error) {
        Log.i("profile error", "showError: " + error);
    }
}
