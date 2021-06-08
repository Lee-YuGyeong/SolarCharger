package com.market.solarcharger

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SolarGraphInfo {
    @SerializedName("error")
    @Expose
    var error: Boolean? = null

    @SerializedName("SolarGraph")
    @Expose
    var solarGraphDetailInfoList: List<SolarGraphDetailInfo>? = null

}