package com.example.mobileappbook.cores.services;

import com.example.mobileappbook.cores.reponse.register_reponse.RegisterReponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DataService {
    @FormUrlEncoded
    @POST("/register")
    Call<RegisterReponse>register(@Field("name") String name,
                                  @Field("email") String email,
                                  @Field("password") String password,
                                  @Field("phone") String phone,
                                  @Field("address") String address,
                                  @Field("description") String description,
                                  @Field("gender") String gender);
}
