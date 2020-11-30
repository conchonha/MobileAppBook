package com.example.mobileappbook.utils;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;
import com.google.gson.Gson;

public class SharePrefs{
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharePrefs(Activity activity){
        sharedPreferences = activity.getSharedPreferences("datalogin",activity.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void saveUser(UserReponse userReponse){
        Gson gson = new Gson();
        String user = gson.toJson(userReponse, UserReponse.class);
        editor.putString(Constain.isUser,user).commit();
    }

}
