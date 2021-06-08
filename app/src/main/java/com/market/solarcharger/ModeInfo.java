package com.market.solarcharger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModeInfo {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("mode")
    @Expose
    private List<ModeDetailInfo> modeDetailInfoList;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<ModeDetailInfo> getModeDetailInfoList() {
        return modeDetailInfoList;
    }

    public void setModeDetailInfoList(List<ModeDetailInfo> modeDetailInfoList) {
        this.modeDetailInfoList = modeDetailInfoList;
    }
}
