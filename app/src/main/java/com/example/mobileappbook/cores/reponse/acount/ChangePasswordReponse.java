package com.example.mobileappbook.cores.reponse.acount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordReponse {
    @SerializedName("status")
    @Expose
    private String mStatus;
    @SerializedName("user")
    @Expose
    private UserReponse mUser;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public UserReponse getmUser() {
        return mUser;
    }

    public void setmUser(UserReponse mUser) {
        this.mUser = mUser;
    }
}
