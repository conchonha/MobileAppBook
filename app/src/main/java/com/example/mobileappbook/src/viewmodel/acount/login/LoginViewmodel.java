package com.example.mobileappbook.src.viewmodel.acount.login;

import android.app.Application;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileappbook.cores.body.LoginBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;
import com.example.mobileappbook.src.repositories.acount.login.LoginRepositories;
import com.example.mobileappbook.utils.SharePrefs;
import com.example.mobileappbook.utils.Validations;

public class LoginViewmodel extends AndroidViewModel {
    private LoginRepositories mLoginRepositories;
    private LoginBody mLoginBody;
    private SharePrefs mSharePrefs = new SharePrefs(getApplication());

    public LoginViewmodel(@NonNull Application application) {
        super(application);
        if(mLoginRepositories == null){
            mLoginRepositories = new LoginRepositories();
        }
    }

    public void saveUser(UserReponse userReponse){
        mSharePrefs.saveUser(userReponse);
    }

    public void login(){
        mLoginRepositories.login(mLoginBody);
    }

    public LiveData<UserReponse>getUserReponse(){
        return mLoginRepositories.getmUserReponse();
    }

    public LiveData<ErrorRepone>getErrorReponse(){
        return mLoginRepositories.getmErroReponse();
    }

    public boolean checkValidation(EditText edtEmail,EditText edtPass){
        if(Validations.isEmailValid(edtEmail.getText().toString())){
            edtEmail.setError("Email invalid");
            return false;
        }
        edtEmail.setError(null);

        if(Validations.isPasswordValid(edtPass.getText().toString())){
            edtPass.setError("Password invalid");
            return false;
        }
        edtPass.setError(null);
        mLoginBody = new LoginBody(edtEmail.getText().toString(),edtPass.getText().toString());
        return true;
    }
}
