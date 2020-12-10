package com.example.mobileappbook.async.acount;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mobileappbook.cores.body.ActiveAcountBody;
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

public class ActiveAcountAsync extends AsyncTask<Void,Void,Void> {
    private ActiveAcountBody mActiveAcountBody;
    private AcountRepositories mAcountRepositories;
    private UserReponse mUserReponse = new UserReponse();
    private String TAG = "ActiveAcountAsync";

    public ActiveAcountAsync(AcountRepositories acountRepositories, ActiveAcountBody mActiveAcountBody) {
        this.mAcountRepositories = acountRepositories;
        this.mActiveAcountBody = mActiveAcountBody;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        DataService dataService = APIServices.getService();
        Call<UserReponse>call = dataService.activeAcount(mActiveAcountBody);

        call.enqueue(new Callback<UserReponse>() {
            @Override
            public void onResponse(Call<UserReponse> call, Response<UserReponse> response) {
                Log.d(TAG, "onResponse: "+response.toString());
                if(response.isSuccessful()){
                    mAcountRepositories.setActiveReponse(response.body());
                }else{
                    try {
                        String reponse = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(reponse);
                        mUserReponse.setMessage(jsonObject.getString("message"));
                        mAcountRepositories.setActiveReponse(mUserReponse);
                        Log.d(TAG, "onResponse: "+jsonObject.get("message"));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserReponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
                mUserReponse.setMessage(t.getMessage());
                mAcountRepositories.setActiveReponse(mUserReponse);
            }
        });
        return null;
    }
}
