package com.market.solarcharger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GraphDetailInfo {


    @SerializedName("Powerpower")
    @Expose
    private float Powerpower;


    public float getPowerpower() {
        return Powerpower;
    }

    public void setPowerpower(float powerpower) {
        Powerpower = powerpower;
    }
}
