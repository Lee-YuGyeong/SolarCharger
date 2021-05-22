package com.market.solarcharger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GraphDetailInfo {

    @SerializedName("Dayday")
    @Expose
    private int Dayday;

    @SerializedName("Powerpower")
    @Expose
    private float Powerpower;

    public int getDayday() {
        return Dayday;
    }

    public void setDayday(int dayday) {
        Dayday = dayday;
    }

    public float getPowerpower() {
        return Powerpower;
    }

    public void setPowerpower(float powerpower) {
        Powerpower = powerpower;
    }
}
