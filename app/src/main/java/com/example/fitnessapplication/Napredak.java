package com.example.fitnessapplication;

public class Napredak {
    String datum;
    String trenutnaTezina;
    String bmi;

    public Napredak() {
    }

    public Napredak(String datum, String trenutnaTezina, String bmi) {
        this.datum = datum;
        this.trenutnaTezina = trenutnaTezina;
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

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }
}
