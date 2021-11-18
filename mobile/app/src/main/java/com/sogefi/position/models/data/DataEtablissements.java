package com.sogefi.position.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataEtablissements {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("idBatiment")
    @Expose
    private Integer idBatiment;
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("indicationAdresse")
    @Expose
    private String indicationAdresse;
    @SerializedName("codePostal")
    @Expose
    private String codePostal;
    @SerializedName("siteInternet")
    @Expose
    private String siteInternet;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("idSousCategorie")
    @Expose
    private Integer idSousCategorie;
    @SerializedName("idCommercial")
    @Expose
    private Integer idCommercial;
    @SerializedName("idManager")
    @Expose
    private Object idManager;
    @SerializedName("etage")
    @Expose
    private Integer etage;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdBatiment() {
        return idBatiment;
    }

    public void setIdBatiment(Integer idBatiment) {
        this.idBatiment = idBatiment;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIndicationAdresse() {
        return indicationAdresse;
    }

    public void setIndicationAdresse(String indicationAdresse) {
        this.indicationAdresse = indicationAdresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getSiteInternet() {
        return siteInternet;
    }

    public void setSiteInternet(String siteInternet) {
        this.siteInternet = siteInternet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdSousCategorie() {
        return idSousCategorie;
    }

    public void setIdSousCategorie(Integer idSousCategorie) {
        this.idSousCategorie = idSousCategorie;
    }

    public Integer getIdCommercial() {
        return idCommercial;
    }

    public void setIdCommercial(Integer idCommercial) {
        this.idCommercial = idCommercial;
    }

    public Object getIdManager() {
        return idManager;
    }

    public void setIdManager(Object idManager) {
        this.idManager = idManager;
    }

    public Integer getEtage() {
        return etage;
    }

    public void setEtage(Integer etage) {
        this.etage = etage;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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
}
