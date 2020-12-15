package com.example.mobileappbook.src.page.course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileappbook.R;

public class CreateCourseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);
        listenerOnClicklistener();

    }

    private void listenerOnClicklistener() {
        findViewById(R.id.btn_create).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_create:
                startActivity(new Intent(CreateCourseActivity.this,CreateCourse2Activity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                break;
        }
    }
}
