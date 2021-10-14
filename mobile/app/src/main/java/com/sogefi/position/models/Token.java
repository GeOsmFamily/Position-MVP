package com.sogefi.position.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Token {

    @SerializedName("iss")
    @Expose
    private String iss;
    @SerializedName("iat")
    @Expose
    private Integer iat;
    @SerializedName("exp")
    @Expose
    private Integer exp;
    @SerializedName("nbf")
    @Expose
    private Integer nbf;
    @SerializedName("jti")
    @Expose
    private String jti;
    @SerializedName("sub")
    @Expose
    private Integer sub;
    @SerializedName("prv")
    @Expose
    private String prv;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("roles_id")
    @Expose
    private List<Integer> rolesId = null;

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public Integer getIat() {
        return iat;
    }

    public void setIat(Integer iat) {
        this.iat = iat;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getNbf() {
        return nbf;
    }

    public void setNbf(Integer nbf) {
        this.nbf = nbf;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public Integer getSub() {
        return sub;
    }

    public void setSub(Integer sub) {
        this.sub = sub;
    }

    public String getPrv() {
        return prv;
    }

    public void setPrv(String prv) {
        this.prv = prv;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getRolesId() {
        return rolesId;
    }

    public void setRolesId(List<Integer> rolesId) {
        this.rolesId = rolesId;
    }

}