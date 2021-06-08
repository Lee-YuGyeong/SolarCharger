package com.market.solarcharger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SolarGraphDetailInfo {

    @SerializedName("solar")
    @Expose
    private float solar;

    public SolarGraphDetailInfo(float solar) {
        this.solar = solar;
    }

    public float getSolar() {
        return solar;
    }

    public void setSolar(float solar) {
        this.solar = solar;
    }
}
