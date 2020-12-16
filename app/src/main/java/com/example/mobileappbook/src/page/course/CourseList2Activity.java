package com.example.mobileappbook.src.page.course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.mobileappbook.R;
import com.example.mobileappbook.src.page.tabbar.TabBarActivity;
import com.example.mobileappbook.utils.Helpers;

public class CourseList2Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list2);
        listenerOnclicked();
    }

    //lắng nghe sự kiện onclick
    private void listenerOnclicked() {
        findViewById(R.id.img_back).setOnClickListener(this);
        findViewById(R.id.btn_next).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                Helpers.intentClearOnTapSelected2(this, TabBarActivity.class);
                finish();
                break;
            case R.id.btn_next:
                startActivity(new Intent(getApplicationContext(),CreateCourseActivity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Helpers.intentClearOnTapSelected2(this, TabBarActivity.class);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}