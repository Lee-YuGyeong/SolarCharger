package com.market.solarcharger;

public class CableItem {

    int Port_num;
    int Report;
    int Broken;
    int StatusInfo;

    public CableItem(int port_num, int report, int broken, int statusInfo) {
        Port_num = port_num;
        Report = report;
        Broken = broken;
        StatusInfo = statusInfo;
    }

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
}
