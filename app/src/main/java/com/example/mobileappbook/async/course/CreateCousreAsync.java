package com.example.mobileappbook.async.course;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mobileappbook.cores.body.CreateCourseBody;
import com.example.mobileappbook.cores.reponse.course.CourseReponse;
import com.example.mobileappbook.cores.services.APIServices;
import com.example.mobileappbook.cores.services.DataService;
import com.example.mobileappbook.src.repositories.Course.CourseRepositories;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCousreAsync extends AsyncTask<Void, Void, Void> {
    private CourseRepositories mCourseRepositories;
    private CreateCourseBody mCreateCourseBody;
    private String mToken;
    private MultipartBody.Part mPart;
    private CourseReponse mCourseReponse = new CourseReponse();
    private String TAG = "CreateCousreAsync";

    public CreateCousreAsync(CourseRepositories courseRepositories, CreateCourseBody createCourseBody, String token,MultipartBody.Part part) {
        this.mCourseRepositories = courseRepositories;
        this.mCreateCourseBody = createCourseBody;
        this.mToken = token;
        this.mPart = part;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        DataService dataService = APIServices.getService();
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), mCreateCourseBody.getmCourseName());
        RequestBody goal = RequestBody.create(MediaType.parse("text/plain"), mCreateCourseBody.getmObjectives());
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), mCreateCourseBody.getmDesciption());
        RequestBody category = RequestBody.create(MediaType.parse("text/plain"), mCreateCourseBody.getmField());
        RequestBody price = RequestBody.create(MediaType.parse("text/plain"), mCreateCourseBody.getmPrice());
        RequestBody discount = RequestBody.create(MediaType.parse("text/plain"), mCreateCourseBody.getmDiscount());
       // RequestBody token = RequestBody.create(MediaType.parse("text/plain"), mToken);

        Call<CourseReponse> callback = dataService.createCourse(name,goal,description,category,price,discount, mToken,mPart);
        callback.enqueue(new Callback<CourseReponse>() {
            @Override
            public void onResponse(Call<CourseReponse> call, Response<CourseReponse> response) {
                Log.d(TAG, "onResponse: " + response.toString());
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse - success " + new Gson().toJson(response.body()));
                    mCourseRepositories.setmCreateCourseReponse(response.body());
                } else {
                    Log.d(TAG, "onResponse - errorBody " + new Gson().toJson(response.errorBody()));
                    try {
                        String reponse = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(reponse);
                        mCourseReponse.setMessage(jsonObject.getString("message"));
                        mCourseRepositories.setmCreateCourseReponse(mCourseReponse);
                    } catch (IOException | JSONException e) {
                        mCourseReponse.setMessage(response.message());
                        mCourseRepositories.setmCreateCourseReponse(mCourseReponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<CourseReponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
                mCourseReponse.setMessage(t.getMessage());
                mCourseRepositories.setmCreateCourseReponse(mCourseReponse);
            }
        });
        return null;
    }
}
