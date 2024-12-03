package com.projeto_fuji.consultas.repository;

import com.projeto_fuji.consultas.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

        @Query("SLECT c FROM Consulta c WHERE c.id = :id")
    List<Consulta> buscarConsultaPorId(@Param("id") Long id);

    @Query("SELECT c.Medico.nome, c.Paciente.nome FROM Consulta c")
    List<Object[]> buscarMedicoEPaciente();
}
