package com.example.mobileappbook.async.featured;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCategoryReponse;
import com.example.mobileappbook.cores.services.*;
import com.example.mobileappbook.src.repositories.featured.FeaturedRepositories;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllCategoryAsync extends AsyncTask<Void,Void,Void> {
    private String TAG = "getcategory";
    private FeaturedRepositories mFeaturedRepositories;
   // private ErrorRepone mErrorRepone;

    public GetAllCategoryAsync(FeaturedRepositories mFeaturedRepositories) {
        this.mFeaturedRepositories = mFeaturedRepositories;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        DataService dataService = APIServices.getService();
        Call<List<GetAllCategoryReponse>> callBack = dataService.getallcategory();
        callBack.enqueue(new Callback<List<GetAllCategoryReponse>>() {
            @Override
            public void onResponse(Call<List<GetAllCategoryReponse>> call, Response<List<GetAllCategoryReponse>> response) {
                Log.d(TAG, "onResponse: " + response.toString());
                if (response.isSuccessful()){
                        mFeaturedRepositories.setmGetAllCategoryReponse(response.body());
                }else {
//                    Log.d(TAG, "onResponse: " + response.hashCode());
//                    Log.d(TAG, "onResponse: " + response.message());
//                    mErrorRepone = new ErrorRepone(response.hashCode(),response.message());
                }
            }

            @Override
            public void onFailure(Call<List<GetAllCategoryReponse>> call, Throwable t) {
//                Log.d(TAG, "onResponse: " + t.hashCode());
//                Log.d(TAG, "onResponse: " + t.getMessage());
//                mErrorRepone = new ErrorRepone(t.hashCode(),t.getMessage());
            }
        });
        return null;
    }
}
