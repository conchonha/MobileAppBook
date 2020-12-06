package com.example.mobileappbook.src.repositories.featured;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.async.featured.GetAllCategoryAsync;
import com.example.mobileappbook.async.featured.GetAllCourseAsync;
import com.example.mobileappbook.async.featured.GetFreeCourseAsync;
import com.example.mobileappbook.async.featured.GetTopCourseAsync;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCategoryReponse;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCourseReponse;

import java.util.List;

public class FeaturedRepositories {
    private MutableLiveData<List<GetAllCategoryReponse>> mGetAllCategory = new MutableLiveData<>();
    private MutableLiveData<ErrorRepone> mGetAllCategoryError = new MutableLiveData<>();
    private MutableLiveData<List<GetAllCourseReponse>> mGetAllCourseReponse = new MutableLiveData<>();
    private MutableLiveData<ErrorRepone> mErrorGetAllCourseReponse = new MutableLiveData<>();

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

    public void setmGetAllCategory(List<GetAllCategoryReponse> getAllCategory){
        mGetAllCategory.setValue(getAllCategory);
    }

    public void setmGetAllCategoryError(ErrorRepone errorRepone){
        mGetAllCategoryError.setValue(errorRepone);
    }

    public LiveData<List<GetAllCategoryReponse>> getAllCategory(){
      return mGetAllCategory;
    };
    public LiveData<ErrorRepone> getAllCategoryError(){
        return mGetAllCategoryError;
    }

    public void setmGetAllCourseReponse(List<GetAllCourseReponse> getAllCourseReponse){
        mGetAllCourseReponse.setValue(getAllCourseReponse);
    }
    public void setmErrorGetAllCourseReponse(ErrorRepone errorRepone){
        mErrorGetAllCourseReponse.setValue(errorRepone);
    }
    public LiveData<List<GetAllCourseReponse>> getAllCourse(){
        return mGetAllCourseReponse;
    }
    public LiveData<ErrorRepone> getAllCourseError(){
        return mErrorGetAllCourseReponse;
    }
}
