package com.example.mobileappbook.src.viewmodel.acount;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileappbook.cores.body.ActiveAcountBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;
import com.example.mobileappbook.src.repositories.acount.ActiveAcountRepositories;

public class ActiveAcountViewModel extends AndroidViewModel {
    private ActiveAcountRepositories mActiveAcountRepositories;

    public ActiveAcountViewModel(@NonNull Application application) {
        super(application);
        if(mActiveAcountRepositories == null){
            mActiveAcountRepositories = new ActiveAcountRepositories();
        }
    }

    public void activeAcount(ActiveAcountBody activeAcountBody){
        mActiveAcountRepositories.activeAcount(activeAcountBody);
    }

    public LiveData<UserReponse>getDataReponseActiveAcount(){
        return mActiveAcountRepositories.getmDataReponse();
    }

    public LiveData<ErrorRepone>getErrorReponse(){
        return mActiveAcountRepositories.getmErrorReponse();
    }
}
