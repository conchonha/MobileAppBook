package com.example.mobileappbook.src.viewmodel.acount;

import android.app.Application;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileappbook.cores.body.ResetPasswordBody;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.src.repositories.acount.AcountRepositories;
import com.example.mobileappbook.utils.Validations;

public class ResetPasswordViewmodel extends AndroidViewModel {
    private AcountRepositories mAcountRepositories;
    private ResetPasswordBody mResetPasswordBody;

    public ResetPasswordViewmodel(@NonNull Application application) {
        super(application);
        if(mAcountRepositories == null){
            mAcountRepositories = new AcountRepositories();
        }
    }

    public void resetPassword(){
        mAcountRepositories.resetPassword(mResetPasswordBody);
    }

    public boolean checkValidation(EditText edtEmail,EditText edtToken,EditText edtNewPass){
        if(Validations.isEmailValid(edtEmail.getText().toString())){
            edtEmail.setError("Email invalidate");
            return false;
        }
        edtEmail.setError(null);

        if(edtToken.getText().toString().isEmpty()){
            edtToken.setError("Token invalidate");
            return false;
        }
        edtToken.setError(null);

        if(Validations.isPasswordValid(edtNewPass.getText().toString())){
            edtNewPass.setError("Password invalidate");
            return false;
        }
        edtNewPass.setError(null);
        mResetPasswordBody = new ResetPasswordBody(edtEmail.getText().toString(),edtToken.getText().toString(),edtNewPass.getText().toString());
        return true;
    }

    public LiveData<UserReponse>getmReponseResetPassword(){
        return mAcountRepositories.getResetPasswordReponse();
    }
}
