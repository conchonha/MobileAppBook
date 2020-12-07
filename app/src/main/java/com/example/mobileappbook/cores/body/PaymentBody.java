package com.example.mobileappbook.cores.body;

import com.google.gson.annotations.SerializedName;

public class PaymentBody {
    @SerializedName("name")
    private String mName;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("stripeToken")
    private String mStripeToken;
    @SerializedName("amount")
    private String mAmount;
    @SerializedName("idCourse")
    private String mIdCourse;
    @SerializedName("idUser")
    private String mIdUser;

    public PaymentBody(String mName, String mEmail, String mStripeToken, String mAmount, String mIdCourse, String mIdUser) {
        this.mName = mName;
        this.mEmail = mEmail;
        this.mStripeToken = mStripeToken;
        this.mAmount = mAmount;
        this.mIdCourse = mIdCourse;
        this.mIdUser = mIdUser;
    }
}
