package com.example.mobileappbook.src.repositories.acount.reset_password;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.async.acount.ResetPasswordAsync;
import com.example.mobileappbook.cores.body.ResetPasswordBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;

public class ResetPasswordRepositories {
    private MutableLiveData<ErrorRepone>mErrorReponseResetPassword = new MutableLiveData<>();
    private MutableLiveData<UserReponse>mReponseResetPassword = new MutableLiveData<>();

    public void resetPassword(ResetPasswordBody resetPasswordBody){
        ResetPasswordAsync async = new ResetPasswordAsync(this,resetPasswordBody);
        async.execute();
    }

    public void setmErrorReponseResetPassword(ErrorRepone errorRepone){
        mErrorReponseResetPassword.setValue(errorRepone);
    }

    public void setmReponseResetPassword(UserReponse userReponse){
        mReponseResetPassword.setValue(userReponse);
    }

    public LiveData<ErrorRepone> getmErrorReponseResetPassword() {
        return mErrorReponseResetPassword;
    }

    public LiveData<UserReponse> getmReponseResetPassword() {
        return mReponseResetPassword;
    }
}
