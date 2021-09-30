package com.sogefi.position.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Language {

    @SerializedName("name")
    @Expose
    private int name;

    @SerializedName("picture")
    @Expose
    private int picture;

    @SerializedName("locale")
    @Expose
    private String locale;


    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
