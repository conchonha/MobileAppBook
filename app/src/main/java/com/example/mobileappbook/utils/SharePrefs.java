package com.example.mobileappbook.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;
import com.google.gson.Gson;

public class SharePrefs{
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public SharePrefs(Context context){
        sharedPreferences = context.getSharedPreferences("datalogin",context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void saveUser(UserReponse userReponse){
        Gson gson = new Gson();
        String user = gson.toJson(userReponse, UserReponse.class);
        editor.putString(Constain.isUser,user).commit();
    }

    public String getUser(){
        return sharedPreferences.getString(Constain.isUser,"");
    }

}
