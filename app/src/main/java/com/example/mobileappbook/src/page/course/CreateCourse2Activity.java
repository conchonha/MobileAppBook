package com.example.mobileappbook.src.page.course;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.mobileappbook.R;

public class CreateCourse2Activity extends AppCompatActivity {
    private CardView mBtnChangeCourseAvt;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course2);
        initView();
        listenerOnclick();
    }

    private void listenerOnclick() {
        mBtnChangeCourseAvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateCourse2Activity.this,ChangeCourseAvataActivity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
            }
        });
    }

    private void initView() {
        mBtnChangeCourseAvt = findViewById(R.id.btn_next);
    }
}
