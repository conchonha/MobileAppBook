package com.example.mobileappbook.utils;

import android.app.Activity;
import android.content.SharedPreferences;

public class SharePrefs{
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharePrefs(Activity activity){
        sharedPreferences = activity.getSharedPreferences("datalogin",activity.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void saveIsLogin(Boolean bool){
        editor.putBoolean(Constain.keyIsLogin,bool);
        editor.commit();
    }

    public boolean getIsLogin(){
        return sharedPreferences.getBoolean(Constain.keyIsLogin,false);
    }
}
