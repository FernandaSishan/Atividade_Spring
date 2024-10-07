package com.example.atividade.model;

import jakarta.persistence.*;

@Entity(name = "estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = true, unique = true)
    private String nome;
    @Column(nullable = true, unique = true)
    private String sigla;
    @ManyToOne
    private Pais pais;

    public Estado(){
    }

    public Estado(long id, String nome, String sigla, Pais pais) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.pais = pais;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
