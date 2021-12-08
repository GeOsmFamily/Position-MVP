package com.sogefi.position.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataSearchSousCategories {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("idCategorie")
    @Expose
    private Integer idCategorie;
    @SerializedName("logoUrl")
    @Expose
    private Object logoUrl;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("etablissements")
    @Expose
    private List<DataEtablissements> etablissements = null;
    @SerializedName("categorie")
    @Expose
    private DataCategories categorie;

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

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
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

    public List<DataEtablissements> getEtablissements() {
        return etablissements;
    }

    public void setEtablissements(List<DataEtablissements> etablissements) {
        this.etablissements = etablissements;
    }

    public DataCategories getCategorie() {
        return categorie;
    }

    public void setCategorie(DataCategories categorie) {
        this.categorie = categorie;
    }
}
