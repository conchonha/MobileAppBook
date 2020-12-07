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
import com.example.mobileappbook.cores.body.ActiveAcountBody;
import com.example.mobileappbook.cores.reponse.error_reponse.ErrorRepone;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;
import com.example.mobileappbook.src.viewmodel.acount.ActiveAcountViewModel;
import com.example.mobileappbook.utils.Helpers;

public class ActiveAcountActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtActivationCode;
    private Dialog mDialog;

    private ActiveAcountViewModel mActiveAcountViewModel;
    private String mEmail = "";
    private String TAG = "ActiveAcountActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acount);
        initView();
        init();
        initViewModel();
        listenerOnclicked();
    }

    private void listenerOnclicked() {
        findViewById(R.id.btn_send).setOnClickListener(this);
    }

    //khoi tạo viewmodel
    private void initViewModel() {
        mActiveAcountViewModel = ViewModelProviders.of(this).get(ActiveAcountViewModel.class);
        //lang nghe va quan sat du lieu
        mActiveAcountViewModel.getDataReponseActiveAcount().observe(this, new Observer<UserReponse>() {
            @Override
            public void onChanged(UserReponse userReponse) {
                Toast.makeText(getApplicationContext(), "Active Success", Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
                Helpers.intentClear(ActiveAcountActivity.this,LoginActivity.class);
            }
        });

        mActiveAcountViewModel.getErrorReponse().observe(this, new Observer<ErrorRepone>() {
            @Override
            public void onChanged(ErrorRepone errorRepone) {
                Toast.makeText(getApplicationContext(), "Active fail: code - " + errorRepone.getmCode() + " message - " + errorRepone.getmMessage(), Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
            }
        });
    }

    //anh xa view
    private void initView() {
        mEdtActivationCode = findViewById(R.id.edt_activation_code);
    }

    private void init() {
        Intent intent = getIntent();
        if(intent.hasExtra("email")){
            mEmail = intent.getStringExtra("email");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send :
                if(mEdtActivationCode.getText().toString().isEmpty()){
                    mEdtActivationCode.setError("active_token invalid");
                }else{
                    mDialog = Helpers.showLoadingDialog(this);
                    mDialog.show();
                    mActiveAcountViewModel.activeAcount(new ActiveAcountBody(mEmail,mEdtActivationCode.getText().toString()));
                }
                break;
        }
    }
}