package com.sogefi.position.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Position {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("origine")
    @Expose
    private List<Double> origine = null;
    @SerializedName("status")
    @Expose
    private boolean status = true;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Double> getOrigine() {
        return origine;
    }

    public void setOrigine(List<Double> origine) {
        this.origine = origine;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}