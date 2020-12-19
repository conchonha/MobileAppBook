package com.example.mobileappbook.src.repositories.featured.detail_buy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.async.detail_buy.GetCommentAsync;
import com.example.mobileappbook.async.detail_buy.GetSuggesTedCourseAsync;

import java.util.Map;

public class DetailBuyRepositories {
    private MutableLiveData<Map>mGetCommentReponse = new MutableLiveData<>();
    private MutableLiveData<Map>mGetSuggestedReponse = new MutableLiveData<>();

    //----------------------Get Comment reponse-------------
    public void getCommentReponse(String courseId){
        GetCommentAsync async = new GetCommentAsync(this);
        async.execute(courseId);
    }

    public void setmGetCommentReponse(Map map){
        mGetCommentReponse.setValue(map);
    }

    public LiveData<Map> getmGetCommentReponse(){
        return mGetCommentReponse;
    }

    //------------------Suggested Courses---------------------
    public void getGetSuggested(String userId){
        GetSuggesTedCourseAsync async = new GetSuggesTedCourseAsync(this);
        async.execute(userId);
    }

    public void setmGetSuggestedReponse(Map map){
        mGetSuggestedReponse.setValue(map);
    }

    public LiveData<Map> getmGetSuggestedReponse(){
        return mGetSuggestedReponse;
    }
}
