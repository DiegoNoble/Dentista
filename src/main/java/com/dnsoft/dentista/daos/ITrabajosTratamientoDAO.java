package com.dnsoft.dentista.daos;

import com.dnsoft.dentista.beans.PlanTratamiento;
import com.dnsoft.dentista.beans.TrabajosTratamiento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ITrabajosTratamientoDAO extends JpaRepository<TrabajosTratamiento, Long> {

    @Query("Select t from TrabajosTratamiento t join fetch t.trabajos tr where t.planTratamiento = ?1")
    List<TrabajosTratamiento> findByPlanTratamiento(PlanTratamiento planTratamiento);

}
