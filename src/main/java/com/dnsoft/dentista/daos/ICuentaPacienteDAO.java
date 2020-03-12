package com.dnsoft.dentista.daos;

import com.dnsoft.dentista.beans.CuentaPaciente;
import com.dnsoft.dentista.beans.MonedaEnum;
import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.beans.PlanTratamiento;
import com.dnsoft.dentista.beans.TrabajosTratamiento;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICuentaPacienteDAO extends JpaRepository<CuentaPaciente, Long> {

    //List<CuentaPaciente> findByPacienteAndMonedaAndFechaGreaterThanEqual(Paciente Paciente, Moneda moneda, Date fecha);
    @Query("select cc from CuentaPaciente cc where cc.paciente=:paciente and cc.moneda=:moneda and cc.fechaMovimiento >=:fechaInicio and cc.fechaMovimiento<=:fechaFin order by cc.fechaMovimiento Desc, cc.id desc")
    List<CuentaPaciente> findByPacienteAndMonedaAndFechaBetween(Paciente paciente, MonedaEnum moneda, LocalDate fechaInicio, LocalDate fechaFin);

    //CuentaPaciente findByPaciente(Paciente Paciente);
    /*@Query("select c from CuentaPaciente c join fetch c.Paciente where c.id = (select max(id) from CuentaPaciente ca where ca.moneda = ?1 and ca.Paciente = ?2)")
    CuentaPaciente findUltimoMovimientoEager(Moneda moneda, Paciente Paciente);*/
    @Query("select c from CuentaPaciente c where c.id = (select max(id) from CuentaPaciente ca where ca.moneda = :moneda and ca.paciente = :paciente) "
            + "and c.paciente = ?2")
    CuentaPaciente findUltimoMovimiento(MonedaEnum moneda, Paciente paciente);

    @Query("select c from CuentaPaciente c where c.paciente = :paciente and c.moneda=:moneda order by c.fechaMovimiento Asc")
    List<CuentaPaciente> findByPacienteAndMonedaOrderFechaMovimientoAsc(Paciente paciente, MonedaEnum moneda);

    CuentaPaciente findByPlanTratamiento(PlanTratamiento planTratamiento);

    CuentaPaciente findByTrabajosTratamiento(TrabajosTratamiento trabajosTratamiento);
    
    @Query(value="SELECT p.nombre,sum(debe) as debe,sum(haber) as haber, sum(haber)-sum(debe) as saldo\n" +
    "FROM cuenta_paciente c, paciente p where c.paciente_id = p.id and p.nombre like %:texto% group by p.id", nativeQuery = true)
    ArrayList<Object[]> findDeudores(String texto);
    
   
}
