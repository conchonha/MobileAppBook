package com.example.mobileappbook.src.page.course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileappbook.R;

public class CreateCourse2Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtCourseName,mEdtObjectives,mEdtDesciption,mEdtField,mEdtPrice,mEdtDiscount;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course2);
        initView();
        listenerOnclicked();
    }

    //ánh xạ view
    private void initView() {
    }

    //lắng nghe sự kiện onClicked
    private void listenerOnclicked() {
        findViewById(R.id.btn_next).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                startActivity(new Intent(CreateCourse2Activity.this,ChangeCourseAvataActivity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                break;
        }
    }
}
