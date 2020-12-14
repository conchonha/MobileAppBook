package com.example.mobileappbook.src.page.course;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileappbook.R;

public class CreateCourseActivity extends AppCompatActivity {
    private Button mButtonCreate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);
        initView();
        onClicklistener();

    }

    private void onClicklistener() {
        mButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateCourseActivity.this,CreateCourse2Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        mButtonCreate = findViewById(R.id.btn_create);
    }
}
