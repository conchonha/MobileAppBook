package com.example.mobileappbook.src.page.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.src.viewmodel.acount.ResetPasswordViewmodel;
import com.example.mobileappbook.utils.Helpers;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtEmail,mEdtToken,mEdtNewPass;
    private ResetPasswordViewmodel mResetPasswordViewmodel;
    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initViewModel();
        initView();
        init();
        listenerOnclicked();
    }

    private void init() {
        Intent intent = getIntent();
        if(intent.hasExtra("email")){
            mEdtEmail.setText(intent.getStringExtra("email"));
        }
    }

    private void listenerOnclicked() {
        findViewById(R.id.btn_send).setOnClickListener(this);
    }

    //ánh xạ view
    private void initView() {
        mEdtEmail = findViewById(R.id.edt_email);
        mEdtToken = findViewById(R.id.edt_token);
        mEdtNewPass = findViewById(R.id.edt_password);
    }

    //khởi tạo viewmodel
    private void initViewModel() {
        mResetPasswordViewmodel = ViewModelProviders.of(this).get(ResetPasswordViewmodel.class);

        //quan sát và lắng nghe sự thay đổi của dữ liệu
        mResetPasswordViewmodel.getmReponseResetPassword().observe(this, new Observer<UserReponse>() {
            @Override
            public void onChanged(UserReponse userReponse) {
                mDialog.dismiss();
                if(userReponse.getMessage() != null){
                    Toast.makeText(ResetPasswordActivity.this, userReponse.getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ResetPasswordActivity.this, "Reset password success", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send :
                if(mResetPasswordViewmodel.checkValidation(mEdtEmail,mEdtToken,mEdtNewPass)){
                    mDialog = Helpers.showLoadingDialog(this);
                    mDialog.show();
                    mResetPasswordViewmodel.resetPassword();
                }
                break;
        }
    }
}