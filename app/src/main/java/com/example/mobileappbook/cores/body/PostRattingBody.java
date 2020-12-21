package com.example.mobileappbook.cores.body;

import com.google.gson.annotations.SerializedName;

public class PostRattingBody {
    @SerializedName("idUser")
    private String mIdUser;
    @SerializedName("idCourse")
    private String mIdCourse;
    @SerializedName("content")
    private String mContent;
    @SerializedName("numStar")
    private String mNumStart;

    public PostRattingBody(String mIdUser, String mIdCourse, String mContent, String mNumStart) {
        this.mIdUser = mIdUser;
        this.mIdCourse = mIdCourse;
        this.mContent = mContent;
        this.mNumStart = mNumStart;
    }
}
