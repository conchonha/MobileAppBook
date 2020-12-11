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
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserChangeAvatarAsync extends AsyncTask<Void,Void,Void> {
    private AcountRepositories mAcountRepositories;
    private MultipartBody.Part mPart;
    private String mToken;
    private String TAG = "UserChangeAvatarAsync";
    private UserReponse mUserRepone = new UserReponse();

    public UserChangeAvatarAsync(AcountRepositories acountRepositories,MultipartBody.Part mPart, String mToken) {
        this.mAcountRepositories = acountRepositories;
        this.mPart = mPart;
        this.mToken = mToken;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        DataService dataService = APIServices.getService();
        Call<ChangePasswordReponse> call = dataService.changeAva(mPart,mToken);

        call.enqueue(new Callback<ChangePasswordReponse>() {
            @Override
            public void onResponse(Call<ChangePasswordReponse> call, Response<ChangePasswordReponse> response) {
                Log.d(TAG, "onResponse: "+response.toString());
                if(response.isSuccessful()){
                    mAcountRepositories.setUserChangeAvatarReponse(response.body().getmUser());
                    Log.d(TAG, "onResponse: -isuccessfull"+new Gson().toJson(response.body()));
                }else{
                    try {
                        String reponse = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(reponse);
                        mUserRepone.setMessage(jsonObject.getString("message"));
                        mAcountRepositories.setUserChangeAvatarReponse(mUserRepone);
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        mUserRepone.setMessage(response.message());
                        mAcountRepositories.setUserChangeAvatarReponse(mUserRepone);
                    }
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordReponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
                mUserRepone.setMessage(t.getMessage());
                mAcountRepositories.setUserChangeAvatarReponse(mUserRepone);
            }
        });
        return null;
    }
}
