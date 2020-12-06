package com.example.mobileappbook.async.featured;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCourseReponse;
import com.example.mobileappbook.cores.services.*;
import com.example.mobileappbook.src.repositories.featured.FeaturedRepositories;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetAllCourseAsync extends AsyncTask<Void,Void,Void> {
    private FeaturedRepositories mGetAllCourseReponsitories;
    private ErrorRepone mErrorRepone;
    private String TAG = "GetAllCourseReponsitories";

    public GetAllCourseAsync(FeaturedRepositories mGetAllCourseReponsitories) {
        this.mGetAllCourseReponsitories = mGetAllCourseReponsitories;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        DataService dataService = APIServices.getService();
        Call<List<GetAllCourseReponse>> getAllCourseReponseCall = dataService.getallcourse();
        getAllCourseReponseCall.enqueue(new Callback<List<GetAllCourseReponse>>() {
            @Override
            public void onResponse(Call<List<GetAllCourseReponse>> call, Response<List<GetAllCourseReponse>> response) {
                Log.d(TAG, "onResponse: " + response.toString());
                if (response.isSuccessful()){
                    mGetAllCourseReponsitories.setmGetAllCourseReponse(response.body());
                }else {
                    Log.d(TAG, "onResponse: " + response.hashCode());
                    Log.d(TAG, "onResponse: " + response.message());
                    mErrorRepone = new ErrorRepone(response.hashCode(),response.message());
                    mGetAllCourseReponsitories.setmErrorGetAllCourseReponse(mErrorRepone);
                }
            }

            @Override
            public void onFailure(Call<List<GetAllCourseReponse>> call, Throwable t) {

                Log.d(TAG, "onResponse: " + t.hashCode());
                Log.d(TAG, "onResponse: " + t.getMessage());
                mErrorRepone = new ErrorRepone(t.hashCode(),t.getMessage());
                mGetAllCourseReponsitories.setmErrorGetAllCourseReponse(mErrorRepone);
            }
        });
        return null;
    }
}
