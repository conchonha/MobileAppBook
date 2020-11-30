package com.example.mobileappbook.cores.reponse.error_reponse;

public class ErrorRepone {
    private int mCode;
    private String mMessage;

    public ErrorRepone(int mCode, String mMessage) {
        this.mCode = mCode;
        this.mMessage = mMessage;
    }

    public int getmCode() {
        return mCode;
    }

    public void setmCode(int mCode) {
        this.mCode = mCode;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }
}
