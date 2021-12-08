package com.sogefi.position.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sogefi.position.models.Commercial;
import com.sogefi.position.models.Horaires;
import com.sogefi.position.models.Images;
import com.sogefi.position.models.SousCategory;
import com.sogefi.position.models.Telephones;

import java.util.List;

import kotlinx.android.parcel.Parcelize;

@Parcelize
public class DataSearchEtablissement implements Parcelable {
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
    @SerializedName("nomCategorie")
    @Expose
    private String nomCategorie;
    @SerializedName("batiment")
    @Expose
    private DataBatiments batiment;
    @SerializedName("nomSousCategorie")
    @Expose
    private String nomSousCategorie;
    @SerializedName("images")
    @Expose
    private List<DataImages> images = null;
    @SerializedName("horaires")
    @Expose
    private List<DataHoraires> horaires = null;
    @SerializedName("telephones")
    @Expose
    private List<DataTelephones> telephones = null;
    @SerializedName("logo_url")
    @Expose
    private String logoUrl;
    @SerializedName("nomCommercial")
    @Expose
    private String nomCommercial;
    @SerializedName("sous_categories")
    @Expose
    private List<SousCategory> sousCategories = null;
    @SerializedName("commercial")
    @Expose
    private Commercial commercial;

    public DataSearchEtablissement() {

    }

    protected DataSearchEtablissement(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            idBatiment = null;
        } else {
            idBatiment = in.readInt();
        }
        nom = in.readString();
        indicationAdresse = in.readString();
        codePostal = in.readString();
        siteInternet = in.readString();
        if (in.readByte() == 0) {
            idCommercial = null;
        } else {
            idCommercial = in.readInt();
        }
        if (in.readByte() == 0) {
            etage = null;
        } else {
            etage = in.readInt();
        }
        cover = in.readString();
        autres = in.readString();
        if (in.readByte() == 0) {
            vues = null;
        } else {
            vues = in.readInt();
        }
        createdAt = in.readString();
        updatedAt = in.readString();
        nomCategorie = in.readString();
        nomSousCategorie = in.readString();
        logoUrl = in.readString();
        nomCommercial = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        if (idBatiment == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idBatiment);
        }
        dest.writeString(nom);
        dest.writeString(indicationAdresse);
        dest.writeString(codePostal);
        dest.writeString(siteInternet);
        if (idCommercial == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idCommercial);
        }
        if (etage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(etage);
        }
        dest.writeString(cover);
        dest.writeString(autres);
        if (vues == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(vues);
        }
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(nomCategorie);
        dest.writeString(nomSousCategorie);
        dest.writeString(logoUrl);
        dest.writeString(nomCommercial);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataSearchEtablissement> CREATOR = new Creator<DataSearchEtablissement>() {
        @Override
        public DataSearchEtablissement createFromParcel(Parcel in) {
            return new DataSearchEtablissement(in);
        }

        @Override
        public DataSearchEtablissement[] newArray(int size) {
            return new DataSearchEtablissement[size];
        }
    };

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

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public DataBatiments getBatiment() {
        return batiment;
    }

    public void setBatiment(DataBatiments batiment) {
        this.batiment = batiment;
    }

    public String getNomSousCategorie() {
        return nomSousCategorie;
    }

    public void setNomSousCategorie(String nomSousCategorie) {
        this.nomSousCategorie = nomSousCategorie;
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
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

    public Commercial getCommercial() {
        return commercial;
    }

    public void setCommercial(Commercial commercial) {
        this.commercial = commercial;
    }
}
