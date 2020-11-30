package com.example.mobileappbook.src.page.login_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileappbook.R;
import com.example.mobileappbook.src.page.recover_activity.RecoverActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        listenerOnclicked();

    }

    private void listenerOnclicked() {
        findViewById(R.id.txt_forgotPassword).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_forgotPassword :
                startActivity(new Intent(getApplicationContext(), RecoverActivity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                break;
        }
    }
}
