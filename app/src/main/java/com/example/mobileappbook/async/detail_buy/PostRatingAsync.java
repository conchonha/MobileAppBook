package com.example.mobileappbook.async.detail_buy;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mobileappbook.cores.body.PostRattingBody;
import com.example.mobileappbook.cores.reponse.comment.GetCommentReponse;
import com.example.mobileappbook.cores.services.APIServices;
import com.example.mobileappbook.cores.services.DataService;
import com.example.mobileappbook.src.repositories.featured.detail_buy.DetailBuyRepositories;
import com.example.mobileappbook.utils.Constain;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRatingAsync extends AsyncTask<String,Void,Void> {
    private DetailBuyRepositories mDetailBuyRepositories;
    private PostRattingBody mPostRattingBody;
    private Map mMap = new HashMap();
    private String TAG = "PostRatingAsync";
    private Gson mGson = new Gson();

    public PostRatingAsync(DetailBuyRepositories detailBuyRepositories,PostRattingBody postRattingBody){
        this.mDetailBuyRepositories = detailBuyRepositories;
        this.mPostRattingBody = postRattingBody;
    }

    @Override
    protected Void doInBackground(String... strings) {
        DataService dataService = APIServices.getService();
        Call<Map>call = dataService.postRating(mPostRattingBody);

        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                Log.d(TAG, "onResponse: "+response.toString());
                if(response.isSuccessful()){
                    mMap.put(200,response.body());
                    mDetailBuyRepositories.setmPostRatingReponse(mMap);
                    Log.d(TAG, "onResponse -success: "+mGson.toJson(response.body()));
                }else{
                    Log.d(TAG, "onResponse -err: "+mGson.toJson(response.errorBody()));
                    try {
                        String reponse = mGson.toJson(response.errorBody());
                        JSONObject jsonObject = new JSONObject(reponse);
                        mMap.put(Constain.keyMapErr,jsonObject.get("message"));
                        mDetailBuyRepositories.setmPostRatingReponse(mMap);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        mMap.put(Constain.keyMapErr,response.message());
                        mDetailBuyRepositories.setmPostRatingReponse(mMap);
                    }
                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
                mMap.put(Constain.keyMapErr,t.getMessage());
                mDetailBuyRepositories.setmPostRatingReponse(mMap);
            }
        });
        return null;
    }
}
