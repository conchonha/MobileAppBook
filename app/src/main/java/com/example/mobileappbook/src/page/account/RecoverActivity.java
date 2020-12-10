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
import com.example.mobileappbook.src.viewmodel.acount.RecoverViewmodel;
import com.example.mobileappbook.utils.Helpers;

import java.util.Map;

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
        mRecoverViewmodel.getReponseRecover().observe(this, new Observer<Map>() {
            @Override
            public void onChanged(Map map) {
                mDialog.dismiss();
                if (map.get("300") != null) {
                    Toast.makeText(RecoverActivity.this, map.get("300")+"", Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(getApplicationContext(), ResetPasswordActivity.class).putExtra("email",mEdtEmail.getText().toString()));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                    finish();
                }
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
                    mDialog  = Helpers.showLoadingDialog(this);
                    mDialog.show();
                    mRecoverViewmodel.recoverPassword(mEdtEmail);
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }
}
