package com.example.mobileappbook.cores.services;
import com.example.mobileappbook.cores.body.ActiveAcountBody;
import com.example.mobileappbook.cores.body.LoginBody;
import com.example.mobileappbook.cores.body.PaymentBody;
import com.example.mobileappbook.cores.body.RegisterBody;
import com.example.mobileappbook.cores.body.UserInfoBody;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCourseReponse;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCategoryReponse;
import com.example.mobileappbook.cores.body.ResetPasswordBody;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

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

    @POST("/active-account")
    Call<UserReponse> activeAcount(@Body ActiveAcountBody body);

    @PUT("/change-profile")
    Call<UserReponse>changeProfile(@Body UserInfoBody body, @Header("auth-token") String authToken);
    //--------------------------------------------------------
    @GET("/category/get-all-category")
    Call<List<GetAllCategoryReponse>>getallcategory();

    @GET("/course/get-all")
    Call<List<GetAllCourseReponse>> getallcourse();

    @GET("/course/get-free")
    Call<List<GetAllCourseReponse>> getfreecourse();

    @GET("/course/get-top")
    Call<List<GetAllCourseReponse>> gettopcourse();

    @POST("/payment/pay")
    Call<Map>pay(@Body PaymentBody body);

}
