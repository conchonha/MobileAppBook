package com.example.mobileappbook.src.page.account.recover_activity;

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
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.src.viewmodel.acount.recover.RecoverViewmodel;
import com.example.mobileappbook.utils.Helpers;

public class RecoverActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtEmail;
    //variable
    private RecoverViewmodel mRecoverViewmodel;
    private Dialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);

        initViewmodel();
        initView();
        listenerOnclicked();
    }

    //khoi tao viewmodel
    private void initViewmodel() {
        mRecoverViewmodel = ViewModelProviders.of(RecoverActivity.this).get(RecoverViewmodel.class);

        //lang nghe va quan sat du lieu
        mRecoverViewmodel.getReponseRecover().observe(RecoverActivity.this, new Observer<ErrorRepone>() {
            @Override
            public void onChanged(ErrorRepone errorRepone) {
                Toast.makeText(RecoverActivity.this, "Code - " + errorRepone.getmCode() + " message - " + errorRepone.getmMessage(), Toast.LENGTH_SHORT).show();
                if (errorRepone.getmCode() == 200) {
                    finish();
                }
                mDialog.dismiss();
            }
        });
    }

    //anh xa view
    private void initView() {
        mEdtEmail = findViewById(R.id.edt_email);
    }

    //lang nghe su kien onclicked view
    private void listenerOnclicked() {
        findViewById(R.id.btn_send).setOnClickListener(this);
        findViewById(R.id.img_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                if (mRecoverViewmodel.checkValidation(mEdtEmail)) {
                    mDialog = Helpers.showLoadingDialog(RecoverActivity.this);
                    mDialog.show();
                    mRecoverViewmodel.forgotPassword(mEdtEmail.getText().toString());
                }
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }
}
