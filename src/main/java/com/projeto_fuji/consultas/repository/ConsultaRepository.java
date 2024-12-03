package com.projeto_fuji.consultas.repository;

import com.projeto_fuji.consultas.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

        @Query("SELECT c FROM Consulta c WHERE c.id = :id")
    List<Consulta> buscarConsultaPorId(@Param("id") Long id);

}
