package com.market.solarcharger;

public class CableItem {

    int charger;
    int number;


    public CableItem(int charger, int number) {
        this.charger = charger;
        this.number = number;
    }

    public int getCharger() {
        return charger;
    }

    public void setCharger(int charger) {
        this.charger = charger;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
