package com.example.mobileappbook.src.repositories.acount.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.async.login.AsyncLogin;
import com.example.mobileappbook.cores.body.LoginBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;

public class LoginRepositories {
    MutableLiveData<ErrorRepone>mErrorReponseLogin = new MutableLiveData<>();
    MutableLiveData<UserReponse>mLoginReponse = new MutableLiveData<>();

    public void login(LoginBody loginBody){
        AsyncLogin asyncLogin = new AsyncLogin(this,loginBody);
        asyncLogin.execute();
    }

    public void setLoginReponse(UserReponse userReponse){
        mLoginReponse.setValue(userReponse);
    }

    public LiveData<UserReponse>getLoginReponse(){
        return mLoginReponse;
    }

    public void setErrorReponse(ErrorRepone errorReponse){
        mErrorReponseLogin.setValue(errorReponse);
    }

    public LiveData<ErrorRepone>getErrorReponse(){
        return mErrorReponseLogin;
    }
}
