package com.example.mobileappbook.src.viewmodel.acount;

import android.app.Application;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileappbook.cores.body.LoginBody;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.src.repositories.acount.AcountRepositories;
import com.example.mobileappbook.utils.SharePrefs;
import com.example.mobileappbook.utils.Validations;

public class LoginViewmodel extends AndroidViewModel {
    private AcountRepositories mLoginRepositories;
    private LoginBody mLoginBody;
    private SharePrefs mSharePrefs = new SharePrefs(getApplication());

    public LoginViewmodel(@NonNull Application application) {
        super(application);
        if(mLoginRepositories == null){
            mLoginRepositories = new AcountRepositories();
        }
    }

    public void saveUser(UserReponse userReponse){
        mSharePrefs.saveUser(userReponse);
    }

    public void login(){
        mLoginRepositories.login(mLoginBody);
    }

    public LiveData<UserReponse>getUserReponse(){
        return mLoginRepositories.getLoginReponse();
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
