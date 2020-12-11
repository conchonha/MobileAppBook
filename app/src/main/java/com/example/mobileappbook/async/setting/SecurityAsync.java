package com.example.mobileappbook.async.setting;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mobileappbook.cores.reponse.acount.ChangePasswordReponse;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.cores.services.APIServices;
import com.example.mobileappbook.cores.services.DataService;
import com.example.mobileappbook.src.repositories.acount.AcountRepositories;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecurityAsync extends AsyncTask<Void,Void,Void> {
    private AcountRepositories mAcountRepositories;
    private String mToken,mPassword,mNewPass;
    private UserReponse mUserReponse = new UserReponse();
    private String TAG = "SecurityAsync";

    public SecurityAsync(AcountRepositories acountRepositories,String token,String pass,String newPass){
        this.mAcountRepositories = acountRepositories;
        this.mToken = token;
        this.mPassword = pass;
        this.mNewPass = newPass;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        DataService dataService = APIServices.getService();
        Call<ChangePasswordReponse> callback = dataService.changePassword(mPassword,mPassword,mToken);

        callback.enqueue(new Callback<ChangePasswordReponse>() {
            @Override
            public void onResponse(Call<ChangePasswordReponse> call, Response<ChangePasswordReponse> response) {
                Log.d(TAG, "onResponse: "+response.toString());
                if(response.isSuccessful()){
                    mAcountRepositories.setChangePasswordReponse(response.body().getmUser());
                    Log.d(TAG, "onResponse: "+new Gson().toJson(response.body()));
                }else{
                    try {
                        String reponse = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(reponse);
                        mUserReponse.setMessage(jsonObject.getString("message"));
                        mAcountRepositories.setChangePasswordReponse(mUserReponse);
                        Log.d(TAG, "onResponse: "+jsonObject.getString("message"));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        mUserReponse.setMessage(response.message());
                        mAcountRepositories.setChangePasswordReponse(mUserReponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordReponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
                mUserReponse.setMessage(t.getMessage());
                mAcountRepositories.setChangePasswordReponse(mUserReponse);
            }
        });
        return null;
    }
}
