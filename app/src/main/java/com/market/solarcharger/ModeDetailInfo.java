package com.market.solarcharger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModeDetailInfo {

    @SerializedName("Mode")
    @Expose
    private int Mode;

    public int getMode() {
        return Mode;
    }

    public void setMode(int mode) {
        Mode = mode;
    }
}
