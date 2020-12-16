package com.example.mobileappbook.src.viewmodel.course;

import android.app.Application;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileappbook.cores.body.CreateCourseBody;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.cores.reponse.course.CourseReponse;
import com.example.mobileappbook.cores.reponse.featured.GetAllCategoryReponse;
import com.example.mobileappbook.src.repositories.Course.CourseRepositories;
import com.example.mobileappbook.src.repositories.featured.FeaturedRepositories;
import com.example.mobileappbook.utils.SharePrefs;
import com.example.mobileappbook.utils.Validations;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.MultipartBody;

public class CourseViewmodel extends AndroidViewModel {
    private CreateCourseBody mCreateCourseBody;
    private FeaturedRepositories mFeaturedRepositories;
    private CourseRepositories mCourseRepositories;
    private SharePrefs mSharePrefs = new SharePrefs(getApplication());
    private Gson mGson = new Gson();

    public CourseViewmodel(@NonNull Application application) {
        super(application);
        if(mCourseRepositories == null || mFeaturedRepositories == null ){
            mCourseRepositories = new CourseRepositories();
            mFeaturedRepositories = new FeaturedRepositories();
            mFeaturedRepositories.initstalise();
        }
    }

    public void createCourse(CreateCourseBody createCourseBody, MultipartBody.Part part){
        UserReponse userReponse = mGson.fromJson(mSharePrefs.getUser(),UserReponse.class);
        mCourseRepositories.createCourse(createCourseBody,userReponse.getmAuthToken(),part);
    }

    public LiveData<List<GetAllCategoryReponse>> getAllCategory(){
        return mFeaturedRepositories.getmGetAllCategoryReponse();
    };

    public LiveData<CourseReponse> getCreateCourseReponse(){
        return mCourseRepositories.getCreateCourseReponse();
    }

    public boolean checkValidation(EditText edtCourseName,EditText edtObjectives,EditText edtDesciption,String field, EditText edtPrice,EditText edtDiscount){
        if(Validations.isValidName(edtCourseName.getText().toString())){
            edtCourseName.setError("Name invalid");
            return false;
        }
        edtCourseName.setError(null);
        if(Validations.isValidAddress(edtObjectives.getText().toString())){
            edtObjectives.setError("Course Objectives invalid");
            return false;
        }
        edtObjectives.setError(null);

        if(Validations.isValidAddress(edtDesciption.getText().toString())){
            edtDesciption.setError("Description invalid");
            return false;
        }
        edtDesciption.setError(null);

        if(Validations.isValidPrice(edtPrice.getText().toString())){
            edtPrice.setError("Price invalid");
            return false;
        }
        edtPrice.setError(null);

        mCreateCourseBody = new CreateCourseBody(edtCourseName.getText().toString(),edtObjectives.getText().toString(),edtDesciption.getText().toString(),field,edtPrice.getText().toString(),edtDiscount.getText().toString());
        return true;
    }

    //getter setter
    public CreateCourseBody getmCreateCourseBody(){
        return mCreateCourseBody;
    }
}
