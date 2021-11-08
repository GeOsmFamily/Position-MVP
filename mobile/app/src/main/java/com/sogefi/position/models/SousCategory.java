package com.sogefi.position.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class SousCategories {

    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("data")
    @Expose
    private List<DatumSousCategories> data = null;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public List<DatumSousCategories> getData() {
        return data;
    }

    public void setData(List<DatumSousCategories> data) {
        this.data = data;
    }

}
