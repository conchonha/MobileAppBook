package com.example.mobileappbook.async.setting;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mobileappbook.cores.services.APIServices;
import com.example.mobileappbook.cores.services.DataService;
import com.example.mobileappbook.src.repositories.acount.AcountRepositories;
import com.example.mobileappbook.utils.Constain;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogoutAsync extends AsyncTask<Void,Void,Void> {
    private AcountRepositories mAcountRepositories;
    private String mToken;
    private Map mMap = new HashMap();
    private String TAG = "AsyncLogin";

    public LogoutAsync(AcountRepositories acountRepositories,String token){
        this.mAcountRepositories = acountRepositories;
        this.mToken = token;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        DataService dataService = APIServices.getService();
        Call<Map> callback = dataService.userLogout(mToken);

        callback.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                Log.d(TAG, "onResponse: "+response.toString());
                if(response.isSuccessful()){
                    mAcountRepositories.setUserLogoutReponse(response.body());
                }else{
                    try {
                        String reponse = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(reponse);
                        mMap.put(Constain.keyMapErr,jsonObject.get("message"));
                        mAcountRepositories.setUserLogoutReponse(mMap);
                        Log.d(TAG, "onResponse: "+jsonObject.getString("message"));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        mMap.put(Constain.keyMapErr,response.message());
                        mAcountRepositories.setUserLogoutReponse(mMap);
                    }
                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
                mMap.put(Constain.keyMapErr,t.getMessage());
                mAcountRepositories.setUserLogoutReponse(mMap);
            }
        });
        return null;
    }
}
