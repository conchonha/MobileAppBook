package com.example.mobileappbook.src.page.splash_activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.mobileappbook.R;

public class StartScreenActivity extends AppCompatActivity {
    private CardView mCardViewLogin, mCardViewRegister;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startscreen);

        init();
        initView();
    }

    private void initView() {

        // button login
        mCardViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_login);
            }
        });
        //button register
        mCardViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_register);
            }
        });
    }

    private void init() {
        mCardViewLogin = findViewById(R.id.btn_login);
        mCardViewRegister = findViewById(R.id.btn_register);
    }
}
