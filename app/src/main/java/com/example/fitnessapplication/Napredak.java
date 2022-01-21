package com.example.fitnessapplication;

public class Napredak {
    String trenutnaTezina;
    String bmi;

    public Napredak() {
    }

    public Napredak(String trenutnaTezina, String bmi) {
        this.trenutnaTezina = trenutnaTezina;
        this.bmi = bmi;
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
