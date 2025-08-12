package com.example.autenticacao.model;

import java.time.LocalDate;

public class InfoWhois {

    private String nomeDominio;
    private LocalDate dataAtualizacao;
    private LocalDate dataCriacao;
    private LocalDate dataExpiracao;
    private String nomeRegistrar;
    private Boolean dominioPrivado;
    private Boolean registrarPrivado;

    public String getNomeDominio() {
        return nomeDominio;
    }

    public void setNomeDominio(String nomeDominio) {
        this.nomeDominio = nomeDominio;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDate dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(LocalDate dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public String getNomeRegistrar() {
        return nomeRegistrar;
    }

    public void setNomeRegistrar(String nomeRegistrar) {
        this.nomeRegistrar = nomeRegistrar;
    }

    public Boolean isPrivado() {
        return dominioPrivado;
    }

    public void setDominioPrivado(Boolean dominioPrivado) {
        this.dominioPrivado = dominioPrivado;
    }

    public Boolean isRegistrarPrivado() {
        return registrarPrivado;
    }

    public void setRegistrarPrivado(Boolean registrarPrivado) {
        this.registrarPrivado = registrarPrivado;
    }
}
