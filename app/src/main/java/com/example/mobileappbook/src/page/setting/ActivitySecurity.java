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
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.src.viewmodel.setting.SecurityViewModel;
import com.example.mobileappbook.utils.Helpers;

public class ActivitySecurity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtPass,mEdtNewPass,mEdtConfirm;
    private SecurityViewModel mSecurityViewModel;
    private Dialog mDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        initView();
        initViewModel();
        listenerOnclicked();
    }

    //khoi tao viewmodel
    private void initViewModel() {
        mSecurityViewModel = ViewModelProviders.of(this).get(SecurityViewModel.class);

        //lang nghe va quan sat su thay doi cua du lieu
        mSecurityViewModel.getUserChangePasswordReponse().observe(this, new Observer<UserReponse>() {
            @Override
            public void onChanged(UserReponse userReponse) {
                mDialog.dismiss();
                if(userReponse.getMessage() != null){
                    Toast.makeText(ActivitySecurity.this, userReponse.getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ActivitySecurity.this, "Security Success", Toast.LENGTH_SHORT).show();
                    mSecurityViewModel.saveUser(userReponse);
                    finish();
                }
            }
        });
    }

    //anh xa view
    private void initView() {
        mEdtPass = findViewById(R.id.edt_password);
        mEdtNewPass = findViewById(R.id.edt_newpassword);
        mEdtConfirm = findViewById(R.id.edt_confirmpassword);
        findViewById(R.id.btn_update).setOnClickListener(this);
    }

    //lang nghe su kien onclicked view
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
                if(mSecurityViewModel.checkValidation(mEdtPass,mEdtNewPass,mEdtConfirm)){
                    mDialog = Helpers.showLoadingDialog(ActivitySecurity.this);
                    mDialog.show();
                    mSecurityViewModel.changePassword();
                }
                break;
        }
    }
}
