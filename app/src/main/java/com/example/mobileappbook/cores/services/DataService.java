package com.example.mobileappbook.cores.services;
import com.example.mobileappbook.cores.body.LoginBody;
import com.example.mobileappbook.cores.body.RegisterBody;
import com.example.mobileappbook.cores.body.ResetPasswordBody;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DataService {
    @POST("/register")
    Call<UserReponse>register(@Body()RegisterBody registerBody);

    @POST("/login")
    Call<UserReponse> sendLogin(@Body LoginBody body);

    @FormUrlEncoded
    @POST("/forgot-password")
    Call<Map>forgotPassword(@Field("email") String email);

    @POST("/reset-password")
    Call<UserReponse> resetPassword(@Body ResetPasswordBody body);
}
