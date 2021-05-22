package com.market.solarcharger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PortResponseInfo {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("port")
    @Expose
    private List<portDetailInfo> portDetailInfoList;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<portDetailInfo> getPortDetailInfoList() {
        return portDetailInfoList;
    }

    public void setPortDetailInfoList(List<portDetailInfo> portDetailInfoList) {
        this.portDetailInfoList = portDetailInfoList;
    }

}
