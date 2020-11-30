package com.example.mobileappbook.cores.body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterBody {
    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("email")
    @Expose
    private String mEmail;

    @SerializedName("password")
    @Expose
    private String mPassWord;

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

//    public RegisterBody(){
//
//    }

    public RegisterBody(String mName, String mEmail, String mPassWord, String mPhone, String mAddress, String mDescription, String mGenger) {
        this.mName = mName;
        this.mEmail = mEmail;
        this.mPassWord = mPassWord;
        this.mPhone = mPhone;
        this.mAddress = mAddress;
        this.mDescription = mDescription;
        this.mGenger = mGenger;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassWord() {
        return mPassWord;
    }

    public void setmPassWord(String mPassWord) {
        this.mPassWord = mPassWord;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmGenger() {
        return mGenger;
    }

    public void setmGenger(String mGenger) {
        this.mGenger = mGenger;
    }
}
