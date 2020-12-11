package com.example.mobileappbook.async.acount;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mobileappbook.cores.body.RegisterBody;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.cores.services.APIServices;
import com.example.mobileappbook.cores.services.DataService;
import com.example.mobileappbook.src.repositories.acount.AcountRepositories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsyncRegister extends AsyncTask<Void, Void, Void> {
    private RegisterBody mRegisterBody;
    private AcountRepositories mAcountRepositories;
    private UserReponse mUserReponse = new UserReponse();
    private String TAG = "AsyncRegister";

    public AsyncRegister(RegisterBody registerBody, AcountRepositories acountRepositories) {
        this.mRegisterBody = registerBody;
        if (mAcountRepositories == null) {
            mAcountRepositories = acountRepositories;
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
                    mAcountRepositories.setRegisterReponse(response.body());
                } else {
                    try {
                        String reponse = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(reponse);
                        Log.d(TAG, "onResponse: message"+jsonObject.getString("message"));
                        if(jsonObject.getString("message") != null){
                            mUserReponse.setMessage(jsonObject.getString("message"));
                            mAcountRepositories.setRegisterReponse(mUserReponse);
                        }else{
                            JSONArray jsonArray = jsonObject.getJSONArray("errors");
                            StringBuffer stringBuffer = new StringBuffer();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jo = jsonArray.getJSONObject(i);
                                stringBuffer.append(jo.getString("msg"));
                            }
                            Log.d(TAG, "onResponse: "+stringBuffer.toString());
                            mUserReponse.setMessage(stringBuffer.toString());
                            mAcountRepositories.setRegisterReponse(mUserReponse);
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        mUserReponse.setMessage(response.message());
                        mAcountRepositories.setRegisterReponse(mUserReponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserReponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
                mUserReponse.setMessage(t.getMessage());
                mAcountRepositories.setRegisterReponse(mUserReponse);
            }
        });
        return null;
    }
}
