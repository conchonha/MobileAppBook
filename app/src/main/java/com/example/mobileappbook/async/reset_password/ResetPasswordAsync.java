package com.example.mobileappbook.async.reset_password;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mobileappbook.cores.body.ResetPasswordBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;
import com.example.mobileappbook.cores.services.APIServices;
import com.example.mobileappbook.cores.services.DataService;
import com.example.mobileappbook.src.repositories.acount.reset_password.ResetPasswordRepositories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordAsync extends AsyncTask<Void,Void,Void> {
    private ResetPasswordRepositories mResetPasswordRepositories;
    private ResetPasswordBody mResetPasswordBody;
    private String TAG ="ResetPasswordAsync";

    public ResetPasswordAsync(ResetPasswordRepositories mResetPasswordRepositories, ResetPasswordBody mResetPasswordBody) {
        this.mResetPasswordRepositories = mResetPasswordRepositories;
        this.mResetPasswordBody = mResetPasswordBody;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        DataService dataService = APIServices.getService();
        Call<UserReponse>call = dataService.resetPassword(mResetPasswordBody);
        call.enqueue(new Callback<UserReponse>() {
            @Override
            public void onResponse(Call<UserReponse> call, Response<UserReponse> response) {
                Log.d(TAG, "onResponse: "+response.toString());
                if(response.isSuccessful()){
                    mResetPasswordRepositories.setmReponseResetPassword(response.body());
                }else{
                    Log.d(TAG, "onResponse: code -"+response.hashCode());
                    Log.d(TAG, "onResponse: message -"+response.message());
                    ErrorRepone errorRepone = new ErrorRepone(response.code(),response.message());
                    mResetPasswordRepositories.setmErrorReponseResetPassword(errorRepone);
                }
            }

            @Override
            public void onFailure(Call<UserReponse> call, Throwable t) {
                Log.d(TAG, "onResponse: code -"+t.hashCode());
                Log.d(TAG, "onResponse: message -"+t.getMessage());
                ErrorRepone errorRepone = new ErrorRepone(t.hashCode(),t.getMessage());
                mResetPasswordRepositories.setmErrorReponseResetPassword(errorRepone);
            }
        });
        return null;
    }
}
