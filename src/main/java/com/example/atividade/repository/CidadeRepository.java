package com.example.atividade.repository;


import com.example.atividade.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    @Query("select e from cidade e where e.nome = :nome")
    Cidade findByNome(@Param("nome") String nome);

}
