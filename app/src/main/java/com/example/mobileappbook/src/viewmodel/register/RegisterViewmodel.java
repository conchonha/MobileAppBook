package com.example.mobileappbook.src.viewmodel.register;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileappbook.cores.body.RegisterBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.register_reponse.RegisterReponse;
import com.example.mobileappbook.src.repositories.register.RegisterRepositories;

public class RegisterViewmodel extends AndroidViewModel {
    private RegisterRepositories mRegisterRepositories;

    public RegisterViewmodel(@NonNull Application application) {
        super(application);
        if(mRegisterRepositories == null){
            mRegisterRepositories = new RegisterRepositories();
        }
    }

    public void register(RegisterBody registerBody){
        mRegisterRepositories.register(registerBody);
    }

    public LiveData<RegisterReponse>getReponseRegister(){
        return mRegisterRepositories.getReponeRegister();
    }

    public LiveData<ErrorRepone>getErrorReponse(){
        return mRegisterRepositories.getErrorReponse();
    }
}
