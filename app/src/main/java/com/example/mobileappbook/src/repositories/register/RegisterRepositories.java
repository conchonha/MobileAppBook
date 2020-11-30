package com.example.mobileappbook.src.repositories.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.async.AsyncRegister;
import com.example.mobileappbook.cores.body.RegisterBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.register_reponse.RegisterReponse;

public class RegisterRepositories {
    private MutableLiveData<RegisterReponse>mReponeRegister = new MutableLiveData<>();
    private MutableLiveData<ErrorRepone>mErroreponseRegister = new MutableLiveData<>();

    public void register(RegisterBody registerBody){
        AsyncRegister asyncRegister = new AsyncRegister(registerBody,this);
        asyncRegister.execute();
    }

    public MutableLiveData getReponeRegister(){
        return mReponeRegister;
    }

    public void setmReponeRegister(RegisterReponse reponeRegister) {
        mReponeRegister.setValue(reponeRegister);
    }

    public void setErrorReponse(ErrorRepone errorReponse){
        mErroreponseRegister.setValue(errorReponse);
    }

    public LiveData<ErrorRepone>getErrorReponse(){
        return mErroreponseRegister;
    }
}
