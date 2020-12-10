package com.example.mobileappbook.src.viewmodel.acount;

import android.app.Application;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mobileappbook.cores.body.UserInfoBody;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.utils.SharePrefs;
import com.example.mobileappbook.utils.Validations;
import com.google.gson.Gson;

public class UpdateInforViewmodel extends AndroidViewModel {
    private SharePrefs mSharePrefs;
    private Gson mGson;
    private UserInfoBody mUserInfoBody;

    public UpdateInforViewmodel(@NonNull Application application) {
        super(application);
    }

    public UserReponse getUserLocal(){
        mSharePrefs = new SharePrefs(getApplication());
        mGson = new Gson();
        return mGson.fromJson(mSharePrefs.getUser(),UserReponse.class);
    }

    public void updateUserInfo(){

    }

    public boolean checkvalidation(EditText yourName,EditText phoneNumber,EditText address,EditText edtJob){
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

        if(edtJob.getText().toString().isEmpty()){
            edtJob.setError("Job invalid");
            return false;
        }
        mUserInfoBody = new UserInfoBody(yourName.getText().toString(), phoneNumber.getText().toString(), address.getText().toString(), edtJob.getText().toString());
        return true;
    }

}
