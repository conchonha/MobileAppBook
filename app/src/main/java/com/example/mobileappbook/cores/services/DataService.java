package com.example.mobileappbook.cores.services;
import android.database.Observable;

import com.example.mobileappbook.cores.body.LoginBody;
import com.example.mobileappbook.cores.body.RegisterBody;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCourseReponse;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCategoryReponse;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @POST("/register")
    Call<UserReponse>register(@Body()RegisterBody registerBody);

    @POST("/login")
    Call<UserReponse> sendLogin(@Body LoginBody body);

    @FormUrlEncoded
    @POST("/forgot-password")
    Call<Map>forgotPassword(@Field("email") String email);

    @GET("/category/get-all-category")
    Call<List<GetAllCategoryReponse>>getallcategory();

    @GET("/course/get-all")
    Call<List<GetAllCourseReponse>> getallcourse();

    @GET("/course/get-free")
    Call<List<GetAllCourseReponse>> getfreecourse();

    @GET("/course/get-top")
    Call<List<GetAllCourseReponse>> gettopcourse();


}
