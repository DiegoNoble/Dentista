package com.dnsoft.dentista.daos;

import com.dnsoft.dentista.beans.MonedaEnum;
import com.dnsoft.dentista.beans.Trabajos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ITrabajosDAO extends JpaRepository<Trabajos, Long> {

    @Query("select t from Trabajos t where t.nombre like %?1% or t.claseTratamiento.nombre like %?1% "
            + "order by t.claseTratamiento.nombre, t.nombre")
    List<Trabajos> findByNombreClase(String texto);

    List<Trabajos> findByMoneda(MonedaEnum monedaEnum);

}
