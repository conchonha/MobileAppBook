package com.example.mobileappbook.src.page.setting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.mobileappbook.R;
import com.example.mobileappbook.src.viewmodel.setting.UserAvatarViewModel;

import java.io.File;
import java.io.FileNotFoundException;

public class UserAvatarActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "UserAvatarActivity";
    private final int REQUEST_IMAGE_BACKGROUND_CAPTURE = 1;
    private final int REQUEST_IMAGE_BACKGROUND_LIBRALY = 2;
    private ImageView mImageBackground;
    private UserAvatarViewModel mUserAvatarViewModel;
    private File mFileImage;
    private Uri mUriBackground;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_avatar);
        initView();
        initViewmodel();
        init();
        listenerOnclicked();
    }

    private void init() {
        String s = mUserAvatarViewModel.getBackgroundImg();
        if(s != null){
            Log.d(TAG, "onChanged: "+s);
            byte[]array = Base64.decode(s,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(array,0,array.length);
            mImageBackground.setImageBitmap(bitmap);
        }
    }

    //khoi tao viewmodel
    private void initViewmodel() {
        mUserAvatarViewModel = ViewModelProviders.of(this).get(UserAvatarViewModel.class);
    }

    //anh xa view
    private void initView() {
        mImageBackground = findViewById(R.id.img_background);
    }

    private void listenerOnclicked() {
        findViewById(R.id.img_back).setOnClickListener(this);
        findViewById(R.id.btn_backgound_camera).setOnClickListener(this);
        findViewById(R.id.btn_backgound_liblary).setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            switch (requestCode){
                case REQUEST_IMAGE_BACKGROUND_CAPTURE:
                    Bundle extras = data.getExtras();
                    Bitmap bitmap = (Bitmap) extras.get("data");
                    mImageBackground.setImageBitmap(bitmap);
                    mUserAvatarViewModel.saveBackgroundImg(mUserAvatarViewModel.imageToString(bitmap));
                    break;
                case REQUEST_IMAGE_BACKGROUND_LIBRALY:
                    Bitmap bitmap1 = null;
                    try {
                        bitmap1 = BitmapFactory.decodeStream(getContentResolver().openInputStream(data.getData()));
                        mImageBackground.setImageBitmap(bitmap1);
                        mUserAvatarViewModel.saveBackgroundImg(mUserAvatarViewModel.imageToString(bitmap1));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_backgound_camera:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_IMAGE_BACKGROUND_CAPTURE);
                break;
            case R.id.btn_backgound_liblary:
                Intent intent1 = new Intent(Intent.ACTION_PICK).setType("image/*");
                startActivityForResult(intent1, REQUEST_IMAGE_BACKGROUND_LIBRALY);
                break;
        }
    }
}
