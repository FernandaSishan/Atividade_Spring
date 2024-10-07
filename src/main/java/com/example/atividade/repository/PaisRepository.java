package com.example.atividade.repository;

import com.example.atividade.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PaisRepository extends JpaRepository<Pais, Long>{

    @Query("select e from pais e where e.nome = :nome")
    Pais findByNome(@Param("nome") String nome);
}
