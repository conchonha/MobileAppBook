package com.example.mobileappbook.src.viewmodel.course;

import android.app.Application;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileappbook.cores.body.CreateCourseBody;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.src.repositories.Course.CourseRepositories;
import com.example.mobileappbook.utils.SharePrefs;
import com.example.mobileappbook.utils.Validations;
import com.google.gson.Gson;

import java.util.Map;

public class CourseViewmodel extends AndroidViewModel {
    private CreateCourseBody mCreateCourseBody;
    private CourseRepositories mCourseRepositories;
    private SharePrefs mSharePrefs = new SharePrefs(getApplication());
    private Gson mGson = new Gson();

    public CourseViewmodel(@NonNull Application application) {
        super(application);
        if(mCourseRepositories == null){
            mCourseRepositories = new CourseRepositories();
        }
    }

    public void createCourse(){
        UserReponse userReponse = mGson.fromJson(mSharePrefs.getUser(),UserReponse.class);
        mCourseRepositories.createCourse(mCreateCourseBody,userReponse.getmAuthToken());
    }

    public LiveData<Map> getCreateCourseReponse(){
        return mCourseRepositories.getCreateCourseReponse();
    }

    public boolean checkValidation(EditText edtCourseName,EditText edtObjectives,EditText edtDesciption,EditText edtField, EditText edtPrice,EditText edtDiscount){
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

        if(edtField.getText().toString().equals("")){
            edtField.setError("Field invalid");
            return false;
        }
        edtField.setError(null);
        mCreateCourseBody = new CreateCourseBody(edtCourseName.getText().toString(),edtObjectives.getText().toString(),edtDesciption.getText().toString(),edtField.getText().toString(),edtPrice.getText().toString(),edtDiscount.getText().toString());
        return true;
    }
}
