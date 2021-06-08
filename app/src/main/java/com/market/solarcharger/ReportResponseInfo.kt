package com.market.solarcharger

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReportResponseInfo {
    @SerializedName("success")
    @Expose
    var success: Boolean? = null

}