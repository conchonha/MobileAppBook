package com.example.mobileappbook.src.repositories.Course;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.async.course.CreateCousreAsync;
import com.example.mobileappbook.async.featured.GetAllCategoryAsync;
import com.example.mobileappbook.cores.body.CreateCourseBody;
import com.example.mobileappbook.cores.reponse.course.CourseReponse;
import com.example.mobileappbook.cores.reponse.featured.GetAllCategoryReponse;

import java.util.List;

import okhttp3.MultipartBody;

public class CourseRepositories {
    private MutableLiveData<CourseReponse>mCreateCourseReponse = new MutableLiveData<>();
    private MutableLiveData<List<GetAllCategoryReponse>> mGetAllCategoryReponse = new MutableLiveData<>();

    //---------------------createCourse------------

    public void createCourse(CreateCourseBody createCourseBody, String token, MultipartBody.Part part){
        CreateCousreAsync async = new CreateCousreAsync(this,createCourseBody,token,part);
        async.execute();
    }

    public void setmCreateCourseReponse(CourseReponse createCourseReponse){
        mCreateCourseReponse.setValue(createCourseReponse);
    }

    public LiveData<CourseReponse> getCreateCourseReponse(){
        return mCreateCourseReponse;
    }
}
