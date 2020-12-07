package com.example.mobileappbook.async.acount;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mobileappbook.cores.body.ActiveAcountBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;
import com.example.mobileappbook.cores.services.APIServices;
import com.example.mobileappbook.cores.services.DataService;
import com.example.mobileappbook.src.repositories.acount.active_acount.ActiveAcountRepositories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActiveAcountAsync extends AsyncTask<Void,Void,Void> {
    private ActiveAcountRepositories mActiveAcountRepositories;
    private ActiveAcountBody mActiveAcountBody;
    private String TAG = "ActiveAcountAsync";

    public ActiveAcountAsync(ActiveAcountRepositories mActiveAcountRepositories, ActiveAcountBody mActiveAcountBody) {
        this.mActiveAcountRepositories = mActiveAcountRepositories;
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
                    mActiveAcountRepositories.setmDataReponse(response.body());
                }else{
                    Log.d(TAG, "onResponse: code - "+response.hashCode());
                    Log.d(TAG, "onResponse: message - "+response.message());
                    ErrorRepone errorRepone = new ErrorRepone(response.hashCode(),response.message());
                    mActiveAcountRepositories.setmErrorReponse(errorRepone);
                }
            }

            @Override
            public void onFailure(Call<UserReponse> call, Throwable t) {
                Log.d(TAG, "onResponse: code - "+t.hashCode());
                Log.d(TAG, "onResponse: message - "+t.getMessage());
                ErrorRepone errorRepone = new ErrorRepone(t.hashCode(),t.getMessage());
                mActiveAcountRepositories.setmErrorReponse(errorRepone);
            }
        });
        return null;
    }
}
