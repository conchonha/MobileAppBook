package com.example.mobileappbook.src.repositories.acount;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.async.acount.ActiveAcountAsync;
import com.example.mobileappbook.async.acount.AsyncLogin;
import com.example.mobileappbook.async.acount.AsyncRecover;
import com.example.mobileappbook.async.acount.AsyncRegister;
import com.example.mobileappbook.async.acount.ResetPasswordAsync;
import com.example.mobileappbook.cores.body.ActiveAcountBody;
import com.example.mobileappbook.cores.body.LoginBody;
import com.example.mobileappbook.cores.body.RegisterBody;
import com.example.mobileappbook.cores.body.ResetPasswordBody;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;

import java.util.Map;

public class AcountRepositories {
    private MutableLiveData<UserReponse> mLoginReponse = new MutableLiveData<>();
    private MutableLiveData<UserReponse> mRegisterReponse = new MutableLiveData<>();
    private MutableLiveData<UserReponse> mActiveReponse = new MutableLiveData<>();
    private MutableLiveData<Map> mRecoverPasswordReponse = new MutableLiveData<>();
    private MutableLiveData<UserReponse>mRessetPasswordReponse = new MutableLiveData<>();

    //---------------------login
    public void login(LoginBody loginBody){
        AsyncLogin asyncLogin = new AsyncLogin(this,loginBody);
        asyncLogin.execute();
    }

    public LiveData<UserReponse> getLoginReponse() {
        return mLoginReponse;
    }

    public void setLoginReponse(UserReponse userReponse) {
        mLoginReponse.setValue(userReponse);
    }

    //-------------------------register
    public void register(RegisterBody registerBody) {
        AsyncRegister asyncRegister = new AsyncRegister(registerBody, this);
        asyncRegister.execute();
    }

    public LiveData<UserReponse> getRegisterReponse() {
        return mRegisterReponse;
    }

    public void setRegisterReponse(UserReponse userReponse) {
        mRegisterReponse.setValue(userReponse);
    }

    //-------------------------active
    public void activeAcount(ActiveAcountBody activeAcountBody){
        ActiveAcountAsync activeAcountAsync = new ActiveAcountAsync(this,activeAcountBody);
        activeAcountAsync.execute();
    }

    public LiveData<UserReponse> getActiveReponse() {
        return mActiveReponse;
    }

    public void setActiveReponse(UserReponse userReponse) {
        mActiveReponse.setValue(userReponse);
    }

    //-------------------------recoverPassword
    public void recoverPassword(String email){
        AsyncRecover asyncRecover = new AsyncRecover(this,email);
        asyncRecover.execute();
    }

    public LiveData<Map> getRecoverPasswordReponse() {
        return mRecoverPasswordReponse;
    }

    public void setRecoverPasswordReponse(Map map) {
        mRecoverPasswordReponse.setValue(map);
    }

    //-------------------------reset password
    public void resetPassword(ResetPasswordBody resetPasswordBody){
        ResetPasswordAsync async = new ResetPasswordAsync(this,resetPasswordBody);
        async.execute();
    }

    public LiveData<UserReponse> getResetPasswordReponse() {
        return mRessetPasswordReponse;
    }

    public void setResetPasswordReponse(UserReponse userReponse) {
        mRessetPasswordReponse.setValue(userReponse);
    }
}
