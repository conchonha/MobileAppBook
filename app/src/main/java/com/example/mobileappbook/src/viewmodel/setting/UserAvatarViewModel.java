package com.example.mobileappbook.src.viewmodel.setting;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import com.example.mobileappbook.utils.SharePrefs;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;

public class UserAvatarViewModel extends AndroidViewModel {
    private SharePrefs mSharePrefs = new SharePrefs(getApplication());
    private Gson mGson = new Gson();

    public UserAvatarViewModel(@NonNull Application application) {
        super(application);
    }

    public void saveBackgroundImg(String img){
        mSharePrefs.saveBackgoundImage(img);
    }

    public String getBackgroundImg(){
        return mSharePrefs.getBackgoundImage();
    }

    public String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[]hinhanh=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(hinhanh,Base64.DEFAULT);
    }
}
