package sample;

import javafx.scene.control.ChoiceBox;

public class BeregnPris {

    private String proffesion;
    private boolean selvstændig;
    private boolean validCpr;

    public boolean isValidCpr() {
        return validCpr;
    }

    public boolean isSelvstændig() {
        return selvstændig;
    }


    public void setProffesion(String proffesion) {
        this.proffesion = proffesion;
    }

    public void setSelvstændig(boolean selvstændig) {
        this.selvstændig = selvstændig;
    }

    public void setCprNr(boolean validCpr) {
        this.validCpr = validCpr;
    }

    public BeregnPris() {

    }

    public String pris(ChoiceBox input) {
        double selvstændigTillæg = 1.07;
        double startPris = 4000.0;
        if (input.getValue().equals("Advokat")) {
            if (isSelvstændig() == true) {
                return "Din ulykkesforsikrings pris er: " + startPris * selvstændigTillæg * 1.15;
            } else {
                return "Din ulykkesforsikrings pris er: " + startPris * 1.15;
            }
        }

        if (input.getValue().equals("Tømrer")) {
            if (isSelvstændig() == true) {
                return "Din ulykkesforsikrings pris er: " + startPris * selvstændigTillæg * 1.2;
            } else {
                return "Din ulykkesforsikrings pris er: " + startPris * 1.2;
            }
        }
        if (input.getValue().equals("Læge")) {
            if (isSelvstændig() == true) {
                return "Din ulykkesforsikrings pris er: " + startPris * selvstændigTillæg * 1.1;
            } else {
                return "Din ulykkesforsikrings pris er: " + startPris * 1.1;
            }
        }
        if (input.getValue().equals("Skolelærer")) {
            if (isSelvstændig() == true) {
                return "Din ulykkesforsikrings pris er: " + startPris * selvstændigTillæg * 1.05;
            } else {
                return "Din ulykkesforsikrings pris er: " + startPris * 1.05;
            }
        }
        return "";
    }
}

