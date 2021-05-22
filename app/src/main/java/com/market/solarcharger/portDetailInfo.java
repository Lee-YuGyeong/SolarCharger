package com.market.solarcharger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class portDetailInfo {

    @SerializedName("Port_num")
    @Expose
    private int Port_num;

    @SerializedName("Report")
    @Expose
    private int Report;

    @SerializedName("Broken")
    @Expose
    private int Broken;

    @SerializedName("StatusInfo")
    @Expose
    private int StatusInfo;

    @SerializedName("Product_Product_ID")
    @Expose
    private int Product_Product_ID;

    public int getPort_num() {
        return Port_num;
    }

    public void setPort_num(int port_num) {
        Port_num = port_num;
    }

    public int getReport() {
        return Report;
    }

    public void setReport(int report) {
        Report = report;
    }

    public int getBroken() {
        return Broken;
    }

    public void setBroken(int broken) {
        Broken = broken;
    }

    public int getStatusInfo() {
        return StatusInfo;
    }

    public void setStatusInfo(int statusInfo) {
        StatusInfo = statusInfo;
    }

    public int getProduct_Product_ID() {
        return Product_Product_ID;
    }

    public void setProduct_Product_ID(int product_Product_ID) {
        Product_Product_ID = product_Product_ID;
    }
}
