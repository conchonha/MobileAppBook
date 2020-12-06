package com.example.mobileappbook.src.page.account.forget_password_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;
import com.example.mobileappbook.src.page.account.recover_activity.RecoverActivity;
import com.example.mobileappbook.src.viewmodel.acount.reset_password.ResetPasswordViewmodel;
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
        listenerOnclicked();
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
        mResetPasswordViewmodel.getErrorReponseResetpass().observe(this, new Observer<ErrorRepone>() {
            @Override
            public void onChanged(ErrorRepone errorRepone) {
                Toast.makeText(getApplicationContext(), "Code - " + errorRepone.getmCode() + " message - " + errorRepone.getmMessage(), Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
            }
        });

        mResetPasswordViewmodel.getReponseResetPass().observe(this, new Observer<UserReponse>() {
            @Override
            public void onChanged(UserReponse userReponse) {
                Toast.makeText(ResetPasswordActivity.this, "Reset password success", Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
                finish();
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