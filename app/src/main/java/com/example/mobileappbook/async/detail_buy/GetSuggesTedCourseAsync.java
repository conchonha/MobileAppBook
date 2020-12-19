package com.example.mobileappbook.async.detail_buy;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mobileappbook.cores.reponse.comment.GetCommentReponse;
import com.example.mobileappbook.cores.reponse.featured.GetAllCourseReponse;
import com.example.mobileappbook.cores.services.APIServices;
import com.example.mobileappbook.cores.services.DataService;
import com.example.mobileappbook.src.repositories.featured.detail_buy.DetailBuyRepositories;
import com.example.mobileappbook.utils.Constain;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetSuggesTedCourseAsync extends AsyncTask<String,Void,Void> {
    private DetailBuyRepositories mDetailBuyRepositories;
    private Map mMap = new HashMap();
    private String TAG = "GetSuggesTedCourseAsync";
    private Gson mGson = new Gson();

    public GetSuggesTedCourseAsync(DetailBuyRepositories detailBuyRepositories){
        this.mDetailBuyRepositories = detailBuyRepositories;
    }

    @Override
    protected Void doInBackground(String... strings) {
        String userId = strings[0];
        DataService dataService = APIServices.getService();
        Call<List<GetAllCourseReponse>> call = dataService.getSussgesTedCourseReponse(userId);

        call.enqueue(new Callback<List<GetAllCourseReponse>>() {
            @Override
            public void onResponse(Call<List<GetAllCourseReponse>> call, Response<List<GetAllCourseReponse>> response) {
                Log.d(TAG, "onResponse: "+response.toString());
                if(response.isSuccessful()){
                    mMap.put("200",response.body());
                    mDetailBuyRepositories.setmGetSuggestedReponse(mMap);
                    Log.d(TAG, "onResponse -success: "+mGson.toJson(response.body()));
                }else{
                    mMap.put(Constain.keyMapErr,response.message());
                    mDetailBuyRepositories.setmGetSuggestedReponse(mMap);
                    Log.d(TAG, "onResponse -err: "+mGson.toJson(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<List<GetAllCourseReponse>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
                mMap.put(Constain.keyMapErr,t.getMessage());
                mDetailBuyRepositories.setmGetSuggestedReponse(mMap);
            }
        });
        return null;
    }
}
