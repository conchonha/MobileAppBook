package com.example.mobileappbook.cores.body;

import com.google.gson.annotations.SerializedName;

public class CreateCourseBody {
    @SerializedName("name")
    private String mCourseName;
    @SerializedName("goal")
    private String mObjectives;
    @SerializedName("description")
    private String mDesciption;
    @SerializedName("category")
    private String mField;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("discount")
    private String mDiscount;

    public CreateCourseBody(String mCourseName, String mObjectives, String mDesciption, String mField, String mPrice, String mDiscount) {
        this.mCourseName = mCourseName;
        this.mObjectives = mObjectives;
        this.mDesciption = mDesciption;
        this.mField = mField;
        this.mPrice = mPrice;
        this.mDiscount = mDiscount;
    }
}
