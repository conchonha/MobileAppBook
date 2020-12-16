package com.example.mobileappbook.cores.services;
import com.example.mobileappbook.cores.body.ActiveAcountBody;
import com.example.mobileappbook.cores.body.CreateCourseBody;
import com.example.mobileappbook.cores.body.LoginBody;
import com.example.mobileappbook.cores.body.PaymentBody;
import com.example.mobileappbook.cores.body.RegisterBody;
import com.example.mobileappbook.cores.body.UserInfoBody;
import com.example.mobileappbook.cores.reponse.acount.ChangePasswordReponse;
import com.example.mobileappbook.cores.reponse.course.CourseReponse;
import com.example.mobileappbook.cores.reponse.featured.GetAllCourseReponse;
import com.example.mobileappbook.cores.reponse.featured.GetAllCategoryReponse;
import com.example.mobileappbook.cores.body.ResetPasswordBody;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface DataService {
    //-----------------------ACCOUNT---------------------------------
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

    //-----------------------SETTING---------------------------------

    @PUT("/change-profile")
    Call<UserReponse>changeProfile(@Body UserInfoBody body, @Header("auth-token") String authToken);

    @GET("logout")
    Call<Map>userLogout(@Header("auth-token") String authToken);

    @FormUrlEncoded
    @PUT("/change-password")
    Call<ChangePasswordReponse>changePassword(@Field("oldpassword") String oldpassword,
                                              @Field("newpassword") String newpassword,
                                              @Header("auth-token") String authToken);

    @Multipart
    @PUT("change-avatar")
    Call<ChangePasswordReponse>changeAva(@Part MultipartBody.Part file,
                               @Header("auth-token") String authToken);
    //-----------------------FEATURED---------------------------------
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

    //-----------------------Course---------------------------------
    @Multipart
    @POST("/course/create")
    Call<CourseReponse>createCourse(@Part("name") RequestBody name,
                                    @Part("goal") RequestBody goal,
                                    @Part("description") RequestBody description,
                                    @Part("category") RequestBody category,
                                    @Part("price") RequestBody price,
                                    @Part("discount") RequestBody discount,
                                    @Header("auth-token") String authToken,
                                    @Part MultipartBody.Part file);
}
