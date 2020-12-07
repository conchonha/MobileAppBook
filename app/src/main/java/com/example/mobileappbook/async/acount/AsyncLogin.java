package com.example.mobileappbook.async.acount;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mobileappbook.cores.body.LoginBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;
import com.example.mobileappbook.cores.services.APIServices;
import com.example.mobileappbook.cores.services.DataService;
import com.example.mobileappbook.src.repositories.acount.login.LoginRepositories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsyncLogin extends AsyncTask<Void,Void,Void> {
    private LoginRepositories mLoginRepositories;
    private LoginBody mLoginBody;
    private String TAG = "AsyncLogin";

    public AsyncLogin(LoginRepositories loginRepositories,LoginBody loginBody){
        this.mLoginRepositories = loginRepositories;
        this.mLoginBody = loginBody;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        DataService dataService = APIServices.getService();
        Call<UserReponse>callback = dataService.sendLogin(mLoginBody);

        callback.enqueue(new Callback<UserReponse>() {
            @Override
            public void onResponse(Call<UserReponse> call, Response<UserReponse> response) {
                Log.d(TAG, "onResponse: "+response.toString());
                if(response.isSuccessful()){
                    UserReponse userReponse = response.body();
                    userReponse.setmAuthToken(response.headers().get("Auth-token"));
                    Log.d(TAG, "onResponse: auth-token -"+ userReponse.getmAuthToken());
                    mLoginRepositories.setmUserReponse(userReponse);
                }else{
                    ErrorRepone errorRepone = new ErrorRepone(response.hashCode(),response.message());
                    mLoginRepositories.setmErroReponse(errorRepone);
                }
            }

            @Override
            public void onFailure(Call<UserReponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
                ErrorRepone errorRepone = new ErrorRepone(t.hashCode(),t.getMessage());
                mLoginRepositories.setmErroReponse(errorRepone);
            }
        });
        return null;
    }
}
