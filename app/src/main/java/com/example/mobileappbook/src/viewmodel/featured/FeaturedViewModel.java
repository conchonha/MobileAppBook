package com.example.mobileappbook.src.viewmodel.featured;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
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
        return  mFeaturedRepositories.getAllCategory();
    };

    public LiveData<ErrorRepone> getAllCategoryError(){
        return mFeaturedRepositories.getAllCategoryError();
    }

    public LiveData<List<GetAllCourseReponse>> getAllCourseReponse(){
        return mFeaturedRepositories.getAllCourse();
    }
    public LiveData<ErrorRepone> getAllCourseError(){
        return mFeaturedRepositories.getAllCourseError();
    };

}
