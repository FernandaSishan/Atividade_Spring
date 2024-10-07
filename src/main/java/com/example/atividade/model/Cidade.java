package com.example.atividade.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = true, unique = true)
    private String nome;
    @ManyToOne
    private Estado estado;
    @OneToMany(mappedBy = "cidade")
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();

    public Cidade(){

    }

    public Cidade(long id, String nome, Estado estado, List<Pessoa> pessoas) {

        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.pessoas = pessoas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
}
