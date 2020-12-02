package com.example.mobileappbook.cores.services;

import androidx.lifecycle.LiveData;

import com.example.mobileappbook.cores.body.LoginBody;
import com.example.mobileappbook.cores.body.RegisterBody;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DataService {
//    @FormUrlEncoded
//    @POST("/register")
//    Call<UserReponse>register(@Field("name") String name,
//                              @Field("email") String email,
//                              @Field("password") String password,
//                              @Field("phone") String phone,
//                              @Field("address") String address,
//                              @Field("description") String description,
//                              @Field("gender") String gender);

    @FormUrlEncoded
    @POST("/register")
    Call<LiveData<Map>>register(@Body()RegisterBody registerBody);

    @POST("/login")
    Call<UserReponse> sendLogin(@Body LoginBody body);

    @FormUrlEncoded
    @POST("/forgot-password")
    Call<Map>forgotPassword(@Field("email") String email);
}
