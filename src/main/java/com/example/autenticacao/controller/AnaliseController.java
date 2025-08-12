package com.example.autenticacao.controller;

import com.example.autenticacao.model.Analise;
import com.example.autenticacao.model.InfoWhois;
import com.example.autenticacao.service.AnaliseService;
import com.example.autenticacao.service.InfoWhoisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/url")
public class AnaliseController {

    //private final AnaliseService analiseService;
    private final InfoWhoisService infoWhoisService;

    public AnaliseController(InfoWhoisService infoWhoisService) {
        this.infoWhoisService = infoWhoisService;
    }

    /*@GetMapping
    public Analise analise(@RequestParam String url) {
        return analiseService.analise(url);
    }*/

    @GetMapping("/whois")
    public InfoWhois getWhois(@RequestParam String dominio) {
        String rawWhois = infoWhoisService.whoisRaw(dominio);
        return infoWhoisService.parseWhoisRaw(rawWhois);
    }

}
