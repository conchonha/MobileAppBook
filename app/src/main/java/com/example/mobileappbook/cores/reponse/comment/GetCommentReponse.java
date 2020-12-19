package com.example.mobileappbook.cores.reponse.comment;

import com.example.mobileappbook.cores.reponse.error.ObjectError;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCommentReponse {
    @SerializedName("create_at")
    @Expose
    private String createAt;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("idUser")
    @Expose
    private IdUser idUser;
    @SerializedName("numStar")
    @Expose
    private Integer numStar;
    @SerializedName("idCourse")
    @Expose
    private String idCourse;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IdUser getIdUser() {
        return idUser;
    }

    public void setIdUser(IdUser idUser) {
        this.idUser = idUser;
    }

    public Integer getNumStar() {
        return numStar;
    }

    public void setNumStar(Integer numStar) {
        this.numStar = numStar;
    }

    public String getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(String idCourse) {
        this.idCourse = idCourse;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public class IdUser {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("image")
        @Expose
        private String image;

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

    }
}
