package com.example.mobileappbook.src.page.course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileappbook.R;

public class QuestionActivity extends AppCompatActivity {
    private Button mBtnCreateQuestion;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_question);
        listenerOnClicklistener();
        initView();
    }
    private void listenerOnClicklistener() {
        mBtnCreateQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuestionActivity.this,Question2Activity.class));
            }
        });
    }

    private void initView() {
        mBtnCreateQuestion = findViewById(R.id.btn_create);
    }
}
