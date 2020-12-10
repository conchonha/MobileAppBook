package com.example.mobileappbook.cores.body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfoBody {
    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("phone")
    @Expose
    private String mPhone;

    @SerializedName("address")
    @Expose
    private String mAddress;

    @SerializedName("description")
    @Expose
    private String mDescription;

    @SerializedName("gender")
    @Expose
    private String mGenger;

    public UserInfoBody(String mName, String mPhone, String mAddress, String mDescription,String mGenger){
        this.mName = mName;
        this.mPhone = mPhone;
        this.mAddress = mAddress;
        this.mDescription = mDescription;
        this.mGenger = mGenger;
    }
}
