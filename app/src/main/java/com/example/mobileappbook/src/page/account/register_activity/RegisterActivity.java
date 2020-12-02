package com.example.mobileappbook.src.page.account.register_activity;

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
import com.example.mobileappbook.cores.body.RegisterBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;
import com.example.mobileappbook.src.page.tabbar.TabBarActivity;
import com.example.mobileappbook.src.viewmodel.acount.register.RegisterViewmodel;
import com.example.mobileappbook.utils.SharePrefs;
import com.example.mobileappbook.utils.*;

import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private RegisterViewmodel mRegisterViewmodel;
    private EditText mEdtYourName, mEdtPhoneNumber, mEdtAddress, mEdtEmail, mEdtPassword, mEdtConfirm;
    //variable
    private Dialog mDialog;
    private SharePrefs mSharePrefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViewmodel();
        initView();
        listenerOnclickedView();

    }

    //lang nghe su kien onclcked view
    private void listenerOnclickedView() {
        findViewById(R.id.card_register1).setOnClickListener(this);
        findViewById(R.id.img_back).setOnClickListener(this);
    }

    //anh xa view
    private void initView() {
        mEdtYourName = findViewById(R.id.edt_yourName);
        mEdtPhoneNumber = findViewById(R.id.edt_phoneNumber);
        mEdtAddress = findViewById(R.id.edt_address);
        mEdtEmail = findViewById(R.id.edt_email);
        mEdtPassword = findViewById(R.id.edt_password);
        mEdtConfirm = findViewById(R.id.edt_confirm);
    }

    //khoi tao viewmodel
    private void initViewmodel() {
        mRegisterViewmodel = ViewModelProviders.of(RegisterActivity.this).get(RegisterViewmodel.class);

        //lang nghe va quan sat su thay doi cua du lieu
        mRegisterViewmodel.getReponseRegister().observe(RegisterActivity.this, new Observer<Map>() {
            @Override
            public void onChanged(Map userReponse) {
//                Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
//                mSharePrefs = new SharePrefs(RegisterActivity.this);
//                mSharePrefs.saveUser(userReponse);
//                mDialog.dismiss();
//                Intent intent = new Intent(getApplicationContext(), TabBarActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_register1:
                RegisterBody registerBody = new RegisterBody(mEdtYourName.getText().toString(), mEdtEmail.getText().toString(), mEdtPassword.getText().toString(), mEdtPhoneNumber.getText().toString(), mEdtAddress.getText().toString(), mEdtAddress.getText().toString(), "nam");

                if (mRegisterViewmodel.checkValidationsRegister(mEdtYourName, mEdtPhoneNumber, mEdtAddress, mEdtEmail, mEdtPassword, mEdtConfirm)) {
                    mDialog = Helpers.showLoadingDialog(RegisterActivity.this);
                    mDialog.show();
                    mRegisterViewmodel.register(registerBody);
                }
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }


}


