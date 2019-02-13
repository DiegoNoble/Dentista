package com.dnsoft.dentista.daos;

import com.dnsoft.dentista.beans.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPagosDAO extends JpaRepository<Pagos, Long> {

    //List<Pagos> findByRecibo(Recibo recibo);

    @Query("from Pagos p join fetch p.cuentaPaciente where p.id = ?1")
    Pagos findFetch(Long idPago);

    /*@Query("from Pagos p join fetch p.recibo as R where MONTH(p.fecha) = ?1 and YEAR(p.fecha) = ?2 and R.contrato.tipoContrato = ?3 and R.contrato.iva = ?4")
    List<PagoRecibo> findPagosParaFacturacion(Integer mes, Integer ano, TipoContrato tipoContrato, Iva iva);
    */
}
