package com.example.mobileappbook.src.viewmodel.acount.register;

import android.app.Application;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.mobileappbook.cores.body.RegisterBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.error_reponse.register.DataError;
import com.example.mobileappbook.src.repositories.acount.register.RegisterRepositories;
import com.example.mobileappbook.utils.SharePrefs;
import com.example.mobileappbook.utils.Validations;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class RegisterViewmodel extends AndroidViewModel {
    private RegisterRepositories mRegisterRepositories;
    private SharePrefs mSharePrefs;
    private RegisterBody mRegisterBody = null;
    private MutableLiveData<Boolean>checkLoadingDialog = new MutableLiveData<>();
    private String TAG = "RegisterViewmodel";

    public RegisterViewmodel(@NonNull Application application) {
        super(application);
        mRegisterRepositories = new RegisterRepositories();
        mSharePrefs = new SharePrefs(application);
    }

    public void register() {
        mRegisterRepositories.register(mRegisterBody);
        mRegisterRepositories.getReponeRegister().observeForever(new Observer<Map>() {
            @Override
            public void onChanged(Map map) {
                 if(map.get("errors") != null){
                    Gson gson = new Gson();
                    DataError repone = gson.fromJson(gson.toJson(map,Map.class),DataError.class);
                    Log.d(TAG, "onChanged: "+repone.getListError().get(0).getMsg());
                }else if(map.get("message") != null){
                    Log.d(TAG, "onChanged: "+map.get("message"));
                }else {
                     Log.d(TAG, "onChanged: success");
                 }
            }
        });
    }

    //register
    public boolean checkValidationsRegister(EditText yourName, EditText phoneNumber, EditText address, EditText email, EditText pass, EditText confirm) {
        if (Validations.isValidName(yourName.getText().toString())) {
            yourName.setError("Name invalid");
            return false;
        }
        yourName.setError(null);

        if (!Validations.isValidPhoneNumber(phoneNumber.getText().toString())) {
            phoneNumber.setError("Phone invalid");
            return false;
        }
        phoneNumber.setError(null);

        if (Validations.isValidAddress(address.getText().toString())) {
            address.setError("Address invalid");
            return false;
        }
        address.setError(null);

        if (Validations.isEmailValid(email.getText().toString())) {
            email.setError("Email invalid");
            return false;
        }
        email.setError(null);

        if (Validations.isPasswordValid(pass.getText().toString()) || !String.valueOf(pass.getText().toString().charAt(0)).equals(String.valueOf(pass.getText().toString().charAt(0)).toUpperCase())) {
            pass.setError("Password invalid");
            return false;
        }
        pass.setError(null);

        if (!confirm.getText().toString().equals(pass.getText().toString())) {
            confirm.setError("Confirm invalid");
            return false;
        }
        mRegisterBody = new RegisterBody(yourName.getText().toString(),email.getText().toString(),pass.getText().toString(),phoneNumber.getText().toString(),address.getText().toString()," "," ");
        return true;
    }

    public LiveData<Map> getReponseRegister() {
        return mRegisterRepositories.getReponeRegister();
    }
}
