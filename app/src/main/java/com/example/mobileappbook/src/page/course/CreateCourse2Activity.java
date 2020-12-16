package com.example.mobileappbook.src.page.course;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mobileappbook.R;
import com.example.mobileappbook.src.viewmodel.course.CourseViewmodel;
import com.example.mobileappbook.utils.Constain;
import com.example.mobileappbook.utils.Helpers;

import java.util.Map;

public class CreateCourse2Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtCourseName,mEdtObjectives,mEdtDesciption,mEdtField,mEdtPrice,mEdtDiscount;
    private CourseViewmodel mCourseViewmodel;
    private Dialog mDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course2);
        initView();
        initViewModel();
        listenerOnclicked();
    }

    //khởi tạo viewmodel
    private void initViewModel() {
        mCourseViewmodel = ViewModelProviders.of(this).get(CourseViewmodel.class);
        //lang nghe va quan sat du lieu
        mCourseViewmodel.getCreateCourseReponse().observe(this, new Observer<Map>() {
            @Override
            public void onChanged(Map map) {
                mDialog.dismiss();
                if(map.get(Constain.keyMapErr) != null){
                    Toast.makeText(CreateCourse2Activity.this, map.get(Constain.keyMapErr)+"", Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(CreateCourse2Activity.this,ChangeCourseAvataActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                }
            }
        });
    }

    //ánh xạ view
    private void initView() {
        mEdtCourseName = findViewById(R.id.edt_courseName);
        mEdtObjectives = findViewById(R.id.edt_objectives);
        mEdtDesciption = findViewById(R.id.edt_desciption);
        mEdtField = findViewById(R.id.edt_field);
        mEdtPrice = findViewById(R.id.edt_price);
        mEdtDiscount = findViewById(R.id.edt_discount);
    }

    //lắng nghe sự kiện onClicked
    private void listenerOnclicked() {
        findViewById(R.id.btn_next).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                if (mCourseViewmodel.checkValidation(mEdtCourseName, mEdtObjectives, mEdtDesciption, mEdtField, mEdtPrice, mEdtDiscount)) {
                    mCourseViewmodel.createCourse();
                    mDialog = Helpers.showLoadingDialog(this);
                    mDialog.show();
                }
                break;
        }
    }
}
