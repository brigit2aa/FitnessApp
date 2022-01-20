package com.example.fitnessapplication;

public class Korisnik {
    String imePrezime;
    String visina;
    String tezina;
    String trenutnaTezina;
    String bmi;
    String email;
    String password;

    public Korisnik() {
    }

    public Korisnik(String imePrezime, String visina, String tezina, String trenutnaTezina, String bmi, String email, String password) {
        this.imePrezime = imePrezime;
        this.visina = visina;
        this.tezina = tezina;
        this.trenutnaTezina = trenutnaTezina;
        this.bmi = bmi;
        this.email = email;
        this.password = password;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getVisina() {
        return visina;
    }

    public void setVisina(String visina) {
        this.visina = visina;
    }

    public String getTezina() {
        return tezina;
    }

    public void setTezina(String tezina) {
        this.tezina = tezina;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
