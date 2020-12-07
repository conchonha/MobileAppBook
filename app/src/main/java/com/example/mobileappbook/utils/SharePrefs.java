package com.example.mobileappbook.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCourseReponse;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;
import com.example.mobileappbook.model.CartModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SharePrefs {
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditTor;
    private Gson mGson = new Gson();
    private String TAG = "SharePrefs";

    public SharePrefs(Context context) {
        mSharedPreferences = context.getSharedPreferences("datalogin", context.MODE_PRIVATE);
        mEditTor = mSharedPreferences.edit();
    }

    public void saveUser(UserReponse userReponse) {
        String user = mGson.toJson(userReponse, UserReponse.class);
        mEditTor.putString(Constain.isUser, user).commit();
    }

    public void saveCart(GetAllCourseReponse reponse, Context context) {
        if (getCart().equals("")) {
            List<GetAllCourseReponse> allCourseReponseList = new ArrayList<>();
            CartModel cartModel = new CartModel();
            allCourseReponseList.add(reponse);
            cartModel.setList(allCourseReponseList);
            String cart = mGson.toJson(cartModel, CartModel.class);
            mEditTor.putString(Constain.cart, cart).commit();
        } else {
            CartModel cartModel = mGson.fromJson(getCart(), CartModel.class);
            boolean check = false;
            for (int i = 0; i < cartModel.getList().size(); i++) {
                if (reponse.getId().equals(cartModel.getList().get(i))) {
                    check = true;
                }
            }
            if (check = true) {
                Toast.makeText(context, "The course is available in the cart", Toast.LENGTH_SHORT).show();
            } else {
                cartModel.getList().add(reponse);
                String cart = mGson.toJson(cartModel, CartModel.class);
                mEditTor.putString(Constain.cart, cart).commit();
                Log.d(TAG, "saveCart: " + cartModel.getList().size());
            }
        }
    }

    public String getCart() {
        return mSharedPreferences.getString(Constain.cart, "");
    }

    public void removeCart(){
        mEditTor.remove(Constain.cart).commit();
    }

    public String getUser() {
        return mSharedPreferences.getString(Constain.isUser, "");
    }

}
