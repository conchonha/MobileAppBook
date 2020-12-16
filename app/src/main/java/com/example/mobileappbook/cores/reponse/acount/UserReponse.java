package com.example.mobileappbook.cores.reponse.acount;

import com.example.mobileappbook.cores.reponse.error.ObjectError;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserReponse extends ObjectError {
    @SerializedName("active")
    @Expose
    private Integer active;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("activeToken")
    @Expose
    private String activeToken;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("Auth-token")
    @Expose
    private String mAuthToken;

    public String getmAuthToken() {
        return mAuthToken;
    }

    public void setmAuthToken(String mAuthToken) {
        this.mAuthToken = mAuthToken;
    }

    public String getActiveToken() {
        return activeToken;
    }

    public void setActiveToken(String activeToken) {
        this.activeToken = activeToken;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
