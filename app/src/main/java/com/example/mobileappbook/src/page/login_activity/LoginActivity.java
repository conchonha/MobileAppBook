package com.example.mobileappbook.src.page.login_activity;

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
import com.example.mobileappbook.cores.body.LoginBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;
import com.example.mobileappbook.src.viewmodel.login.LoginViewmodel;
import com.example.mobileappbook.utils.Helpers;
import com.example.mobileappbook.utils.SharePrefs;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtEmail;
    private EditText mEdtPassword;
    //variable
    private Dialog mDialog;
    private LoginViewmodel mLoginViewmodel;
    private SharePrefs mSharePrefs;

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
        mLoginViewmodel.getErrorReponse().observe(LoginActivity.this, new Observer<ErrorRepone>() {
            @Override
            public void onChanged(ErrorRepone errorRepone) {
                Toast.makeText(LoginActivity.this, "Login thất bại: code - " + errorRepone.getmCode() + " message - " + errorRepone.getmMessage(), Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
            }
        });

        mLoginViewmodel.getLoginReponse().observe(LoginActivity.this, new Observer<UserReponse>() {
            @Override
            public void onChanged(UserReponse userReponse) {
                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                mSharePrefs = new SharePrefs(LoginActivity.this);
                mSharePrefs.saveUser(userReponse);
                mDialog.dismiss();
            }
        });
    }

    //lang nghe su kien onclicked view
    private void listenerOnclicked() {
        findViewById(R.id.btn_login).setOnClickListener(this);
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
                LoginBody loginBody = new LoginBody(mEdtEmail.getText().toString(), mEdtPassword.getText().toString());

                if (mLoginViewmodel.checkValidation(mEdtEmail, mEdtPassword)) {
                    mDialog = Helpers.showLoadingDialog(LoginActivity.this);
                    mDialog.show();
                    mLoginViewmodel.login(loginBody);
                }
                break;
        }
    }
}
