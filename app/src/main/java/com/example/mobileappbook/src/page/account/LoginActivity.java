package com.example.mobileappbook.src.page.account;

import android.app.Dialog;
import android.content.Intent;
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
import com.example.mobileappbook.src.page.tabbar.TabBarActivity;
import com.example.mobileappbook.src.viewmodel.acount.LoginViewmodel;
import com.example.mobileappbook.utils.Helpers;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtEmail, mEdtPassword;
    //variable
    private Dialog mDialog;
    private LoginViewmodel mLoginViewmodel;
    private String TAG = "LoginActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViewModel();
        initView();
        listenerOnclicked();
    }

    //khoi tao viewmodel
    private void initViewModel() {
        mLoginViewmodel = ViewModelProviders.of(LoginActivity.this).get(LoginViewmodel.class);

        //lang nghe va quan sat su thay doi cua du lieu
        mLoginViewmodel.getUserReponse().observe(LoginActivity.this, new Observer<UserReponse>() {
            @Override
            public void onChanged(UserReponse userReponse) {
                mDialog.dismiss();
                if (userReponse.getMessage() != null) {
                    Toast.makeText(LoginActivity.this, userReponse.getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    mLoginViewmodel.saveUser(userReponse);
                    if (userReponse.getActive() == 0) {
                        Toast.makeText(LoginActivity.this, "Please active acount", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ActiveAccountActivity.class).putExtra("email", userReponse.getEmail()));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        Helpers.intentClear(LoginActivity.this, TabBarActivity.class);
                    }
                }
            }
        });
    }

    //lang nghe su kien onclicked view
    private void listenerOnclicked() {
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.txt_forgotPassword).setOnClickListener(this);
        findViewById(R.id.img_back).setOnClickListener(this);
        findViewById(R.id.txt_create_acount).setOnClickListener(this);
    }

    //anh xa view
    private void initView() {
        mEdtEmail = findViewById(R.id.edt_email);
        mEdtPassword = findViewById(R.id.edt_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (mLoginViewmodel.checkValidation(mEdtEmail, mEdtPassword)) {
                    mDialog = Helpers.showLoadingDialog(LoginActivity.this);
                    mDialog.show();
                    mLoginViewmodel.login();
                }
                break;
            case R.id.txt_forgotPassword:
                startActivity(new Intent(getApplicationContext(), RecoverActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
            case R.id.txt_create_acount:
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }
}
