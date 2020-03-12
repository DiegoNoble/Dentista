package com.dnsoft.dentista.daos;

import com.dnsoft.dentista.beans.ClaseTratamiento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IClaseTratamientoDAO extends JpaRepository<ClaseTratamiento, Long> {

     @Query("select c from ClaseTratamiento c where c.nombre like %:texto% ")
    List<ClaseTratamiento> findByNombreLike(String texto);
    
}
