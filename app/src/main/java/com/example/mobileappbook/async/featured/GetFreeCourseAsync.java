package com.example.mobileappbook.async.featured;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCourseReponse;
import com.example.mobileappbook.src.repositories.featured.FeaturedRepositories;
import com.example.mobileappbook.cores.services.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetFreeCourseAsync extends AsyncTask<Void, Void, Void> {
    private FeaturedRepositories mGetFreeCourseRepositories;
    private ErrorRepone mErrorRepone;
    private String TAG = "GetFreeCourseAsync";

    public GetFreeCourseAsync(FeaturedRepositories mGetFreeCourseRepositories) {
        this.mGetFreeCourseRepositories = mGetFreeCourseRepositories;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        DataService dataService = APIServices.getService();
        Call<List<GetAllCourseReponse>> getFreeCourseReponse = dataService.getfreecourse();
        getFreeCourseReponse.enqueue(new Callback<List<GetAllCourseReponse>>() {
            @Override
            public void onResponse(Call<List<GetAllCourseReponse>> call, Response<List<GetAllCourseReponse>> response) {
                Log.d(TAG, "onResponse: " + response.toString());
                if (response.isSuccessful()) {
                    mGetFreeCourseRepositories.setmGetAllCourseReponse(response.body());
                }else {
                    Log.d(TAG, "onResponse: " + response.hashCode());
                    Log.d(TAG, "onResponse: " + response.message());
                    mErrorRepone = new ErrorRepone(response.hashCode(), response.message());
                    mGetFreeCourseRepositories.setmErrorGetAllCourseReponse(mErrorRepone);
                }

            }

            @Override
            public void onFailure(Call<List<GetAllCourseReponse>> call, Throwable t) {
                mErrorRepone = new ErrorRepone(t.hashCode(), t.getMessage());
                mGetFreeCourseRepositories.setmErrorGetAllCourseReponse(mErrorRepone);
            }
        });
        return null;
    }
}
