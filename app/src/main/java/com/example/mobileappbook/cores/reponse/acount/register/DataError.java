package com.example.mobileappbook.cores.reponse.acount.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataError {
    @SerializedName("errors")
    @Expose
    private List<Error>listError;

    public List<Error> getListError() {
        return listError;
    }

    public void setListError(List<Error> listError) {
        this.listError = listError;
    }
}
