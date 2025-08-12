package com.example.autenticacao.service;

import com.example.autenticacao.model.InfoWhois;
import org.apache.commons.net.whois.WhoisClient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class InfoWhoisService {

    private String dominio;

    public String whoisRaw(String dominio) {
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

    public InfoWhois parseWhoisRaw (String whoisRaw) {
        InfoWhois parsedWhois = new InfoWhois();

        if (whoisRaw == null || whoisRaw.isEmpty()) {
            return parsedWhois;
        } else {
            String[] linhas = whoisRaw.split("\\r?\\n");

            for (String linha : linhas) {
                linha = linha.trim();

                //DOMAIN NAME
                if (linha.toLowerCase().startsWith("domain name:")) {
                    parsedWhois.setDominioPrivado(checkPrivado(linha));
                    parsedWhois.setNomeDominio(linha.replace("Domain Name:", "").trim());
                }
                //UPDATED DATE
                else if (linha.toLowerCase().startsWith("updated date:")) {
                    try {
                        String s = (linha.replace("Updated Date:", "").trim());
                        LocalDate date = LocalDate.parse(s.substring(0, 10), DateTimeFormatter.ISO_DATE);
                        parsedWhois.setDataAtualizacao(date);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //CREATION DATE
                else if (linha.toLowerCase().startsWith("creation date:")) {
                    try {
                        String s = (linha.replace("Creation Date:", "").trim());
                        LocalDate date = LocalDate.parse(s.substring(0, 10), DateTimeFormatter.ISO_DATE);
                        parsedWhois.setDataCriacao(date);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //EXPIRY DATE
                else if (linha.toLowerCase().startsWith("registry expiry date:")) {
                    try {
                        String s = (linha.replace("Registry Expiry Date:", "").trim());
                        LocalDate date = LocalDate.parse(s.substring(0, 10), DateTimeFormatter.ISO_DATE);
                        parsedWhois.setDataExpiracao(date);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //REGISTRAR NAME
                else if (linha.toLowerCase().startsWith("registrar:")) {
                    parsedWhois.setRegistrarPrivado(checkPrivado(linha));
                    parsedWhois.setNomeRegistrar(linha.replace("Registrar:", "").trim());
                }
            }

            return parsedWhois;
        }
    }

    public boolean checkPrivado(String s) {
        return s.toLowerCase(Locale.ROOT).contains("privacy") || s.toLowerCase(Locale.ROOT).contains("redacted") || s.toLowerCase(Locale.ROOT).contains("withheld") || s.toLowerCase(Locale.ROOT).contains("proxy") || s.toLowerCase(Locale.ROOT).contains("protected");
    }

}