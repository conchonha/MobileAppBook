package com.example.mobileappbook.src.viewmodel.setting;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileappbook.src.repositories.acount.AcountRepositories;

import java.util.Map;

public class SettingViewModel extends AndroidViewModel {
    private AcountRepositories mAcountRepositories;

    public SettingViewModel(@NonNull Application application) {
        super(application);
        if(mAcountRepositories == null){
            mAcountRepositories = new AcountRepositories();
        }
    }

    public void userLogout(String token){
        mAcountRepositories.userLogout(token);
    }

    public LiveData<Map>getUserLogoutReponse(){
        return mAcountRepositories.getUserLogoutReponse();
    }
}
