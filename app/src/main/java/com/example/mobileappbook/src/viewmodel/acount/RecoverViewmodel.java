package com.example.mobileappbook.src.viewmodel.acount;

import android.app.Application;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileappbook.src.repositories.acount.AcountRepositories;
import com.example.mobileappbook.utils.Validations;

import java.util.HashMap;
import java.util.Map;

public class RecoverViewmodel extends AndroidViewModel {
    private AcountRepositories mAcountRepositories;

    public RecoverViewmodel(@NonNull Application application) {
        super(application);
        if(mAcountRepositories == null){
            mAcountRepositories = new AcountRepositories();
        }
    }

    public LiveData<Map>getReponseRecover(){
        return mAcountRepositories.getRecoverPasswordReponse();
    }

    public void recoverPassword(EditText editText){
        if(Validations.isEmailValid(editText.getText().toString())){
            editText.setError("Email invalid");
            Map map = new HashMap();
            map.put("300","Email invalid");
            mAcountRepositories.setRecoverPasswordReponse(map);
        }else{
            mAcountRepositories.recoverPassword(editText.getText().toString());
        }
    }
}
