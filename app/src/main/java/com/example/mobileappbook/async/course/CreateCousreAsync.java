package com.example.mobileappbook.async.course;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mobileappbook.cores.body.CreateCourseBody;
import com.example.mobileappbook.cores.services.APIServices;
import com.example.mobileappbook.cores.services.DataService;
import com.example.mobileappbook.src.repositories.Course.CourseRepositories;
import com.example.mobileappbook.utils.Constain;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCousreAsync extends AsyncTask<Void, Void, Void> {
    private CourseRepositories mCourseRepositories;
    private CreateCourseBody mCreateCourseBody;
    private String mToken;
    private Map mMap = new HashMap();
    private String TAG = "CreateCousreAsync";

    public CreateCousreAsync(CourseRepositories courseRepositories, CreateCourseBody createCourseBody, String token) {
        this.mCourseRepositories = courseRepositories;
        this.mCreateCourseBody = createCourseBody;
        this.mToken = token;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        DataService dataService = APIServices.getService();
        Call<Map> callback = dataService.createCourse(mCreateCourseBody, mToken);

        callback.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                Log.d(TAG, "onResponse: " + response.toString());
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse-success data " + new Gson().toJson(response.body()));
                    if (response.body().get("status") != null) {
                        mMap.put(Constain.keyMapErr, response.body().get("status"));
                        mCourseRepositories.setmCreateCourseReponse(mMap);
                    } else {
                        mCourseRepositories.setmCreateCourseReponse(response.body());
                    }
                } else {
                    Log.d(TAG, "onResponse - errorBody " + new Gson().toJson(response.errorBody()));
                    try {
                        String reponse = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(reponse);
                        mMap.put(Constain.keyMapErr, jsonObject.getString("message"));
                        mCourseRepositories.setmCreateCourseReponse(mMap);
                        Log.d(TAG, "onResponse: " + jsonObject.getString("message"));
                    } catch (IOException | JSONException e) {
                        mMap.put(Constain.keyMapErr, response.message());
                        mCourseRepositories.setmCreateCourseReponse(mMap);
                    }
                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
                mMap.put(Constain.keyMapErr, t.getMessage());
                mCourseRepositories.setmCreateCourseReponse(mMap);
            }
        });
        return null;
    }
}
