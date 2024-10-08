package com.example.atividade.repository;

import com.example.atividade.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("select e from pessoa e where e.nome = :nome")
    Pessoa findByNome(@Param("nome") String nome);
}
