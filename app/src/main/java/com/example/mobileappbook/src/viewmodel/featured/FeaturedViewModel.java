package com.example.mobileappbook.src.viewmodel.featured;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.cores.reponse.comment.GetCommentReponse;
import com.example.mobileappbook.cores.reponse.featured.GetAllCategoryReponse;
import com.example.mobileappbook.cores.reponse.featured.GetAllCourseReponse;
import com.example.mobileappbook.src.repositories.featured.FeaturedRepositories;

import java.util.ArrayList;
import java.util.List;

public class FeaturedViewModel extends AndroidViewModel {
    private FeaturedRepositories mFeaturedRepositories;
    private MutableLiveData<List<GetAllCourseReponse>>mMutableLiveData = new MutableLiveData<>();

    public FeaturedViewModel(@NonNull Application application) {
        super(application);
        if (mFeaturedRepositories == null){
            mFeaturedRepositories = new FeaturedRepositories();
        }
        mFeaturedRepositories.initstalise();
    }

    public LiveData<List<GetAllCategoryReponse>> getAllCategory(){
        return mFeaturedRepositories.getmGetAllCategoryReponse();
    };

    public LiveData<List<GetAllCourseReponse>> getAllCourseReponse(){
        return mFeaturedRepositories.getmGetAllCourseReponse();
    }

    public LiveData<List<GetAllCourseReponse>>getmGetTopCourseReponse(){
        return mFeaturedRepositories.getmGetTopCourseReponse();
    }

    public LiveData<List<GetAllCourseReponse>>getmGetFreeCourseReponse(){
        return mFeaturedRepositories.getmGetFreeCourseReponse();
    }

    public void searchCoure(final String content){
        List<GetAllCourseReponse>listTmt = new ArrayList<>();
        for (GetAllCourseReponse reponse : mFeaturedRepositories.getmGetAllCourseReponse().getValue()){
            if(reponse.getName().toLowerCase().contains(content.toLowerCase())){
                listTmt.add(reponse);
            }
        }
        mMutableLiveData.setValue(listTmt);
    }

    public void setDataAllSearch(){
        mMutableLiveData.setValue(mFeaturedRepositories.getmGetAllCourseReponse().getValue());
    }
    public LiveData<List<GetAllCourseReponse>>getListSearch(){
        return mMutableLiveData;
    }


}
