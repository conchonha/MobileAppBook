package com.example.mobileappbook.async.setting;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mobileappbook.cores.body.ResetPasswordBody;
import com.example.mobileappbook.cores.body.UserInfoBody;
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

public class ChangeProfileAsync extends AsyncTask<Void,Void,Void> {
    private AcountRepositories mAcountRepositories;
    private UserInfoBody mUserInfoBody;
    private String mToken;
    private UserReponse mUserReponse = new UserReponse();
    private String TAG ="ChangeProfileAsync";

    public ChangeProfileAsync(AcountRepositories acountRepositories, UserInfoBody userInfoBody,String token) {
        this.mAcountRepositories = acountRepositories;
        this.mUserInfoBody = userInfoBody;
        this.mToken = token;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        DataService dataService = APIServices.getService();
        Call<UserReponse> call = dataService.changeProfile(mUserInfoBody,mToken);
        call.enqueue(new Callback<UserReponse>() {
            @Override
            public void onResponse(Call<UserReponse> call, Response<UserReponse> response) {
                Log.d(TAG, "onResponse: "+response.toString());
                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse: "+new Gson().toJson(response.body()));
                    mAcountRepositories.setChangeProfileReponse(response.body());
                }else{
                    try {
                        String reponse = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(reponse);
                        mUserReponse.setMessage(jsonObject.getString("message"));
                        mAcountRepositories.setChangeProfileReponse(mUserReponse);
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        mUserReponse.setMessage(response.message());
                        mAcountRepositories.setChangeProfileReponse(mUserReponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserReponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
                mUserReponse.setMessage(t.getMessage());
                mAcountRepositories.setResetPasswordReponse(mUserReponse);
            }
        });
        return null;
    }
}
