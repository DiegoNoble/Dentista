package com.dnsoft.dentista.daos;

import com.dnsoft.dentista.beans.Paciente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPacienteDAO extends JpaRepository<Paciente, Long> {

    @Query("select p from Paciente p where p.nombre like %?1% or p.documento like %?1%")
    List<Paciente> findByNombreDocumento(String texto);

}
