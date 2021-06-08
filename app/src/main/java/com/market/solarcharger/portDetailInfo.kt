package com.market.solarcharger

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class portDetailInfo {
    @SerializedName("Port_num")
    @Expose
    var port_num = 0

    @SerializedName("Report")
    @Expose
    var report = 0

    @SerializedName("Broken")
    @Expose
    var broken = 0

    @SerializedName("StatusInfo")
    @Expose
    var statusInfo = 0

    @SerializedName("Product_Product_ID")
    @Expose
    var product_Product_ID = 0

}