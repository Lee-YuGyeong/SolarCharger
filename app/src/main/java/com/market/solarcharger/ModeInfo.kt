package com.market.solarcharger

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ModeInfo {
    @SerializedName("error")
    @Expose
    var error: Boolean? = null

    @SerializedName("mode")
    @Expose
    var modeDetailInfoList: List<ModeDetailInfo>? = null

}