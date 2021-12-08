package com.sogefi.position.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Commercial {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("role")
    @Expose
    private Integer role;
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
    @SerializedName("idZone")
    @Expose
    private Integer idZone;
    @SerializedName("actif")
    @Expose
    private Integer actif;
    @SerializedName("sexe")
    @Expose
    private String sexe;
    @SerializedName("whatsapp")
    @Expose
    private String whatsapp;
    @SerializedName("diplome")
    @Expose
    private String diplome;
    @SerializedName("tailleTshirt")
    @Expose
    private String tailleTshirt;
    @SerializedName("age")
    @Expose
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

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

    public Integer getIdZone() {
        return idZone;
    }

    public void setIdZone(Integer idZone) {
        this.idZone = idZone;
    }

    public Integer getActif() {
        return actif;
    }

    public void setActif(Integer actif) {
        this.actif = actif;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getTailleTshirt() {
        return tailleTshirt;
    }

    public void setTailleTshirt(String tailleTshirt) {
        this.tailleTshirt = tailleTshirt;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
