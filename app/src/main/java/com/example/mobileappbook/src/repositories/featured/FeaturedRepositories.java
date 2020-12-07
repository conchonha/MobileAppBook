package com.example.mobileappbook.src.repositories.featured;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.async.featured.GetAllCategoryAsync;
import com.example.mobileappbook.async.featured.GetAllCourseAsync;
import com.example.mobileappbook.async.featured.GetFreeCourseAsync;
import com.example.mobileappbook.async.featured.GetTopCourseAsync;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCategoryReponse;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCourseReponse;

import java.util.List;

public class FeaturedRepositories {
    private MutableLiveData<List<GetAllCategoryReponse>> mGetAllCategoryReponse = new MutableLiveData<>();
    private MutableLiveData<List<GetAllCourseReponse>> mGetAllCourseReponse = new MutableLiveData<>();
    private MutableLiveData<List<GetAllCourseReponse>> mGetTopCourseReponse = new MutableLiveData<>();
    private MutableLiveData<List<GetAllCourseReponse>> mGetFreeCourseReponse = new MutableLiveData<>();


    public void initstalise(){
        GetAllCourseAsync allCourseAsync = new GetAllCourseAsync(this);
        GetAllCategoryAsync allCategoryAsync = new GetAllCategoryAsync(this);
        GetTopCourseAsync topCourseAsync = new GetTopCourseAsync(this);
        GetFreeCourseAsync freeCourseAsync = new GetFreeCourseAsync(this);

        allCategoryAsync.execute();
        allCourseAsync.execute();
        topCourseAsync.execute();
        freeCourseAsync.execute();
    }

    //---------------------Category------------
    public void setmGetAllCategoryReponse(List<GetAllCategoryReponse> getAllCategory){
        mGetAllCategoryReponse.setValue(getAllCategory);
    }

    public LiveData<List<GetAllCategoryReponse>> getmGetAllCategoryReponse(){
        return mGetAllCategoryReponse;
    };

    //----------------------All Course-------------
    public void setmGetAllCourseReponse(List<GetAllCourseReponse> getAllCourseReponse){
        mGetAllCourseReponse.setValue(getAllCourseReponse);
    }

    public LiveData<List<GetAllCourseReponse>> getmGetAllCourseReponse(){
        return mGetAllCourseReponse;
    }

    //----------------------Top Course-------------
    public void setmGetTopCourseReponse(List<GetAllCourseReponse> getAllCourseReponse){
        mGetTopCourseReponse.setValue(getAllCourseReponse);
    }

    public LiveData<List<GetAllCourseReponse>> getmGetTopCourseReponse(){
        return mGetTopCourseReponse;
    }

    //----------------------Free Course-------------
    public void setmGetFreeCourseReponse(List<GetAllCourseReponse> getAllCourseReponse){
        mGetFreeCourseReponse.setValue(getAllCourseReponse);
    }

    public LiveData<List<GetAllCourseReponse>> getmGetFreeCourseReponse(){
        return mGetFreeCourseReponse;
    }
}
