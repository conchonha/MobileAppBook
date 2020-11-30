package com.example.mobileappbook.src.viewmodel.login;

import android.app.Application;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileappbook.cores.body.LoginBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;
import com.example.mobileappbook.src.repositories.login.LoginRepositories;
import com.example.mobileappbook.utils.Validations;

public class LoginViewmodel extends AndroidViewModel {
    private LoginRepositories mLoginRepositories;

    public LoginViewmodel(@NonNull Application application) {
        super(application);
        if(mLoginRepositories == null){
            mLoginRepositories = new LoginRepositories();
        }
    }

    public void login(LoginBody loginBody){
        mLoginRepositories.login(loginBody);
    }

    public LiveData<UserReponse>getLoginReponse(){
        return mLoginRepositories.getLoginReponse();
    }

    public LiveData<ErrorRepone>getErrorReponse(){
        return mLoginRepositories.getErrorReponse();
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
        return true;
    }
}
