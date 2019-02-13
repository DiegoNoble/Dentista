package com.dnsoft.dentista.daos;


import com.dnsoft.dentista.beans.Caja;
import com.dnsoft.dentista.beans.MonedaEnum;
import com.dnsoft.dentista.beans.Pagos;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICajaDAO extends JpaRepository<Caja, Long> {

    List<Caja> findByMonedaAndFechaBetweenOrderByFechaDesc(MonedaEnum moneda, LocalDate fechaInicio, LocalDate fechaFin);

    @Query("select c from Caja c where c.fecha >= ?1 and c.moneda = ?2 order by fecha desc")
    List<Caja> findByFechaAfterOrFechaEqualAndMonedaOrderByFechaDesc(LocalDate fecha, MonedaEnum moneda);

    @Query("select c from Caja c where c.id = (select max(id) from Caja ca where ca.moneda = ?1)")
    Caja findUltimoMovimiento(MonedaEnum moneda);
    
    
    //@Query("select c from Caja c where c.id = (select max(id) from Caja ca where ca.moneda = ?1 and ca.tipoDeCaja = ?2)")
    //Caja findUltimoMovimiento(Moneda moneda, TipoDeCaja tipoDeCaja);

    @Query("select c from Caja c where c.id = (select max(id) from Caja ca where ca.moneda = ?1 and ca.fecha < ?2)")
    Caja findSaldoDiaAnterior(MonedaEnum moneda, LocalDate fecha);
    
    Caja findByPagos(Pagos pagos);

}
