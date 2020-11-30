package com.example.mobileappbook.cores.services;

public class APIServices {
    private static String baseurl="http://192.168.43.248/cuahangquanlyoto/public/";

    public static DataService getService(){
        return APIRetrofitClient.getClient(baseurl).create(DataService.class);
    }
}
