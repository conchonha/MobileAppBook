package com.example.mobileappbook.src.viewmodel.acount;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileappbook.cores.body.ActiveAcountBody;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.src.repositories.acount.AcountRepositories;

public class ActiveAcountViewModel extends AndroidViewModel {
    private AcountRepositories mAcountRepositories;

    public ActiveAcountViewModel(@NonNull Application application) {
        super(application);
        if(mAcountRepositories == null){
            mAcountRepositories = new AcountRepositories();
        }
    }

    public void activeAcount(ActiveAcountBody activeAcountBody){
        mAcountRepositories.activeAcount(activeAcountBody);
    }

    public LiveData<UserReponse>getDataReponseActiveAcount(){
        return mAcountRepositories.getActiveReponse();
    }
}
