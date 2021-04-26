package com.market.solarcharger;

public class CableItem {

    int phone;
    int circle;
    int number;

    public CableItem(int phone, int circle, int number) {
        this.phone = phone;
        this.circle = circle;
        this.number = number;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getCircle() {
        return circle;
    }

    public void setCircle(int circle) {
        this.circle = circle;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
