package com.example.atividade.dto;

import com.example.atividade.model.Pessoa;
import com.example.atividade.repository.CidadeRepository;

public class PessoaInputDTO {

    private String nome;
    private String email;
    private String telefone;
    private String cidade;

    public PessoaInputDTO(){
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Pessoa build(CidadeRepository cidadeRepository){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(this.nome);
        pessoa.setEmail(this.email);
        pessoa.setTelefone(this.telefone);
        pessoa.setCidade(cidadeRepository.findByNome(this.cidade));
        return pessoa;
    }
}
