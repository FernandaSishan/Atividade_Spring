package com.example.atividade.dto;

import com.example.atividade.model.Cidade;
import com.example.atividade.repository.EstadoRepository;

public class CidadeInputDTO {
    private String nome;
    private String estado;

    public CidadeInputDTO(){
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

    public Cidade build(EstadoRepository estadoRepository){
        Cidade cidade = new Cidade();
        cidade.setNome(this.nome);
        cidade.setEstado(estadoRepository.findByNome(this.estado));
        return cidade;
    }
}
