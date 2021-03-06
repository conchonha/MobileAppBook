package com.example.mobileappbook.src.page.course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobileappbook.R;

public class LessonListActivity extends AppCompatActivity {
    private Button mBtnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list2);
        initView();
        listenerOnClicklistener();

    }

    private void listenerOnClicklistener() {
        mBtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LessonListActivity.this,QuestionActivity.class));
            }
        });
    }

    private void initView() {
        mBtnCreate = findViewById(R.id.btn_create);
    }
}