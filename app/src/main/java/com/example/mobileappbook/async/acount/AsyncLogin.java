package com.example.mobileappbook.async.acount;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mobileappbook.cores.body.LoginBody;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.cores.services.APIServices;
import com.example.mobileappbook.cores.services.DataService;
import com.example.mobileappbook.src.repositories.acount.AcountRepositories;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsyncLogin extends AsyncTask<Void,Void,Void> {
    private AcountRepositories mAcountRepositories;
    private LoginBody mLoginBody;
    private UserReponse mUserReponse  = new UserReponse();
    private String TAG = "AsyncLogin";

    public AsyncLogin(AcountRepositories acountRepositories,LoginBody loginBody){
        this.mAcountRepositories = acountRepositories;
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
                    mUserReponse = response.body();
                    mUserReponse.setmAuthToken(response.headers().get("Auth-token"));
                    mAcountRepositories.setLoginReponse(mUserReponse);
                }else{
                    try {
                        String reponse = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(reponse);
                        mUserReponse.setMessage(jsonObject.getString("message"));
                        mAcountRepositories.setLoginReponse(mUserReponse);
                        Log.d(TAG, "onResponse: "+jsonObject.getString("message"));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserReponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
                mUserReponse.setMessage(t.getMessage());
                mAcountRepositories.setLoginReponse(mUserReponse);
            }
        });
        return null;
    }
}
