package com.example.mobileappbook.src.repositories.Course;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.async.course.CreateCousreAsync;
import com.example.mobileappbook.cores.body.CreateCourseBody;

import java.util.Map;

public class CourseRepositories {
    private MutableLiveData<Map>mCreateCourseReponse = new MutableLiveData<>();

    public void createCourse(CreateCourseBody createCourseBody,String token){
        CreateCousreAsync async = new CreateCousreAsync(this,createCourseBody,token);
        async.execute();
    }

    public void setmCreateCourseReponse(Map map){
        mCreateCourseReponse.setValue(map);
    }

    public LiveData<Map> getCreateCourseReponse(){
        return mCreateCourseReponse;
    }
}
