package com.example.mobileappbook.src.page.setting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.src.viewmodel.setting.UserAvatarViewModel;
import com.example.mobileappbook.utils.Constain;
import com.example.mobileappbook.utils.SharePrefs;
import com.google.gson.Gson;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;

public class UserAvatarActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImageBackground, mImageAvatar;
    //variable
    private String TAG = "UserAvatarActivity";
    private final int REQUEST_IMAGE_BACKGROUND_CAPTURE = 1;
    private final int REQUEST_IMAGE_BACKGROUND_LIBRALY = 2;
    private UserAvatarViewModel mUserAvatarViewModel;
    private File mFileImage;
    private UserReponse mUserReponse;

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
        mUserReponse = new Gson().fromJson(new SharePrefs(getApplicationContext()).getUser(), UserReponse.class);
        String s = mUserAvatarViewModel.getBackgroundImg();
        if (s != null) {
            byte[] array = Base64.decode(s, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(array, 0, array.length);
            mImageBackground.setImageBitmap(bitmap);
        } else {
            Picasso.get().load(Constain.userUrlImg + mUserReponse.getImage()).placeholder(R.drawable.useravatar).error(R.drawable.useravatar).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(mImageAvatar);
        }
    }

    //khoi tao viewmodel
    private void initViewmodel() {
        mUserAvatarViewModel = ViewModelProviders.of(this).get(UserAvatarViewModel.class);
    }

    //anh xa view
    private void initView() {
        mImageBackground = findViewById(R.id.img_background);
        mImageAvatar = findViewById(R.id.img_avatar);
    }

    private void listenerOnclicked() {
        findViewById(R.id.img_back).setOnClickListener(this);
        findViewById(R.id.btn_backgound_camera).setOnClickListener(this);
        findViewById(R.id.btn_backgound_liblary).setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
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
        switch (v.getId()) {
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
