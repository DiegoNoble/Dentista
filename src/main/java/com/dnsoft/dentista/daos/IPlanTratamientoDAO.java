package com.dnsoft.dentista.daos;

import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.beans.PlanTratamiento;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPlanTratamientoDAO extends JpaRepository<PlanTratamiento, Long> {

    @Query("select p from PlanTratamiento p where p.consulta.fechaConsulta between ?1 and ?2 order by p.consulta.fechaConsulta desc")
    List<PlanTratamiento> findBetweenFechaConsulta(LocalDate fechaDesde, LocalDate fechaHasta);

    @Query("Select p from PlanTratamiento p where p.paciente = ?1 order by p.fechaCreacion desc")
    List<PlanTratamiento> findByPaciente(Paciente paciente);

    @Query("select p from PlanTratamiento p join fetch p.consulta c where c.fechaConsulta between ?1 and ?2 and  c.paciente = ?3 order by c.fechaConsulta desc")
    List<PlanTratamiento> findBetweenFechaConsultaAndPaciente(LocalDate fechaDesde, LocalDate fechaHasta, Paciente paciente);

    @Query("Select p from PlanTratamiento p join fetch p.trabajosTratamientoList t join fetch t.trabajos where p.id = ?1")
    PlanTratamiento findPlanTratamientoFetch(Long idPlanTratamiento);

}
