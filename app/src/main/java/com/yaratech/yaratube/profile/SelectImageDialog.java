package com.yaratech.yaratube.profile;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.theartofdev.edmodo.cropper.CropImage;
import com.yaratech.yaratube.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;


public class SelectImageDialog extends DialogFragment {

    private static final int CAMERA_CODE = 0;
    private static final int GALLERY_CODE = 1;

    private static final int PERMISSION_GALLERY = 2;
    private static final int PERMISSION_CAMERA = 3;
    String[] GALLERY_PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    String[] CAMERA_PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private String imageFilePath;

    private TextView mFromGalleryButton;
    private TextView mFromCameraButton;

    private static onProfileImageListener mImageListener;


    public SelectImageDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    public static SelectImageDialog newInstance(onProfileImageListener listener) {

        mImageListener = listener;
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
        mFromGalleryButton = view.findViewById(R.id.gallery_btn);
        mFromCameraButton = view.findViewById(R.id.camera_btn);

        mFromGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    if (!hasPermissions(getContext(), GALLERY_PERMISSIONS))
                        requestPermissions(GALLERY_PERMISSIONS, PERMISSION_GALLERY);
                    else
                        setGalleryIntent();
                }
                setGalleryIntent();
            }
        });

        mFromCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    if (!hasPermissions(getContext(), CAMERA_PERMISSIONS)) {
                        requestPermissions(CAMERA_PERMISSIONS, PERMISSION_CAMERA);
                    } else
                        setCameraIntent();
                } else {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, CAMERA_CODE);
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_GALLERY:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    setGalleryIntent();
                else
                    Log.e("gallery permission", "onRequestPermissionsResult: Permission Denied");
                break;

            case PERMISSION_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    setCameraIntent();
                else
                    Log.e("camera permission", "onRequestPermissionsResult: Permission Denied");
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GALLERY_CODE:
                if (resultCode == RESULT_OK)
                    cropImage(data.getData());
                mImageListener.onProfileImageSelected(createSelectedImageFilePath(data.getData()));
                break;

            case CAMERA_CODE:
                if (resultCode == RESULT_OK)
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        cropImage(Uri.fromFile(new File(imageFilePath)));
                        mImageListener.onProfileImageSelected(createSelectedImageFilePath(data.getData()));
                    } else {
                        cropImage(data.getData());
                        mImageListener.onProfileImageSelected(createSelectedImageFilePath(data.getData()));
                    }
                break;

            case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    mImageListener.onProfileImageSelected(createSelectedImageFilePath(resultUri));
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }
                getDialog().dismiss();
                break;
        }
    }


    public void setGalleryIntent() {
        final Intent galleryIntent =
                new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        Uri cameraUri = createImageUri();
        galleryIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        galleryIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(galleryIntent, GALLERY_CODE);
    }

    public Uri createImageUri() {
        ContentResolver contentResolver = getContext().getContentResolver();
        ContentValues cv = new ContentValues();
        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cv);
    }

    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }

    public void setCameraIntent() {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE
        );
        if (pictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        getContext().getPackageName() + ".provider",
                        photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        photoURI);
                startActivityForResult(pictureIntent,
                        CAMERA_CODE);
            }
        }
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void cropImage(Uri uri) {
        CropImage.activity(uri)
                .setOutputCompressQuality(50)
                .setFixAspectRatio(true)
                .start(getContext(), this);
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

interface onProfileImageListener {
    void onProfileImageSelected(String uri);
}
