package com.dnsoft.dentista.daos;

import com.dnsoft.dentista.beans.Proveedor;
import com.dnsoft.dentista.beans.TrabajosProveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ITrabajosProveedorDAO extends JpaRepository<TrabajosProveedor, Long> {

    List<TrabajosProveedor> findByProveedor(Proveedor proveedor);

    @Query("select t from TrabajosProveedor t join fetch t.planTratamiento p join fetch p.consulta c join fetch c.paciente "
            + "where t.finalizado = false order by t.fechaEntregaProgramada asc")
    List<TrabajosProveedor> findTrabajosPenientes();

}
