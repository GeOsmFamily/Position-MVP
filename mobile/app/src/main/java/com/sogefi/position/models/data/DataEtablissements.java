package com.sogefi.position.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sogefi.position.models.SousCategory;

import java.util.List;

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
    private Object indicationAdresse;
    @SerializedName("codePostal")
    @Expose
    private String codePostal;
    @SerializedName("siteInternet")
    @Expose
    private String siteInternet;
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
    private Object cover;
    @SerializedName("autres")
    @Expose
    private String autres;
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
    @SerializedName("nomCommercial")
    @Expose
    private String nomCommercial;
    @SerializedName("sous_categories")
    @Expose
    private List<SousCategory> sousCategories = null;
    @SerializedName("images")
    @Expose
    private List<DataImages> images = null;
    @SerializedName("horaires")
    @Expose
    private List<DataHoraires> horaires = null;
    @SerializedName("telephones")
    @Expose
    private List<DataTelephones> telephones = null;

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

    public Object getCover() {
        return cover;
    }

    public void setCover(Object cover) {
        this.cover = cover;
    }

    public String getAutres() {
        return autres;
    }

    public void setAutres(String autres) {
        this.autres = autres;
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

    public String getNomCommercial() {
        return nomCommercial;
    }

    public void setNomCommercial(String nomCommercial) {
        this.nomCommercial = nomCommercial;
    }

    public List<SousCategory> getSousCategories() {
        return sousCategories;
    }

    public void setSousCategories(List<SousCategory> sousCategories) {
        this.sousCategories = sousCategories;
    }

    public List<DataImages> getImages() {
        return images;
    }

    public void setImages(List<DataImages> images) {
        this.images = images;
    }

    public List<DataHoraires> getHoraires() {
        return horaires;
    }

    public void setHoraires(List<DataHoraires> horaires) {
        this.horaires = horaires;
    }

    public List<DataTelephones> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<DataTelephones> telephones) {
        this.telephones = telephones;
    }
}
