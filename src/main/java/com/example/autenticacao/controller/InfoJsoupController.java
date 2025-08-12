package com.example.autenticacao.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.autenticacao.service.InfoJsoupService;

@RestController
@RequestMapping("/info")
public class InfoJsoupController {

    private final InfoJsoupService service;

    public InfoJsoupController(InfoJsoupService service) {
        this.service = service;
    }

    @GetMapping("/analisar")
    public ResponseEntity<?> analisarUrl(@RequestParam String url) {
        try {
            Map<String, String> resultado = service.analisar(url);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao processar a URL: " + e.getMessage());
        }
    }
}
