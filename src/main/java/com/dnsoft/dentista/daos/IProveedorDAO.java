package com.dnsoft.dentista.daos;

import com.dnsoft.dentista.beans.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProveedorDAO extends JpaRepository<Proveedor, Long> {

    @Query("select p from Proveedor p where p.nombre like %:texto%")
    List<Proveedor> findByNombreLike(String texto);

}
