package com.example.mobileappbook.cores.services;

import com.example.mobileappbook.utils.Constain;

public class APIServices {
    private static String baseurl= Constain.baseUrl;

    public static DataService getService(){
        return APIRetrofitClient.getClient(baseurl).create(DataService.class);
    }
}
