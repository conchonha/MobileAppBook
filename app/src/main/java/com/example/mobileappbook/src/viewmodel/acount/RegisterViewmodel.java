package com.example.mobileappbook.src.viewmodel.acount;

import android.app.Application;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.cores.body.RegisterBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;
import com.example.mobileappbook.src.repositories.acount.RegisterRepositories;
import com.example.mobileappbook.utils.Validations;

public class RegisterViewmodel extends AndroidViewModel {
    private RegisterRepositories mRegisterRepositories;
    private RegisterBody mRegisterBody = null;
    private MutableLiveData<Boolean> checkLoadingDialog = new MutableLiveData<>();
    private String TAG = "RegisterViewmodel";

    public RegisterViewmodel(@NonNull Application application) {
        super(application);
         if(mRegisterRepositories == null){
             mRegisterRepositories = new RegisterRepositories();
         }
    }

    public void register() {
        mRegisterRepositories.register(mRegisterBody);
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
        mRegisterBody = new RegisterBody(yourName.getText().toString(), email.getText().toString(), pass.getText().toString(), phoneNumber.getText().toString(), address.getText().toString(), " ", " ");
        return true;
    }

    public LiveData<UserReponse> getReponseRegister() {
        return mRegisterRepositories.getReponeRegister();
    }

    public LiveData<ErrorRepone> getErroreponse() {
        return mRegisterRepositories.getErrorReponse();
    }
}
