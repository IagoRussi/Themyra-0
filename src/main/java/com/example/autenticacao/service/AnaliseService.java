package com.example.autenticacao.service;

import com.example.autenticacao.model.Analise;
import com.example.autenticacao.model.InfoJsoup;
import com.example.autenticacao.model.InfoWhois;
import org.springframework.stereotype.Service;

@Service
public class AnaliseService {

    private final InfoWhoisService whoisService;

    public AnaliseService(InfoWhoisService whoisService) {
        this.whoisService = whoisService;
    }

    public Analise analise(String url) {
        String rawWhois = whoisService.whoisRaw(url);
        InfoWhois whois = whoisService.parseWhoisRaw(rawWhois);

        return new Analise(whois);
    }

}
