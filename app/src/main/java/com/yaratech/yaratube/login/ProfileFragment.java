package com.yaratech.yaratube.login;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Profile;
import com.yaratech.yaratube.data.model.User;
import com.yaratech.yaratube.data.source.WebService;
import com.yaratech.yaratube.data.source.local.LocalDataSource;
import com.yaratech.yaratube.data.source.local.UserDatabase;
import com.yaratech.yaratube.data.source.remote.RemoteDataSource;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.yaratech.yaratube.util.AppConstants.BASE_URL;

public class ProfileFragment extends Fragment
        implements ProfileContract.View, onFragmentInteractionListener {

    private static String TAG = "profile";

    private EditText mNameEditText;
    private EditText mGenderEditText;
    private TextView mDateOfBirthTextView;
    private ImageView mEditDateImageView;
    private ImageView mAvatarImageView;
    private Button mSaveChangeBtn;

    private UserDatabase db;

    private ProfilePresenter mPresenter;
    private SelectImageDialog mSelectImageDialog;

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
        mDateOfBirthTextView = view.findViewById(R.id.profile_birthday_tv);
        mEditDateImageView = view.findViewById(R.id.profile_birthday_imgView);
        mAvatarImageView = view.findViewById(R.id.profile_avatar_imgView);
        mSaveChangeBtn = view.findViewById(R.id.save_change_btn);

        db = UserDatabase.getUserDatabase(getContext());

        mPresenter = new ProfilePresenter(this, getContext());

        setProfileFieldsFromDatabase(db);
        mSelectImageDialog = SelectImageDialog.newInstance(this);

        mAvatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectImageDialog.show(getFragmentManager(), "tag");
            }
        });

        mSaveChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mNameEditText.getText().toString();
                gender = mGenderEditText.getText().toString();

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
                mNameEditText.setClickable(false);
                mGenderEditText.setClickable(false);

                db.userDao().updateUser(user);
            }
        });

        mEditDateImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersianDatePickerDialog picker = createPersianCalenderDialog();
                picker.setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        birthday = checkDateOfBirthFormat(persianCalendar.getPersianYear(),
                                persianCalendar.getPersianMonth(),
                                persianCalendar.getPersianDay());

                        mDateOfBirthTextView.setText(birthday);
                        Toast.makeText(getContext(), birthday, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDismissed() {
                    }
                });
                picker.show();
            }
        });
    }

    //when we post fields to the server,its response will be profile object too
    @Override
    public void showProfileFields(Profile profile) {
        Log.i("shoo", "showProfileFields: " + profile.getData().getGender());
    }

    @Override
    public void showError(String error) {
        Log.i("profile error", "showError: " + error);
    }

    @Override
    public void showProfilePhoto(String avatar) {
        Log.i("avatar", "showProfilePhoto: " + avatar);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void getFilePath(String filePath, Uri uri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),
                    uri);
            Glide.with(getContext()).load(bitmap)
                    .into(mAvatarImageView);
            mSelectImageDialog.dismiss();

        } catch (IOException e) {
            e.printStackTrace();
        }

        mPresenter.uploadProfilePhoto(filePath, db.userDao().getUserTokenFromDatabase());
    }

    public void setProfileFieldsFromDatabase(UserDatabase db) {

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
                mDateOfBirthTextView.setText(user.getBirthday());
            }
        }

    }

    //1397/01/01
    public String checkDateOfBirthFormat(int year, int month, int day) {
        String mm = "", dd = "";
        if (month < 10) {
            mm = "0" + String.valueOf(month);
        } else mm = String.valueOf(month);
        if (day < 10) {
            dd = "0" + String.valueOf(day);
        } else dd = String.valueOf(day);

        String date = String.valueOf(year) + "/" + mm + "/" + dd;
        return date;
    }

    public PersianDatePickerDialog createPersianCalenderDialog() {
        PersianDatePickerDialog picker = new PersianDatePickerDialog(getContext())
                .setPositiveButtonString("باشه")
                .setNegativeButton("بیخیال")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setMinYear(1300)
                .setActionTextColor(Color.GRAY);

        return picker;
    }
}
