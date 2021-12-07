package com.sogefi.position.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Commercial {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("idUser")
    @Expose
    private Integer idUser;
    @SerializedName("numeroCni")
    @Expose
    private Integer numeroCni;
    @SerializedName("numeroBadge")
    @Expose
    private Integer numeroBadge;
    @SerializedName("ville")
    @Expose
    private String ville;
    @SerializedName("quartier")
    @Expose
    private String quartier;
    @SerializedName("imageProfil")
    @Expose
    private String imageProfil;
    @SerializedName("zone")
    @Expose
    private String zone;
    @SerializedName("actif")
    @Expose
    private Integer actif;
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

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getNumeroCni() {
        return numeroCni;
    }

    public void setNumeroCni(Integer numeroCni) {
        this.numeroCni = numeroCni;
    }

    public Integer getNumeroBadge() {
        return numeroBadge;
    }

    public void setNumeroBadge(Integer numeroBadge) {
        this.numeroBadge = numeroBadge;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getImageProfil() {
        return imageProfil;
    }

    public void setImageProfil(String imageProfil) {
        this.imageProfil = imageProfil;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Integer getActif() {
        return actif;
    }

    public void setActif(Integer actif) {
        this.actif = actif;
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
