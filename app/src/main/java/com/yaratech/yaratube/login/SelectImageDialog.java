package com.yaratech.yaratube.login;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;


public class SelectImageDialog extends DialogFragment {

    private Button mGalleryButton;

    private static onFragmentInteractionListener mListener;


    public SelectImageDialog() {
        // Required empty public constructor
    }

    public static SelectImageDialog newInstance(onFragmentInteractionListener listener) {

        mListener = listener;
        Bundle args = new Bundle();

        SelectImageDialog fragment = new SelectImageDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_image_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGalleryButton = view.findViewById(R.id.gallery_btn);

        mGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGalleryIntent();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            Uri uri = createImageUri();
            String filePath = createSelectedImageFilePath(uri);
            mListener.getFilePath(filePath, data.getData());
        }
    }


    public void setGalleryIntent() {
        final Intent galleryIntent =
                new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        Uri cameraUri = createImageUri();
        galleryIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        galleryIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(galleryIntent, 101);
    }

    public Uri createImageUri() {
        ContentResolver contentResolver = getContext().getContentResolver();
        ContentValues cv = new ContentValues();
        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cv);
    }

    private String createSelectedImageFilePath(Uri selectedImage) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        android.database.Cursor cursor = getContext().getContentResolver().query(
                selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

}

interface onFragmentInteractionListener {
    void getFilePath(String filePath, Uri uri);
}
