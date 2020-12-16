package com.example.mobileappbook.src.page.course;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileappbook.R;
import com.example.mobileappbook.utils.Helpers;

import java.io.File;
import java.io.FileNotFoundException;

public class ChangeCourseAvataActivity extends AppCompatActivity implements View.OnClickListener {
    private final int REQUEST_IMAGE_AVATAR_LIBLARY = 1234;
    private ImageView mImgCourseCover;
    private File mFileImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_course_avt);
        initView();
        listenerOnclicked();
    }

    private void initView() {
        mImgCourseCover = findViewById(R.id.img_course_cover);
    }

    private void listenerOnclicked() {
        findViewById(R.id.btn_course_library).setOnClickListener(this);
        findViewById(R.id.btn_create_course);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_course_library:
                Intent intent3 = new Intent(Intent.ACTION_PICK).setType("image/*");
                startActivityForResult(intent3, REQUEST_IMAGE_AVATAR_LIBLARY);
                break;
            case R.id.btn_create_course:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case REQUEST_IMAGE_AVATAR_LIBLARY:
                    Bitmap bitmap2 = null;
                    try {
                        bitmap2 = BitmapFactory.decodeStream(getContentResolver().openInputStream(data.getData()));
                        mImgCourseCover.setImageBitmap(bitmap2);
                        mFileImage = Helpers.savebitmap(bitmap2);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
