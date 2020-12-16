package com.example.mobileappbook.src.fragment.fragment_courses;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mobileappbook.R;
import com.example.mobileappbook.src.page.course.CourseList2Activity;
import com.example.mobileappbook.src.page.course.CreateCourseActivity;
import com.example.mobileappbook.src.page.tabbar.TabBarActivity;
import com.example.mobileappbook.utils.Constain;
import com.example.mobileappbook.utils.Helpers;

import static android.app.Activity.RESULT_OK;

public class mycoursesFragment extends Fragment{
    private View mView;
    private Button mBtnSwipe;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_mycourses,container,false);
        initView();
        listenerOnclick();
        return mView;
    }

    private void listenerOnclick() {
        mBtnSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animTranslate = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_translate);
                mBtnSwipe.startAnimation(animTranslate);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getContext(), CourseList2Activity.class));
                        getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                    }
                },2000);

            }
        });
    }

    private void initView() {
        mBtnSwipe = mView.findViewById(R.id.btn_swipe);
    }

}
