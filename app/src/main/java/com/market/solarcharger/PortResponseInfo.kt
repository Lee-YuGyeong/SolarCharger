package com.market.solarcharger

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PortResponseInfo {
    @SerializedName("error")
    @Expose
    var error: Boolean? = null

    @SerializedName("port")
    @Expose
    var portDetailInfoList: List<portDetailInfo>? = null

}