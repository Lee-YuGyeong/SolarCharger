package com.market.solarcharger

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GraphInfo {
    @SerializedName("error")
    @Expose
    var error: Boolean? = null

    @SerializedName("Weather")
    @Expose
    var graphDetailInfoList: List<GraphDetailInfo>? = null

}