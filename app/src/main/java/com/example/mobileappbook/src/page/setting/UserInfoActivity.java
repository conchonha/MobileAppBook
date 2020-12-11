package com.example.mobileappbook.src.page.setting;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.body.UserInfoBody;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.src.viewmodel.setting.UpdateInforViewmodel;
import com.example.mobileappbook.utils.Helpers;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtYourName,mEdtPhoneNumber,mEdtAddress,mEdtEmail,mEdtJob;
    //variable
    private UpdateInforViewmodel mUpdateInfoViewmodel;
    private UserReponse mUserReponse;
    private Dialog mDialog;

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
        mUserReponse = mUpdateInfoViewmodel.getUserLocal();
        if(mUserReponse != null){
            mEdtYourName.setText(mUserReponse.getName());
            mEdtPhoneNumber.setText(mUserReponse.getPhone());
            mEdtAddress.setText(mUserReponse.getAddress());
            mEdtEmail.setText(mUserReponse.getEmail());
            mEdtJob.setText(mUserReponse.getDescription());
        }
    }

    //khoi tao viewmodel
    private void initViewModel() {
        mUpdateInfoViewmodel = ViewModelProviders.of(UserInfoActivity.this).get(UpdateInforViewmodel.class);

        //lăng nghe va quan sat su thay doi cua du lieu
        mUpdateInfoViewmodel.getChangeProfileReponse().observe(this, new Observer<UserReponse>() {
            @Override
            public void onChanged(UserReponse userReponse) {
                mDialog.dismiss();
                if(userReponse.getMessage() != null){
                    Toast.makeText(UserInfoActivity.this, userReponse.getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    mUserReponse.setName(mEdtYourName.getText().toString());
                    mUserReponse.setPhone(mEdtPhoneNumber.getText().toString());
                    mUserReponse.setAddress(mEdtAddress.getText().toString());
                    mUserReponse.setDescription(mEdtJob.getText().toString());
                    mUpdateInfoViewmodel.saveUserLocal(mUserReponse);
                    Toast.makeText(UserInfoActivity.this, "Update Thành Công", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
        findViewById(R.id.btn_update).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_update:
                mDialog = Helpers.showLoadingDialog(this);
                mDialog.show();
                UserInfoBody userInfoBody = new UserInfoBody(mEdtYourName.getText().toString(),mEdtPhoneNumber.getText().toString(),mEdtAddress.getText().toString(),mEdtJob.getText().toString(),"");
                mUpdateInfoViewmodel.updateUserInfo(userInfoBody,mUserReponse.getmAuthToken());
                break;
        }
    }
}
