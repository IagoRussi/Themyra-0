package com.example.autenticacao.model;

public class Analise {

    private InfoWhois infoWhois;

    public Analise() {}

    public Analise(InfoWhois infoWhois) {
        this.infoWhois = infoWhois;
    }

    public InfoWhois getInfoWhois() {
        return infoWhois;
    }

    public void setInfoWhois(InfoWhois infoWhois) {
        this.infoWhois = infoWhois;
    }
}
