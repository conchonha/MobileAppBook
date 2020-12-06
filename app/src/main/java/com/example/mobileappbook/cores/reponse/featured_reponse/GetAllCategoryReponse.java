package com.example.mobileappbook.cores.reponse.featured_reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllCategoryReponse {
@SerializedName("_id")
@Expose
private String id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("image")
@Expose
private String image;
@SerializedName("__v")
@Expose
private Integer v;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

public Integer getV() {
return v;
}

public void setV(Integer v) {
this.v = v;
}

}