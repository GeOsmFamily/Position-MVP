package com.sogefi.position.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sogefi.position.models.Commercial;
import com.sogefi.position.models.SousCategory;

import java.util.List;

import kotlinx.android.parcel.Parcelize;

@Parcelize
public class DataEtablissements implements Parcelable {

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
    @SerializedName("autres")
    @Expose
    private String autres;
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
    @SerializedName("nomCommercial")
    @Expose
    private String nomCommercial;
    @SerializedName("batiment")
    @Expose
    private DataBatiments batiment;
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
    @SerializedName("commercial")
    @Expose
    private Commercial commercial;
    public final static Creator<DataEtablissements> CREATOR = new Creator<DataEtablissements>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DataEtablissements createFromParcel(android.os.Parcel in) {
            return new DataEtablissements(in);
        }

        public DataEtablissements[] newArray(int size) {
            return (new DataEtablissements[size]);
        }

    }
            ;


    public DataEtablissements() {
    }

    protected DataEtablissements(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.idBatiment = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.nom = ((String) in.readValue((String.class.getClassLoader())));
        this.indicationAdresse = ((String) in.readValue((Object.class.getClassLoader())));
        this.codePostal = ((String) in.readValue((Object.class.getClassLoader())));
        this.siteInternet = ((String) in.readValue((Object.class.getClassLoader())));
        this.idCommercial = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.idManager = ((Object) in.readValue((Object.class.getClassLoader())));
        this.etage = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.autres = ((String) in.readValue((String.class.getClassLoader())));
        this.cover = ((String) in.readValue((String.class.getClassLoader())));
        this.vues = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((Object) in.readValue((Object.class.getClassLoader())));
        this.nomCommercial = ((String) in.readValue((String.class.getClassLoader())));
        this.batiment = ((DataBatiments) in.readValue((DataBatiments.class.getClassLoader())));
        in.readList(this.sousCategories, (com.sogefi.position.models.SousCategory.class.getClassLoader()));
        in.readList(this.images, (com.sogefi.position.models.data. DataImages.class.getClassLoader()));
        in.readList(this.horaires, (com.sogefi.position.models.data.DataHoraires.class.getClassLoader()));
        in.readList(this.telephones, (com.sogefi.position.models.data.DataTelephones.class.getClassLoader()));
        this.commercial = ((Commercial) in.readValue((Commercial.class.getClassLoader())));
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(idBatiment);
        dest.writeValue(nom);
        dest.writeValue(indicationAdresse);
        dest.writeValue(codePostal);
        dest.writeValue(siteInternet);
        dest.writeValue(idCommercial);
        dest.writeValue(idManager);
        dest.writeValue(etage);
        dest.writeValue(autres);
        dest.writeValue(cover);
        dest.writeValue(vues);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(description);
        dest.writeValue(nomCommercial);
        dest.writeValue(batiment);
        dest.writeList(sousCategories);
        dest.writeList(images);
        dest.writeList(horaires);
        dest.writeList(telephones);
        dest.writeValue(commercial);
    }

    @Override
    public int describeContents() {
        return 0;
    }


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

    public String getAutres() {
        return autres;
    }

    public void setAutres(String autres) {
        this.autres = autres;
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

    public String getNomCommercial() {
        return nomCommercial;
    }

    public void setNomCommercial(String nomCommercial) {
        this.nomCommercial = nomCommercial;
    }

    public DataBatiments getBatiment() {
        return batiment;
    }

    public void setBatiment(DataBatiments batiment) {
        this.batiment = batiment;
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

    public Commercial getCommercial() {
        return commercial;
    }

    public void setCommercial(Commercial commercial) {
        this.commercial = commercial;
    }
}
