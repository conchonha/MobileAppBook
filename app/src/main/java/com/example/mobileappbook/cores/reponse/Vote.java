package com.example.mobileappbook.cores.reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vote {
    @SerializedName("totalVote")
    @Expose
    private Integer totalVote;
    @SerializedName("EVGVote")
    @Expose
    private Integer eVGVote;

    public Integer getTotalVote() {
        return totalVote;
    }

    public void setTotalVote(Integer totalVote) {
        this.totalVote = totalVote;
    }

    public Integer getEVGVote() {
        return eVGVote;
    }

    public void setEVGVote(Integer eVGVote) {
        this.eVGVote = eVGVote;
    }

}