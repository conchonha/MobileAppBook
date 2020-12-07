package com.example.mobileappbook.src.page.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileappbook.R;

public class ActivitySetting extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        listenerOnclicked();
    }

    //lang nghe su kien onclick
    private void listenerOnclicked() {
        findViewById(R.id.img_back).setOnClickListener(this);
        findViewById(R.id.txt_update_infor).setOnClickListener(this);
        findViewById(R.id.txt_security).setOnClickListener(this);
        findViewById(R.id.txt_change).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.txt_update_infor:
                startActivity(new Intent(getApplicationContext(), ActivityUpdateInfor.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                break;
            case R.id.txt_security:
                startActivity(new Intent(getApplicationContext(), ActivitySecurity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                break;
            case R.id.txt_change:
                startActivity(new Intent(getApplicationContext(), ActivityChangeAvatar.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                break;

        }
    }
}
