package com.example.autenticacao.service;

import org.apache.commons.net.whois.WhoisClient;

public class InfoWhoisService {

    String dominio = "google.com";

    public String getInfoWhois(String dominio) {
        try {
            WhoisClient whois = new WhoisClient();
            whois.connect(WhoisClient.DEFAULT_HOST);
            String resultado = whois.query(dominio);
            whois.disconnect();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Erro ao consultar WHOIS";
    }
}