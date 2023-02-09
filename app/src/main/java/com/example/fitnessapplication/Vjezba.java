package com.example.fitnessapplication;

public class Vjezba {
    String idVjezba;
    String kalorije;
    String imeVjezbe;
    String opis;

    public Vjezba() {
    }

    public Vjezba(String idVjezba, String kalorije, String imeVjezbe, String opis) {
        this.idVjezba = idVjezba;
        this.kalorije = kalorije;
        this.imeVjezbe = imeVjezbe;
        this.opis = opis;
    }

    public String getIdVjezba() {
        return idVjezba;
    }

    public void setIdVjezba(String idVjezba) {
        this.idVjezba = idVjezba;
    }

    public String getKalorije() {
        return kalorije;
    }

    public void setKalorije(String kalorije) {
        this.kalorije = kalorije;
    }

    public String getImeVjezbe() {
        return imeVjezbe;
    }

    public void setImeVjezbe(String imeVjezbe) {
        this.imeVjezbe = imeVjezbe;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
