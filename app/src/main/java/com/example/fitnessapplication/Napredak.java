package com.example.fitnessapplication;

public class Napredak {
    String datum;
    String trenutnaTezina;
    String visina;
    String bmi;

    public Napredak() {
    }

    public Napredak(String datum, String trenutnaTezina, String visina, String bmi) {
        this.datum = datum;
        this.trenutnaTezina = trenutnaTezina;
        this.visina = visina;
        this.bmi = bmi;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getTrenutnaTezina() {
        return trenutnaTezina;
    }

    public void setTrenutnaTezina(String trenutnaTezina) {
        this.trenutnaTezina = trenutnaTezina;
    }

    public String getVisina() {
        return visina;
    }

    public void setVisina(String visina) {
        this.visina = visina;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }
}
