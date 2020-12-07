package com.example.mobileappbook.src.repositories.acount;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.async.acount.AsyncLogin;
import com.example.mobileappbook.cores.body.LoginBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;

public class LoginRepositories {
    private MutableLiveData<ErrorRepone>mErroReponse = new MutableLiveData<>();
    private MutableLiveData<UserReponse>mUserReponse = new MutableLiveData<>();

    public void login(LoginBody loginBody){
        AsyncLogin asyncLogin = new AsyncLogin(this,loginBody);
        asyncLogin.execute();
    }

    public LiveData<ErrorRepone> getmErroReponse() {
        return mErroReponse;
    }

    public void setmErroReponse(ErrorRepone erroReponse) {
        mErroReponse.setValue(erroReponse);
    }

    public LiveData<UserReponse> getmUserReponse() {
        return mUserReponse;
    }

    public void setmUserReponse(UserReponse userReponse) {
        mUserReponse.setValue(userReponse);
    }
}
