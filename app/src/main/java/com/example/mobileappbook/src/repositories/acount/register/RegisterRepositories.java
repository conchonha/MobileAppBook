package com.example.mobileappbook.src.repositories.acount.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.async.register.AsyncRegister;
import com.example.mobileappbook.cores.body.RegisterBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;

import java.util.Map;

public class RegisterRepositories {
    private LiveData<Map> mReponeRegister = new MutableLiveData<>();
    private MutableLiveData<ErrorRepone> mErroreponseRegister = new MutableLiveData<>();

    public void register(RegisterBody registerBody) {
        AsyncRegister asyncRegister = new AsyncRegister(registerBody, this);
        asyncRegister.execute();
    }

    public LiveData<Map> getReponeRegister() {
        return mReponeRegister;
    }

    public void setmReponeRegister(LiveData<Map> reponeRegister) {
        mReponeRegister = reponeRegister;
    }

    public void setErrorReponse(ErrorRepone errorReponse) {
        mErroreponseRegister.setValue(errorReponse);
    }

    public LiveData<ErrorRepone> getErrorReponse() {
        return mErroreponseRegister;
    }
}
