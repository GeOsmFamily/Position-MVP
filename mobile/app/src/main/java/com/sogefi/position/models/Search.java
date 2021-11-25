package com.sogefi.position.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Search {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("nom")
    @Expose
    private String nom;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("details")
    @Expose
    private String details;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
