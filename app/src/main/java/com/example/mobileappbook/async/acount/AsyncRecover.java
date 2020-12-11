package com.example.mobileappbook.async.acount;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mobileappbook.cores.services.APIServices;
import com.example.mobileappbook.cores.services.DataService;
import com.example.mobileappbook.src.repositories.acount.AcountRepositories;
import com.example.mobileappbook.utils.Constain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsyncRecover extends AsyncTask<Void,Void,Void> {
    private String mEmail;
    private AcountRepositories mAcountRepositories;
    private Map mMap = new HashMap();
    private String TAG = "AsyncRecover";


    public  AsyncRecover(AcountRepositories acountRepositories,String email){
        this.mAcountRepositories = acountRepositories;
        this.mEmail = email;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        DataService dataService = APIServices.getService();
        Call<Map> call = dataService.forgotPassword(mEmail);

        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                Log.d(TAG, "onResponse: "+response.toString());
                if(response.isSuccessful()){
                    mAcountRepositories.setRecoverPasswordReponse(response.body());
                }else{
                    try {
                        String repone = response.errorBody().string();
                        mMap.put(Constain.keyMapErr,repone);
                        mAcountRepositories.setRecoverPasswordReponse(mMap);
                    } catch (IOException e) {
                        e.printStackTrace();
                        mMap.put(Constain.keyMapErr,response.message());
                        mAcountRepositories.setRecoverPasswordReponse(mMap);
                    }
                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                mMap.put("300",t.getMessage());
                mAcountRepositories.setRecoverPasswordReponse(mMap);
            }
        });
        return null;
    }
}
