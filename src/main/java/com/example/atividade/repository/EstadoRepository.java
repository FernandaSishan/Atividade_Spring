package com.example.atividade.repository;

import com.example.atividade.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    @Query("select e from estado e where e.nome = :nome")
    Estado findByNome(@Param("nome") String nome);
}
