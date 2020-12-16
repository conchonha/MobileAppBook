package com.example.mobileappbook.model;

import com.example.mobileappbook.cores.reponse.featured.GetAllCourseReponse;

import java.util.List;

public class CartModel {
    private List<GetAllCourseReponse>list;

    public List<GetAllCourseReponse> getList() {
        return list;
    }

    public void setList(List<GetAllCourseReponse> list) {
        this.list = list;
    }
}
