package com.sogefi.position.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datum {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("logo_url")
    @Expose
    private Object logoUrl;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
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

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<SousCategory> getSousCategories() {
        return sousCategories;
    }

    public void setSousCategories(List<SousCategory> sousCategories) {
        this.sousCategories = sousCategories;
    }
}
