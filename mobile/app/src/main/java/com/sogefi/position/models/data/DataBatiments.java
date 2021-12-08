package com.sogefi.position.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.List;

public class DataBatiments {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("nombreNiveaux")
    @Expose
    private Integer nombreNiveaux;
    @SerializedName("codeBatiment")
    @Expose
    private String codeBatiment;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("indication")
    @Expose
    private String indication;
    @SerializedName("rue")
    @Expose
    private String rue;
    @SerializedName("ville")
    @Expose
    private String ville;
    @SerializedName("commune")
    @Expose
    private String commune;
    @SerializedName("quartier")
    @Expose
    private String quartier;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("etablissements")
    @Expose
    private List<DataEtablissements> etablissements = null;

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

    public Integer getNombreNiveaux() {
        return nombreNiveaux;
    }

    public void setNombreNiveaux(Integer nombreNiveaux) {
        this.nombreNiveaux = nombreNiveaux;
    }

    public String getCodeBatiment() {
        return codeBatiment;
    }

    public void setCodeBatiment(String codeBatiment) {
        this.codeBatiment = codeBatiment;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
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
}
