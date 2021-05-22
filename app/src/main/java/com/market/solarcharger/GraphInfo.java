package com.market.solarcharger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GraphInfo {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("Weather")
    @Expose
    private List<GraphDetailInfo> graphDetailInfoList;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<GraphDetailInfo> getGraphDetailInfoList() {
        return graphDetailInfoList;
    }

    public void setGraphDetailInfoList(List<GraphDetailInfo> graphDetailInfoList) {
        this.graphDetailInfoList = graphDetailInfoList;
    }
}
