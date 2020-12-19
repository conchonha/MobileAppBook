package com.example.mobileappbook.src.repositories.featured.detail_buy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.async.comment.GetCommentAsync;
import com.example.mobileappbook.cores.reponse.comment.GetCommentReponse;

import java.util.List;
import java.util.Map;

public class DetailBuyRepositories {
    private MutableLiveData<Map>mGetCommentReponse = new MutableLiveData<>();

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
}
