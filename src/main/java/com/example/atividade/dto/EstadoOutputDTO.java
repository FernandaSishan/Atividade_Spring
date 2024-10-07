package com.example.atividade.dto;

import com.example.atividade.model.Estado;

public class EstadoOutputDTO {

    private Long id;
    private String nome;
    private String sigla;
    private String pais;

    public EstadoOutputDTO(Estado estado) {
        this.id = estado.getId();
        this.nome = estado.getNome();
        this.sigla = estado.getSigla();
        this.pais = estado.getPais().getNome();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
