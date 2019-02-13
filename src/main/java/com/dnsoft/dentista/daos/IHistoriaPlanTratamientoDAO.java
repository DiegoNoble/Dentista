package com.dnsoft.dentista.daos;

import com.dnsoft.dentista.beans.HistoriaPlanTratamiento;
import com.dnsoft.dentista.beans.PlanTratamiento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHistoriaPlanTratamientoDAO extends JpaRepository<HistoriaPlanTratamiento, Long> {

    List<HistoriaPlanTratamiento> findByPlanTratamientoOrderByFechaDesc(PlanTratamiento planTratamiento);

}
