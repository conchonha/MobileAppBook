package com.example.mobileappbook.src.viewmodel.setting;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.src.repositories.acount.AcountRepositories;
import com.example.mobileappbook.utils.SharePrefs;
import com.google.gson.Gson;

import okhttp3.MultipartBody;

public class UserAvatarViewModel extends AndroidViewModel {
    private SharePrefs mSharePrefs = new SharePrefs(getApplication());
    private Gson mGson = new Gson();
    private AcountRepositories mAcountRepositories;


    public UserAvatarViewModel(@NonNull Application application) {
        super(application);
        if(mAcountRepositories == null){
            mAcountRepositories = new AcountRepositories();
        }
    }

    public void saveBackgroundImg(String img){
        mSharePrefs.saveBackgoundImage(img);
    }

    public String getBackgroundImg(){
        return mSharePrefs.getBackgoundImage();
    }

    public void userChangeAvatar(MultipartBody.Part part){
        UserReponse userReponse = mGson.fromJson(mSharePrefs.getUser(),UserReponse.class);
        mAcountRepositories.userChangeAvatar(part,userReponse.getmAuthToken());
    }

    public LiveData<UserReponse>getUserChangeAvatarReponse(){
        return mAcountRepositories.getUserChangeAvatarReponse();
    }

    public void saveUser(UserReponse userReponse){
        mSharePrefs.saveUser(userReponse);
    }
}
