package com.example.mobileappbook.src.viewmodel.setting;

import android.app.Application;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.src.repositories.acount.AcountRepositories;
import com.example.mobileappbook.utils.SharePrefs;
import com.example.mobileappbook.utils.Validations;
import com.google.gson.Gson;

public class SecurityViewModel extends AndroidViewModel {
    private String mPass,mNewPass;
    private AcountRepositories mAcountRepositories;
    private SharePrefs mSharePrefs;

    public SecurityViewModel(@NonNull Application application) {
        super(application);
        if(mAcountRepositories == null){
            mAcountRepositories = new AcountRepositories();
            mSharePrefs = new SharePrefs(getApplication());
        }
    }
    public void changePassword(){
        UserReponse userReponse = new Gson().fromJson(mSharePrefs.getUser(),UserReponse.class);
        mAcountRepositories.userChangePassword(mPass,mNewPass,userReponse.getmAuthToken());
    }

    public LiveData<UserReponse>getUserChangePasswordReponse(){
        return mAcountRepositories.getChangePasswordReponse();
    }

    public void saveUser(UserReponse userReponse){
        mSharePrefs.saveUser(userReponse);
    }

    public boolean checkValidation(EditText edtPass, EditText edtNewPass, EditText edtConfirmPass) {
        if (Validations.isPasswordValid(edtPass.getText().toString())) {
            edtPass.setError("Password invalid");
            return false;
        }
        edtPass.setError(null);
        if (Validations.isPasswordValid(edtNewPass.getText().toString())) {
            edtNewPass.setError("Newpassword invalid");
            return false;
        }
        edtNewPass.setError(null);
        if (!edtConfirmPass.getText().toString().equals(edtNewPass.getText().toString())) {
            edtNewPass.setError("Confirm password invalid");
            return false;
        }
        edtConfirmPass.setError(null);
        mPass = edtPass.getText().toString();
        mNewPass = edtNewPass.getText().toString();
        return true;
    }

}
