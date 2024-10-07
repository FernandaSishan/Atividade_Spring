package com.example.atividade.dto;

import com.example.atividade.model.Cidade;

public class CidadeOutputDTO {

    private Long id;
    private String nome;
    private String estado;

    public CidadeOutputDTO(Cidade cidade){
        this.id = cidade.getId();
        this.nome = cidade.getNome();
        this.estado = cidade.getEstado().getNome();
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
