package com.example.mobileappbook.model;

import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCourseReponse;

public interface CallbackFeatured {
    public void onClickItem(GetAllCourseReponse reponse);
}
