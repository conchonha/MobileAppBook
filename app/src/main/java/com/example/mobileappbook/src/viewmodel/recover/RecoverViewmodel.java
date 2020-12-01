package com.example.mobileappbook.src.viewmodel.recover;

import android.app.Application;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.src.repositories.recover.RecoverRepositories;
import com.example.mobileappbook.utils.Validations;

public class RecoverViewmodel extends AndroidViewModel {
    private RecoverRepositories mRecoverRepositories;

    public RecoverViewmodel(@NonNull Application application) {
        super(application);
        if(mRecoverRepositories == null){
            mRecoverRepositories = new RecoverRepositories();
        }
    }

    public void forgotPassword(String email){
        mRecoverRepositories.forgotPassword(email);
    }

    public LiveData<ErrorRepone>getReponseRecover(){
        return mRecoverRepositories.getDataReponseRecover();
    }

    public boolean checkValidation(EditText editText){
        if(Validations.isEmailValid(editText.getText().toString())){
            editText.setError("Email invalid");
            return false;
        }
        editText.setError(null);
        return true;
    }
}
