package com.example.mobileappbook.src.viewmodel.featured.detail_buy;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileappbook.src.repositories.featured.detail_buy.DetailBuyRepositories;

import java.util.Map;

public class DetailBuyViewModel extends AndroidViewModel {
    private DetailBuyRepositories mDetailBuyRepositories;

    public DetailBuyViewModel(@NonNull Application application) {
        super(application);
        if(mDetailBuyRepositories == null){
            mDetailBuyRepositories = new DetailBuyRepositories();
        }
    }

    public void getComment(String courseId){
        mDetailBuyRepositories.getCommentReponse(courseId);
    }

    public LiveData<Map>getCommentReponse(){
        return mDetailBuyRepositories.getmGetCommentReponse();
    }
}
