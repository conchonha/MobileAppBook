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


public class GetTopCourseAsync extends AsyncTask<Void,Void,Void> {
    private FeaturedRepositories mGetTopCourseReponsitories;
    private ErrorRepone mErrorRepone;
    private String TAG = "GetTopCourseAsync";

    public GetTopCourseAsync(FeaturedRepositories mGetTopCourseReponsitories) {
        this.mGetTopCourseReponsitories = mGetTopCourseReponsitories;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        DataService dataService = APIServices.getService();
        Call<List<GetAllCourseReponse>> getAllCourseReponse = dataService.gettopcourse();
        getAllCourseReponse.enqueue(new Callback<List<GetAllCourseReponse>>() {
            @Override
            public void onResponse(Call<List<GetAllCourseReponse>> call, Response<List<GetAllCourseReponse>> response) {
                Log.d(TAG, "onResponse: " + response.toString());
                if (response.isSuccessful()){
                    mGetTopCourseReponsitories.setmGetAllCourseReponse(response.body());
                }else {
                    Log.d(TAG, "onResponse: " + response.hashCode());
                    Log.d(TAG, "onResponse: " + response.message());
                    mErrorRepone = new ErrorRepone(response.hashCode(),response.message());
                    mGetTopCourseReponsitories.setmErrorGetAllCourseReponse(mErrorRepone);
                }
            }

            @Override
            public void onFailure(Call<List<GetAllCourseReponse>> call, Throwable t) {
                Log.d(TAG, "onResponse: " + t.hashCode());
                Log.d(TAG, "onResponse: " + t.getMessage());
                mErrorRepone = new ErrorRepone(t.hashCode(),t.getMessage());
                mGetTopCourseReponsitories.setmErrorGetAllCourseReponse(mErrorRepone);

            }
        });
        return null;
    }
}
