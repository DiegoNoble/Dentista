package com.dnsoft.dentista.daos;

import com.dnsoft.dentista.beans.FotosTratamiento;
import com.dnsoft.dentista.beans.PlanTratamiento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFotosTratamientoDAO extends JpaRepository<FotosTratamiento, Long> {

  List<FotosTratamiento> findByPlanTratamiento(PlanTratamiento planTratamiento);

}
