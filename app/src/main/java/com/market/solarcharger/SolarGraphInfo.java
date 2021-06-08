package com.market.solarcharger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SolarGraphInfo {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("SolarGraph")
    @Expose
    private List<SolarGraphDetailInfo> solarGraphDetailInfoList;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<SolarGraphDetailInfo> getSolarGraphDetailInfoList() {
        return solarGraphDetailInfoList;
    }

    public void setSolarGraphDetailInfoList(List<SolarGraphDetailInfo> solarGraphDetailInfoList) {
        this.solarGraphDetailInfoList = solarGraphDetailInfoList;
    }
}
