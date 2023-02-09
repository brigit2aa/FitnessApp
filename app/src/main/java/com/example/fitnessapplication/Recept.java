package com.example.fitnessapplication;

public class Recept {
    String brojKalorija;
    String dorucak;
    String prvaUzina;
    String rucak;
    String drugaUzina;
    String vecera;

    public Recept() {
    }

    public Recept(String brojKalorija, String dorucak, String prvaUzina, String rucak, String drugaUzina, String vecera) {
        this.brojKalorija = brojKalorija;
        this.dorucak = dorucak;
        this.prvaUzina = prvaUzina;
        this.rucak = rucak;
        this.drugaUzina = drugaUzina;
        this.vecera = vecera;
    }

    public String getBrojKalorija() {
        return brojKalorija;
    }

    public void setBrojKalorija(String brojKalorija) {
        this.brojKalorija = brojKalorija;
    }

    public String getDorucak() {
        return dorucak;
    }

    public void setDorucak(String dorucak) {
        this.dorucak = dorucak;
    }

    public String getPrvaUzina() {
        return prvaUzina;
    }

    public void setPrvaUzina(String prvaUzina) {
        this.prvaUzina = prvaUzina;
    }

    public String getRucak() {
        return rucak;
    }

    public void setRucak(String rucak) {
        this.rucak = rucak;
    }

    public String getDrugaUzina() {
        return drugaUzina;
    }

    public void setDrugaUzina(String drugaUzina) {
        this.drugaUzina = drugaUzina;
    }

    public String getVecera() {
        return vecera;
    }

    public void setVecera(String vecera) {
        this.vecera = vecera;
    }
}
