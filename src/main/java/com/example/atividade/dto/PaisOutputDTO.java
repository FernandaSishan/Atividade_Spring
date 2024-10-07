package com.example.atividade.dto;

import com.example.atividade.model.Pais;

public class PaisOutputDTO {

    private Long id;
    private String nome;
    private String sigla;

    public PaisOutputDTO(Pais pais) {
        this.id = pais.getId();
        this.nome = pais.getNome();
        this.sigla = pais.getSigla();
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
}
