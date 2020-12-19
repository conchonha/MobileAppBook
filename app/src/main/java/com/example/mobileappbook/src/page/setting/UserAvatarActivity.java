package com.example.mobileappbook.src.page.setting;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.src.page.tabbar.TabBarActivity;
import com.example.mobileappbook.src.viewmodel.setting.UserAvatarViewModel;
import com.example.mobileappbook.utils.Constain;
import com.example.mobileappbook.utils.Helpers;
import com.example.mobileappbook.utils.SharePrefs;
import com.google.gson.Gson;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserAvatarActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImageBackground, mImageAvatar;
    private Dialog mDialog;
    //variable
    private String TAG = "UserAvatarActivity";
    private final int REQUEST_IMAGE_BACKGROUND_CAPTURE = 1;
    private final int REQUEST_IMAGE_BACKGROUND_LIBRALY = 2;
    private final int REQUEST_IMAGE_AVATAR_CAMERA = 3;
    private final int REQUEST_IMAGE_AVATAR_LIBLARY = 4;
    private final int REQUEST_CODE_PERMISSION_STORAGE = 100;
    private UserAvatarViewModel mUserAvatarViewModel;
    private File mFileImage;
    private UserReponse mUserReponse;
    private final String[] mPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private boolean mCheckBackground = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_avatar);
        ActivityCompat.requestPermissions(this, mPermissions, REQUEST_CODE_PERMISSION_STORAGE);
        initView();
        initViewmodel();
        init();
    }

    private void init() {
        mUserReponse = new Gson().fromJson(new SharePrefs(getApplicationContext()).getUser(), UserReponse.class);
        String s = mUserAvatarViewModel.getBackgroundImg();
        if (s != null) {
            byte[] array = Base64.decode(s, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(array, 0, array.length);
            mImageBackground.setImageBitmap(bitmap);
        }
        Picasso.get().load(Constain.userUrlImg + mUserReponse.getImage()).placeholder(R.drawable.useravatar).error(R.drawable.useravatar).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(mImageAvatar);
    }

    //khoi tao viewmodel
    private void initViewmodel() {
        mUserAvatarViewModel = ViewModelProviders.of(this).get(UserAvatarViewModel.class);
        //lang nghe va quan sat su thay doi cua du lieu
        mUserAvatarViewModel.getUserChangeAvatarReponse().observe(this, new Observer<UserReponse>() {
            @Override
            public void onChanged(UserReponse userReponse) {
                if(userReponse.getMessage() != null){
                    Toast.makeText(UserAvatarActivity.this, userReponse.getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UserAvatarActivity.this, "Change avatar success", Toast.LENGTH_SHORT).show();
                    mUserAvatarViewModel.saveUser(userReponse);
                    Helpers.intentClearOnTapSelected4(UserAvatarActivity.this, TabBarActivity.class);
                }
            }
        });
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
        findViewById(R.id.btn_avatar_camera).setOnClickListener(this);
        findViewById(R.id.btn_avatar_liblary).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
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
                    mUserAvatarViewModel.saveBackgroundImg(Helpers.imageToString(bitmap));
                    break;
                case REQUEST_IMAGE_BACKGROUND_LIBRALY:
                    Bitmap bitmap1 = null;
                    try {
                        bitmap1 = BitmapFactory.decodeStream(getContentResolver().openInputStream(data.getData()));
                        mImageBackground.setImageBitmap(bitmap1);
                        mUserAvatarViewModel.saveBackgroundImg(Helpers.imageToString(bitmap1));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case REQUEST_IMAGE_AVATAR_CAMERA:
                    final Bitmap photo = (Bitmap) data.getExtras().get("data");
                    mImageAvatar.setImageBitmap(photo);
                    mFileImage = Helpers.savebitmap(photo);
                    break;
                case REQUEST_IMAGE_AVATAR_LIBLARY:
                    Bitmap bitmap2 = null;
                    try {
                        bitmap2 = BitmapFactory.decodeStream(getContentResolver().openInputStream(data.getData()));
                        mImageAvatar.setImageBitmap(bitmap2);
                        mFileImage = Helpers.savebitmap(bitmap2);
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
                mCheckBackground = true;
                break;
            case R.id.btn_backgound_liblary:
                Intent intent1 = new Intent(Intent.ACTION_PICK).setType("image/*");
                startActivityForResult(intent1, REQUEST_IMAGE_BACKGROUND_LIBRALY);
                mCheckBackground = true;
                break;
            case R.id.btn_avatar_camera:
                mCheckBackground = false;
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent2, REQUEST_IMAGE_AVATAR_CAMERA);
                break;
            case R.id.btn_avatar_liblary:
                mCheckBackground = false;
                Intent intent3 = new Intent(Intent.ACTION_PICK).setType("image/*");
                startActivityForResult(intent3, REQUEST_IMAGE_AVATAR_LIBLARY);
                break;
            case R.id.btn_update:
                mDialog = Helpers.showLoadingDialog(this);
                mDialog.show();
                if(mCheckBackground){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mDialog.dismiss();
                            Toast.makeText(UserAvatarActivity.this, "Update background success", Toast.LENGTH_SHORT).show();
                            Helpers.intentClearOnTapSelected4(UserAvatarActivity.this, TabBarActivity.class);
                        }
                    },3000);
                }else{
                    if (mFileImage != null) {
                        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/jpg"), mFileImage);
                        MultipartBody.Part part = MultipartBody.Part.createFormData("image", mFileImage.getName(), fileReqBody);
                        mUserAvatarViewModel.userChangeAvatar(part);
                    }else{
                        mDialog.dismiss();
                        Toast.makeText(this, "Vui lòng chọn tối thiểu một trường", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSION_STORAGE) {
            boolean check = false;
            for (int i = 0; i < mPermissions.length; i++) {
                String permissio = mPermissions[i];
                int grantResult = grantResults[i];

                if (permissio.equals(Manifest.permission.READ_EXTERNAL_STORAGE) || permissio.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        check = true;
                    } else {
                        requestPermissions(new String[]{permissio}, REQUEST_CODE_PERMISSION_STORAGE);
                    }
                }
            }
            if(check){
                listenerOnclicked();
            }else{
                Toast.makeText(this, "Please grant permission to the app\n", Toast.LENGTH_SHORT).show();
            }
        }
    }
}