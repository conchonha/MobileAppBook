package com.example.mobileappbook.async.acount;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.services.APIServices;
import com.example.mobileappbook.cores.services.DataService;
import com.example.mobileappbook.src.repositories.acount.recover.RecoverRepositories;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsyncRecover extends AsyncTask<Void,Void,Void> {
    private String mEmail;
    private RecoverRepositories mRecoverRepositories;
    private String TAG = "AsyncRecover";
    private ErrorRepone mErrorReponse;

    public  AsyncRecover(RecoverRepositories recoverRepositories,String email){
        this.mRecoverRepositories = recoverRepositories;
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
                    mErrorReponse = new ErrorRepone(200,"send data mail success");
                    mRecoverRepositories.setDataReponseRecover(mErrorReponse);
                }else{
                    mErrorReponse = new ErrorRepone(response.hashCode(),response.message());
                    mRecoverRepositories.setDataReponseRecover(mErrorReponse);
                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                mErrorReponse = new ErrorRepone(t.hashCode(),t.getMessage());
                mRecoverRepositories.setDataReponseRecover(mErrorReponse);
            }
        });
        return null;
    }
}
