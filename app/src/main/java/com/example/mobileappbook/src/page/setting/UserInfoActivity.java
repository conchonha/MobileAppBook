package com.example.mobileappbook.src.page.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.src.viewmodel.acount.UpdateInforViewmodel;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtYourName,mEdtPhoneNumber,mEdtAddress,mEdtEmail,mEdtJob;
    //variable
    private UpdateInforViewmodel mUpdateInfoViewmodel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initViewModel();
        initView();
        init();
        listenerOnclicked();
    }

    private void init() {
        UserReponse userReponse = mUpdateInfoViewmodel.getUserLocal();
        if(userReponse != null){
            mEdtYourName.setText(userReponse.getName());
            mEdtPhoneNumber.setText(userReponse.getPhone());
            mEdtAddress.setText(userReponse.getAddress());
            mEdtEmail.setText(userReponse.getEmail());
            mEdtJob.setText(userReponse.getDescription());
        }
    }

    //khoi tao viewmodel
    private void initViewModel() {
        mUpdateInfoViewmodel = ViewModelProviders.of(UserInfoActivity.this).get(UpdateInforViewmodel.class);
    }

    //anh xa view
    private void initView() {
        mEdtYourName = findViewById(R.id.edt_yourName);
        mEdtPhoneNumber = findViewById(R.id.edt_phoneNumber);
        mEdtAddress = findViewById(R.id.edt_address);
        mEdtEmail = findViewById(R.id.edt_email);
        mEdtJob = findViewById(R.id.edt_job);
    }

    //lang nghe su kien onclic
    private void listenerOnclicked() {
        findViewById(R.id.img_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_update:
                if(mUpdateInfoViewmodel.checkvalidation(mEdtYourName,mEdtPhoneNumber,mEdtAddress,mEdtJob)){

                }
                break;
        }
    }
}
