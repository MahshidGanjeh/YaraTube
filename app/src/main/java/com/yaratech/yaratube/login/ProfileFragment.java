package com.yaratech.yaratube.login;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.User;
import com.yaratech.yaratube.data.source.local.LocalDataSource;
import com.yaratech.yaratube.data.source.local.UserDatabase;

public class ProfileFragment extends Fragment {

    private EditText mNameEditText;
    private EditText mGenderEditText;
    private EditText mBirthdaytEditText;
    private Button mSaveChangeBtn;

    private UserDatabase db;

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
                db.userDao().updateUser(user);
            }
        });


    }
}
