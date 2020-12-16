package com.example.mobileappbook.src.page.course;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mobileappbook.R;
import com.example.mobileappbook.compoments.OnItemSelectedListenner;
import com.example.mobileappbook.cores.reponse.featured.GetAllCategoryReponse;
import com.example.mobileappbook.src.viewmodel.course.CourseViewmodel;
import com.example.mobileappbook.utils.Constain;
import com.example.mobileappbook.utils.Helpers;
import com.google.gson.Gson;

import java.util.List;


public class CreateCourse2Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtCourseName,mEdtObjectives,mEdtDesciption,mEdtPrice,mEdtDiscount;
    private CourseViewmodel mCourseViewmodel;
    private Dialog mDialog;
    private Spinner mSpiner;
    private SpinnerAdapter mSpinnerAdapter;
    private GetAllCategoryReponse mGetAllCategoryReponse;

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

        mCourseViewmodel.getAllCategory().observe(this, new Observer<List<GetAllCategoryReponse>>() {
            @Override
            public void onChanged(List<GetAllCategoryReponse> getAllCategoryReponses) {
                mSpinnerAdapter = new com.example.mobileappbook.src.adapter.spinner_adapter.SpinnerAdapter(getApplicationContext(), getAllCategoryReponses);
                mSpiner.setAdapter(mSpinnerAdapter);
            }
        });
    }

    //ánh xạ view
    private void initView() {
        mEdtCourseName = findViewById(R.id.edt_courseName);
        mEdtObjectives = findViewById(R.id.edt_objectives);
        mEdtDesciption = findViewById(R.id.edt_desciption);
        mEdtPrice = findViewById(R.id.edt_price);
        mEdtDiscount = findViewById(R.id.edt_discount);
        mSpiner = findViewById(R.id.spiner_field);
    }

    //lắng nghe sự kiện onClicked
    private void listenerOnclicked() {
        mSpiner.setOnItemSelectedListener(new OnItemSelectedListenner() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                super.onItemSelected(parent, view, position, id);
                mGetAllCategoryReponse = (GetAllCategoryReponse) parent.getItemAtPosition(position);
            }
        });

        findViewById(R.id.btn_next).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                if (mCourseViewmodel.checkValidation(mEdtCourseName, mEdtObjectives, mEdtDesciption, mGetAllCategoryReponse.getId(), mEdtPrice, mEdtDiscount)) {
                    mDialog = Helpers.showLoadingDialog(this);
                    mDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mDialog.dismiss();
                            startActivity(new Intent(CreateCourse2Activity.this,ChangeCourseAvataActivity.class).putExtra(Constain.keyCreateCourseBody,mCourseViewmodel.getmCreateCourseBody()));
                        }
                    },2000);
                }
                break;
        }
    }
}
