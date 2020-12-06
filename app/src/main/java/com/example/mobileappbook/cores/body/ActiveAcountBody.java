package com.example.mobileappbook.cores.body;

import com.google.gson.annotations.SerializedName;

public class ActiveAcountBody {
    @SerializedName("email")
    private String mEmail;

    @SerializedName("activeToken")
    private String mToken;

    public ActiveAcountBody(String mEmail, String mToken) {
        this.mEmail = mEmail;
        this.mToken = mToken;
    }
}
