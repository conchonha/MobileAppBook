package com.example.mobileappbook.src.repositories.acount.active_acount;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.async.active_acount.ActiveAcountAsync;
import com.example.mobileappbook.cores.body.ActiveAcountBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;

public class ActiveAcountRepositories {
    private MutableLiveData<ErrorRepone>mErrorReponse = new MutableLiveData<>();
    private MutableLiveData<UserReponse>mDataReponse = new MutableLiveData<>();

    public void activeAcount(ActiveAcountBody activeAcountBody){
        ActiveAcountAsync activeAcountAsync = new ActiveAcountAsync(this,activeAcountBody);
        activeAcountAsync.execute();
    }

    public void setmDataReponse(UserReponse userReponse) {
        this.mDataReponse.setValue(userReponse);
    }

    public void setmErrorReponse(ErrorRepone errorReponse) {
        this.mErrorReponse.setValue(errorReponse);
    }

    public LiveData<ErrorRepone> getmErrorReponse() {
        return mErrorReponse;
    }

    public LiveData<UserReponse> getmDataReponse() {
        return mDataReponse;
    }
}
