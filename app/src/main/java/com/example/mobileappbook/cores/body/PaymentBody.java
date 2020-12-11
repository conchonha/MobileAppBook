package com.example.mobileappbook.cores.body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentBody {
    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("email")
    @Expose
    private String mEmail;

    @SerializedName("stripeToken")
    @Expose
    private String mStripeToken;

    @SerializedName("amount")
    @Expose
    private String mAmount;

    @SerializedName("idCourse")
    @Expose
    private String mIdCourse;

    @SerializedName("idUser")
    @Expose
    private String mIdUser;

    public PaymentBody(){

    }
    public PaymentBody(String mName, String mEmail, String mStripeToken, String mAmount, String mIdCourse, String mIdUser) {
        this.mName = mName;
        this.mEmail = mEmail;
        this.mStripeToken = mStripeToken;
        this.mAmount = mAmount;
        this.mIdCourse = mIdCourse;
        this.mIdUser = mIdUser;
    }
}
