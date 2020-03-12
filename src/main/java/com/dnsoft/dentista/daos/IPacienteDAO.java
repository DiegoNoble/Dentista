package com.dnsoft.dentista.daos;

import com.dnsoft.dentista.beans.Paciente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPacienteDAO extends JpaRepository<Paciente, Long> {

    @Query("select p from Paciente p where p.nombre like %:texto% or p.documento like %:texto%")
    List<Paciente> findByNombreDocumento(String texto);

}
