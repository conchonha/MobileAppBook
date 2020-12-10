package com.example.mobileappbook.cores.body;

import com.google.gson.annotations.SerializedName;

public class UserInfoBody {
    @SerializedName("name")
    private String mName;

    @SerializedName("phone")
    private String mPhone;

    @SerializedName("address")
    private String mAddress;

    @SerializedName("desscription")
    private String mDescription;

    public UserInfoBody(String mName, String mPhone, String mAddress, String mDescription) {
        this.mName = mName;
        this.mPhone = mPhone;
        this.mAddress = mAddress;
        this.mDescription = mDescription;
    }
}
