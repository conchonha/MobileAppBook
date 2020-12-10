package com.example.mobileappbook.cores.reponse.error_reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObjectError {
    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
