package com.sogefi.position.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sogefi.position.models.SousCategory;

public class DataSearchEtablissement {
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
    private Object indicationAdresse;
    @SerializedName("codePostal")
    @Expose
    private Object codePostal;
    @SerializedName("siteInternet")
    @Expose
    private Object siteInternet;
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
    @SerializedName("vues")
    @Expose
    private Integer vues;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("batiment")
    @Expose
    private Object batiment;
    @SerializedName("nomSousCategorie")
    @Expose
    private String nomSousCategorie;
    @SerializedName("nomCategorie")
    @Expose
    private String nomCategorie;
    @SerializedName("logo_url")
    @Expose
    private Object logoUrl;
    @SerializedName("sous_categorie")
    @Expose
    private SousCategory sousCategorie;

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

    public Object getIndicationAdresse() {
        return indicationAdresse;
    }

    public void setIndicationAdresse(Object indicationAdresse) {
        this.indicationAdresse = indicationAdresse;
    }

    public Object getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(Object codePostal) {
        this.codePostal = codePostal;
    }

    public Object getSiteInternet() {
        return siteInternet;
    }

    public void setSiteInternet(Object siteInternet) {
        this.siteInternet = siteInternet;
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

    public Integer getVues() {
        return vues;
    }

    public void setVues(Integer vues) {
        this.vues = vues;
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

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getBatiment() {
        return batiment;
    }

    public void setBatiment(Object batiment) {
        this.batiment = batiment;
    }

    public String getNomSousCategorie() {
        return nomSousCategorie;
    }

    public void setNomSousCategorie(String nomSousCategorie) {
        this.nomSousCategorie = nomSousCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public Object getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(Object logoUrl) {
        this.logoUrl = logoUrl;
    }

    public SousCategory getSousCategorie() {
        return sousCategorie;
    }

    public void setSousCategorie(SousCategory sousCategorie) {
        this.sousCategorie = sousCategorie;
    }
}
