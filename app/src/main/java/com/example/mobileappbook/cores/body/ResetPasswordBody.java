package com.example.mobileappbook.cores.body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPasswordBody {
    @SerializedName("email")
    @Expose
    private String mEmail;

    @SerializedName("token")
    @Expose
    private String mActivityToken;

    @SerializedName("password")
    @Expose
    private String mNewPassword;

    public ResetPasswordBody(){

    }

    public ResetPasswordBody(String mEmail, String mActivityToken, String mNewPassword) {
        this.mEmail = mEmail;
        this.mActivityToken = mActivityToken;
        this.mNewPassword = mNewPassword;
    }
}
