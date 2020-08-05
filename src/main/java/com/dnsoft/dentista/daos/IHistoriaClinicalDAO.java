package com.dnsoft.dentista.daos;

import com.dnsoft.dentista.beans.HistoriaClinica;
import com.dnsoft.dentista.beans.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IHistoriaClinicalDAO extends JpaRepository<HistoriaClinica, Long> {

    @Query("select p from HistoriaClinica p where p.paciente = :paciente ")
    HistoriaClinica findHistoriaPaciente(Paciente paciente);

}
