package com.example.mobileappbook.src.repositories.recover;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.async.recover.AsyncRecover;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;

public class RecoverRepositories {
    MutableLiveData<ErrorRepone>mReponseRecover = new MutableLiveData<>();

    public void forgotPassword(String email){
        AsyncRecover asyncRecover = new AsyncRecover(this,email);
        asyncRecover.execute();
    }

    public void setDataReponseRecover(ErrorRepone errorRepone){
        mReponseRecover.setValue(errorRepone);
    }

    public LiveData<ErrorRepone> getDataReponseRecover(){
        return mReponseRecover;
    }
}
