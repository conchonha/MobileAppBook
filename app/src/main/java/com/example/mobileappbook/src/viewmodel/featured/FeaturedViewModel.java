package com.example.mobileappbook.src.viewmodel.featured;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCategoryReponse;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCourseReponse;
import com.example.mobileappbook.src.repositories.featured.FeaturedRepositories;

import java.util.List;

public class FeaturedViewModel extends AndroidViewModel {
    private FeaturedRepositories mFeaturedRepositories;

    public FeaturedViewModel(@NonNull Application application) {
        super(application);
        if (mFeaturedRepositories == null){
            mFeaturedRepositories = new FeaturedRepositories();
        }
        mFeaturedRepositories.initstalise();
    }

    public LiveData<List<GetAllCategoryReponse>> getAllCategory(){
        return  mFeaturedRepositories.getmGetAllCategoryReponse();
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
}
