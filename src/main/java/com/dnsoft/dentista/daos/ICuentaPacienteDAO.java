package com.dnsoft.dentista.daos;

import com.dnsoft.dentista.beans.CuentaPaciente;
import com.dnsoft.dentista.beans.MonedaEnum;
import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.beans.PlanTratamiento;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICuentaPacienteDAO extends JpaRepository<CuentaPaciente, Long> {

    //List<CuentaPaciente> findByPacienteAndMonedaAndFechaGreaterThanEqual(Paciente Paciente, Moneda moneda, Date fecha);
    @Query("select cc from CuentaPaciente cc where cc.paciente=?1 and cc.moneda=?2 and cc.fechaMovimiento >=?3 and cc.fechaMovimiento<=?4 order by cc.fechaMovimiento Desc")
    List<CuentaPaciente> findByPacienteAndMonedaAndFechaBetween(Paciente paciente, MonedaEnum moneda, LocalDate fechaInicio, LocalDate fechaFin);

    //CuentaPaciente findByPaciente(Paciente Paciente);
    /*@Query("select c from CuentaPaciente c join fetch c.Paciente where c.id = (select max(id) from CuentaPaciente ca where ca.moneda = ?1 and ca.Paciente = ?2)")
    CuentaPaciente findUltimoMovimientoEager(Moneda moneda, Paciente Paciente);*/
    @Query("select c from CuentaPaciente c where c.id = (select max(id) from CuentaPaciente ca where ca.moneda = ?1 and ca.paciente = ?2) "
            + "and c.paciente = ?2")
    CuentaPaciente findUltimoMovimiento(MonedaEnum moneda, Paciente paciente);

    @Query("select c from CuentaPaciente c where c.paciente = ?1 and c.moneda=?2 order by c.fechaMovimiento Asc")
    List<CuentaPaciente> findByPacienteAndMonedaOrderFechaMovimientoAsc(Paciente Paciente, MonedaEnum moneda);

    CuentaPaciente findByPlanTratamiento(PlanTratamiento planTratamiento);
}
