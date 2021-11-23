package com.sogefi.position.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sogefi.position.models.SousCategory;

import java.util.List;

public class DataCategories {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("logoUrl")
    @Expose
    private Object logoUrl;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("sous_categories")
    @Expose
    private List<SousCategory> sousCategories = null;

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

    public Object getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(Object logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<SousCategory> getSousCategories() {
        return sousCategories;
    }

    public void setSousCategories(List<SousCategory> sousCategories) {
        this.sousCategories = sousCategories;
    }

}
