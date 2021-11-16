package com.sogefi.position.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sogefi.position.models.data.DataUser;

public class UserModel {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private DataUser data;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("password")
    public String password;
    @SerializedName("phone")
    public String phone;

    public UserModel(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public DataUser getData() {
        return data;
    }

    public void setData(DataUser data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}




