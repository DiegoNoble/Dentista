package com.dnsoft.dentista.daos;

import com.dnsoft.dentista.beans.Consulta;
import com.dnsoft.dentista.beans.Paciente;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IConsultaDAO extends JpaRepository<Consulta, Long> {
    
    List<Consulta> findByFechaConsultaOrderByHoraConsultaDesdeAsc(LocalDate fechaSeleccionada);

    @Query("from Consulta c where c.fechaConsulta = (select min(fechaConsulta) from Consulta where paciente = ?1 and fechaConsulta > current_date())")
    Consulta buscarProximaConsulta(Paciente paciente);
    
    List<Consulta> findByPacienteOrderByFechaConsultaDesc(Paciente paciente);
}
