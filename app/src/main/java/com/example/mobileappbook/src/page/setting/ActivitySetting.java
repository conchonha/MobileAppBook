package com.example.mobileappbook.src.page.setting;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.src.page.account.LoginActivity;
import com.example.mobileappbook.src.viewmodel.setting.SettingViewModel;
import com.example.mobileappbook.utils.Constain;
import com.example.mobileappbook.utils.Helpers;
import com.example.mobileappbook.utils.SharePrefs;
import com.google.gson.Gson;

import java.util.Map;

public class ActivitySetting extends AppCompatActivity implements View.OnClickListener {
    private SettingViewModel mSettingViewModel;
    private Dialog mDialog;
    private UserReponse mUserReponse;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initViewModel();
        init();
        listenerOnclicked();
    }

    private void init() {
        mUserReponse = new Gson().fromJson(new SharePrefs(this).getUser(),UserReponse.class);
    }

    //khởi tạo viewmodel
    private void initViewModel() {
        mSettingViewModel = ViewModelProviders.of(this).get(SettingViewModel.class);

        //lắng nghe và quan sát sự thay đổi của dữ liệu
        mSettingViewModel.getUserLogoutReponse().observe(this, new Observer<Map>() {
            @Override
            public void onChanged(Map map) {
                mDialog.dismiss();
                if(map.get(Constain.keyMapErr) != null){
                    Toast.makeText(ActivitySetting.this, map.get(Constain.keyMapErr)+"", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ActivitySetting.this, "Logout Success", Toast.LENGTH_SHORT).show();
                    Helpers.intentClear(ActivitySetting.this, LoginActivity.class);
                }
            }
        });
    }

    //lang nghe su kien onclick
    private void listenerOnclicked() {
        findViewById(R.id.img_back).setOnClickListener(this);
        findViewById(R.id.txt_update_infor).setOnClickListener(this);
        findViewById(R.id.txt_security).setOnClickListener(this);
        findViewById(R.id.txt_change).setOnClickListener(this);
        findViewById(R.id.cart_logout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.txt_update_infor:
                startActivity(new Intent(getApplicationContext(), UserInfoActivity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                break;
            case R.id.txt_security:
                startActivity(new Intent(getApplicationContext(), ActivitySecurity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                break;
            case R.id.txt_change:
                startActivity(new Intent(getApplicationContext(), UserAvatarActivity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                break;
            case R.id.cart_logout:
                mDialog = Helpers.showLoadingDialog(this);
                mDialog.show();
                mSettingViewModel.userLogout(mUserReponse.getmAuthToken());
                break;
        }
    }
}
