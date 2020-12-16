package com.example.mobileappbook.cores.body;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CreateCourseBody implements Serializable {
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

    public String getmCourseName() {
        return mCourseName;
    }

    public void setmCourseName(String mCourseName) {
        this.mCourseName = mCourseName;
    }

    public String getmObjectives() {
        return mObjectives;
    }

    public void setmObjectives(String mObjectives) {
        this.mObjectives = mObjectives;
    }

    public String getmDesciption() {
        return mDesciption;
    }

    public void setmDesciption(String mDesciption) {
        this.mDesciption = mDesciption;
    }

    public String getmField() {
        return mField;
    }

    public void setmField(String mField) {
        this.mField = mField;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getmDiscount() {
        return mDiscount;
    }

    public void setmDiscount(String mDiscount) {
        this.mDiscount = mDiscount;
    }
}
