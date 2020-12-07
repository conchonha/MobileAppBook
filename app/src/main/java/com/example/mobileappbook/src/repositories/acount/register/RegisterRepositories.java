package com.example.mobileappbook.src.repositories.acount.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.async.acount.AsyncRegister;
import com.example.mobileappbook.cores.body.RegisterBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;

public class RegisterRepositories {
    private MutableLiveData<UserReponse> mReponeRegister = new MutableLiveData<>();
    private MutableLiveData<ErrorRepone> mErroreponseRegister = new MutableLiveData<>();

    public void register(RegisterBody registerBody) {
        AsyncRegister asyncRegister = new AsyncRegister(registerBody, this);
        asyncRegister.execute();
    }

    public LiveData<UserReponse> getReponeRegister() {
        return mReponeRegister;
    }

    public void setmReponeRegister(UserReponse userReponse) {
        mReponeRegister.setValue(userReponse);
    }

    public void setErrorReponse(ErrorRepone errorReponse) {
        mErroreponseRegister.setValue(errorReponse);
    }

    public LiveData<ErrorRepone> getErrorReponse() {
        return mErroreponseRegister;
    }
}
