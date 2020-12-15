package com.example.mobileappbook.src.page.course;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileappbook.R;

public class ChangeCourseAvataActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImageCourse;
    private Button mButtonCover;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_course_avt);
        init();
        listenerOnclicked();
    }

    private void listenerOnclicked() {
        findViewById(R.id.btn_course_library).setOnClickListener(this);
    }

    private void init() {
        mButtonCover = findViewById(R.id.btn_course_library);
        mImageCourse = findViewById(R.id.img_course_cover);
    }

    @Override
    public void onClick(View v) {

    }
}
