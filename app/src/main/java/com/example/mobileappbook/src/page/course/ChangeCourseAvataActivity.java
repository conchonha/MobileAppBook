package com.example.mobileappbook.src.page.course;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.body.CreateCourseBody;
import com.example.mobileappbook.cores.reponse.course.CourseReponse;
import com.example.mobileappbook.src.viewmodel.course.CourseViewmodel;
import com.example.mobileappbook.utils.Constain;
import com.example.mobileappbook.utils.Helpers;

import java.io.File;
import java.io.FileNotFoundException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ChangeCourseAvataActivity extends AppCompatActivity implements View.OnClickListener {
    private final int REQUEST_IMAGE_AVATAR_LIBLARY = 1234;
    private ImageView mImgCourseCover;
    //variable
    private CourseViewmodel mCourseViewmodel;
    private Dialog mDialog;
    private File mFileImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_course_avt);
        initView();
        initViewModel();
        listenerOnclicked();
    }

    //khởi tạo viewmodel
    private void initViewModel() {
        mCourseViewmodel = ViewModelProviders.of(this).get(CourseViewmodel.class);

        mCourseViewmodel.getCreateCourseReponse().observe(this, new Observer<CourseReponse>() {
            @Override
            public void onChanged(CourseReponse courseReponse) {
                mDialog.dismiss();
                if (courseReponse != null) {
                    if (courseReponse.getStatus() != null) {
                        if (courseReponse.getStatus().equals("success")) {
                            Toast.makeText(ChangeCourseAvataActivity.this, "Create courser success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LessonListActivity.class));
                            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                        } else {
                            Toast.makeText(ChangeCourseAvataActivity.this, courseReponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ChangeCourseAvataActivity.this, courseReponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initView() {
        mImgCourseCover = findViewById(R.id.img_course_cover);
    }

    //lắng nghe sự kiện oncicked
    private void listenerOnclicked() {
        findViewById(R.id.btn_course_library).setOnClickListener(this);
        findViewById(R.id.btn_create_course).setOnClickListener(this);
        findViewById(R.id.img_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_course_library:
                Intent intent3 = new Intent(Intent.ACTION_PICK).setType("image/*");
                startActivityForResult(intent3, REQUEST_IMAGE_AVATAR_LIBLARY);
                break;
            case R.id.btn_create_course:
                Intent intent = getIntent();
                CreateCourseBody createCourseBody = (CreateCourseBody) intent.getSerializableExtra(Constain.keyCreateCourseBody);
                if (createCourseBody != null && mFileImage != null) {
                    mDialog = Helpers.showLoadingDialog(this);
                    mDialog.show();
                    RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/jpg"), mFileImage);
                    MultipartBody.Part part = MultipartBody.Part.createFormData("image", mFileImage.getName(), fileReqBody);
                    mCourseViewmodel.createCourse(createCourseBody, part);
                } else {
                    Toast.makeText(this, "The data is not properly formatted", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.img_back:
                finish();
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
