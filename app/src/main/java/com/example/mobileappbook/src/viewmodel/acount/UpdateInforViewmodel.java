package com.example.mobileappbook.src.viewmodel.acount;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileappbook.cores.body.UserInfoBody;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.src.repositories.acount.AcountRepositories;
import com.example.mobileappbook.utils.SharePrefs;
import com.google.gson.Gson;

public class UpdateInforViewmodel extends AndroidViewModel {
    private SharePrefs mSharePrefs;
    private AcountRepositories mAcountRepositories;
    private Gson mGson = new Gson();

    public UpdateInforViewmodel(@NonNull Application application) {
        super(application);
        mSharePrefs = new SharePrefs(getApplication());
        if(mAcountRepositories == null){
            mAcountRepositories = new AcountRepositories();
        }
    }

    public UserReponse getUserLocal(){
        return mGson.fromJson(mSharePrefs.getUser(),UserReponse.class);
    }

    public void saveUserLocal(UserReponse userReponse){
        mSharePrefs.saveUser(userReponse);
    }

    public void updateUserInfo(UserInfoBody userInfoBody,String token){
        mAcountRepositories.changeProfile(userInfoBody,token);
    }

    public LiveData<UserReponse>getChangeProfileReponse(){
        return mAcountRepositories.getChangeProfileReponse();
    }
}
