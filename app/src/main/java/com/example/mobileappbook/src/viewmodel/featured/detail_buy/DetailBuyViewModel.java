package com.example.mobileappbook.src.viewmodel.featured.detail_buy;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileappbook.cores.body.PostRattingBody;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.src.repositories.featured.detail_buy.DetailBuyRepositories;
import com.example.mobileappbook.utils.SharePrefs;
import com.google.gson.Gson;

import java.util.Map;

public class DetailBuyViewModel extends AndroidViewModel {
    private DetailBuyRepositories mDetailBuyRepositories;
    private SharePrefs mSharePrefs;
    private Gson mGson;

    public DetailBuyViewModel(@NonNull Application application) {
        super(application);
        if(mDetailBuyRepositories == null){
            mGson = new Gson();
            mSharePrefs = new SharePrefs(application);
            mDetailBuyRepositories = new DetailBuyRepositories();
        }
    }

    public void getComment(String courseId){
        mDetailBuyRepositories.getCommentReponse(courseId);
    }

    public LiveData<Map>getCommentReponse(){
        return mDetailBuyRepositories.getmGetCommentReponse();
    }

    public void getSuggestedCourses(String idUser){
        mDetailBuyRepositories.getGetSuggested(idUser);
    }

    public LiveData<Map>getmGetSuggestedReponse(){
        return mDetailBuyRepositories.getmGetSuggestedReponse();
    }

    public void postRating(String comment, int mRating,String idCourse) {
        UserReponse userReponse  = mGson.fromJson(mSharePrefs.getUser(),UserReponse.class);
        PostRattingBody body = new PostRattingBody(userReponse.getId(),idCourse,comment,mRating+"");
        Log.d("AAA", "postRating: "+mGson.toJson(body));
        mDetailBuyRepositories.postRating(body);
    }

    public LiveData<Map>getPostRatingRepone(){
        return mDetailBuyRepositories.getmPostRatingReponse();
    }
}
