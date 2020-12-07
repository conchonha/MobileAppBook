package com.example.mobileappbook.async.acount;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mobileappbook.cores.body.RegisterBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;
import com.example.mobileappbook.cores.services.APIServices;
import com.example.mobileappbook.cores.services.DataService;
import com.example.mobileappbook.src.repositories.acount.RegisterRepositories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsyncRegister extends AsyncTask<Void, Void, Void> {
    private RegisterBody mRegisterBody;
    private RegisterRepositories mRepositoriesInstance;
    private String TAG = "AsyncRegister";

    public AsyncRegister(RegisterBody registerBody, RegisterRepositories registerRepositories) {
        this.mRegisterBody = registerBody;
        if (mRepositoriesInstance == null) {
            mRepositoriesInstance = registerRepositories;
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        DataService dataService = APIServices.getService();
        Call<UserReponse> callback = dataService.register(mRegisterBody);

        callback.enqueue(new Callback<UserReponse>() {
            @Override
            public void onResponse(Call<UserReponse> call, Response<UserReponse> response) {
                Log.d(TAG, "onResponse: " + response.toString());
                if (response.isSuccessful()) {
                    mRepositoriesInstance.setmReponeRegister(response.body());
                } else {
                    ErrorRepone errorRepone = new ErrorRepone(response.code(), response.message());
                    mRepositoriesInstance.setErrorReponse(errorRepone);
                }
            }

            @Override
            public void onFailure(Call<UserReponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
                ErrorRepone errorRepone = new ErrorRepone(t.hashCode(), t.getMessage());
                mRepositoriesInstance.setErrorReponse(errorRepone);
            }
        });
        return null;
    }
}
