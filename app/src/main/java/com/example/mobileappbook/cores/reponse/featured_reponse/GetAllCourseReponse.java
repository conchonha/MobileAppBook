package com.example.mobileappbook.cores.reponse.featured_reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetAllCourseReponse implements Serializable {
    @SerializedName("vote")
    @Expose
    private Vote vote;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("ranking")
    @Expose
    private Integer ranking;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("is_checked")
    @Expose
    private Integer isChecked;
    @SerializedName("is_required")
    @Expose
    private Boolean isRequired;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("idUser")
    @Expose
    private IdUser idUser;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("goal")
    @Expose
    private String goal;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("category")
    @Expose
    private Category Category;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
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

    public IdUser getIdUser() {
        return idUser;
    }

    public void setIdUser(IdUser idUser) {
        this.idUser = idUser;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    public GetAllCourseReponse.Category getCategory() {
        return Category;
    }

    public void setCategory(GetAllCourseReponse.Category category) {
        Category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public class Category {
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;

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

    }

    public class IdUser {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;

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

    }

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

}