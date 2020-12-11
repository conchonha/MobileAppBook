package com.example.mobileappbook.src.repositories.acount;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.async.acount.ActiveAcountAsync;
import com.example.mobileappbook.async.acount.AsyncLogin;
import com.example.mobileappbook.async.acount.AsyncRecover;
import com.example.mobileappbook.async.acount.AsyncRegister;
import com.example.mobileappbook.async.setting.ChangeProfileAsync;
import com.example.mobileappbook.async.setting.LogoutAsync;
import com.example.mobileappbook.async.acount.ResetPasswordAsync;
import com.example.mobileappbook.async.setting.SecurityAsync;
import com.example.mobileappbook.cores.body.ActiveAcountBody;
import com.example.mobileappbook.cores.body.LoginBody;
import com.example.mobileappbook.cores.body.RegisterBody;
import com.example.mobileappbook.cores.body.ResetPasswordBody;
import com.example.mobileappbook.cores.body.UserInfoBody;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;

import java.util.Map;

public class AcountRepositories {
    private MutableLiveData<UserReponse> mLoginReponse = new MutableLiveData<>();
    private MutableLiveData<UserReponse> mRegisterReponse = new MutableLiveData<>();
    private MutableLiveData<UserReponse> mActiveReponse = new MutableLiveData<>();
    private MutableLiveData<Map> mRecoverPasswordReponse = new MutableLiveData<>();
    private MutableLiveData<UserReponse>mRessetPasswordReponse = new MutableLiveData<>();
    private MutableLiveData<UserReponse>mChangeProfileReponse = new MutableLiveData<>();
    private MutableLiveData<Map>mUserLogoutReponse = new MutableLiveData<>();
    private MutableLiveData<UserReponse>mChangePasswordReponse = new MutableLiveData<>();

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

    //-------------------------change profile
    public void changeProfile(UserInfoBody userInfoBody,String token){
        ChangeProfileAsync async = new ChangeProfileAsync(this,userInfoBody,token);
        async.execute();
    }

    public LiveData<UserReponse> getChangeProfileReponse() {
        return mChangeProfileReponse;
    }

    public void setChangeProfileReponse(UserReponse userReponse) {
        mChangeProfileReponse.setValue(userReponse);
    }

    //-------------------------user logout
    public void userLogout(String token){
        LogoutAsync async = new LogoutAsync(this,token);
        async.execute();
    }

    public LiveData<Map> getUserLogoutReponse() {
        return mUserLogoutReponse;
    }

    public void setUserLogoutReponse(Map map) {
        mUserLogoutReponse.setValue(map);
    }

    //-------------------------user Change password
    public void userChangePassword(String pass,String newPass,String token){
        SecurityAsync async = new SecurityAsync(this,token,pass,newPass);
        async.execute();
    }

    public LiveData<UserReponse> getChangePasswordReponse() {
        return mChangePasswordReponse;
    }

    public void setChangePasswordReponse(UserReponse userLogoutReponse) {
        mChangePasswordReponse.setValue(userLogoutReponse);
    }
}
