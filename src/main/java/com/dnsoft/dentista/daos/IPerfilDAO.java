package com.dnsoft.dentista.daos;


import com.dnsoft.dentista.beans.Perfil;
import com.dnsoft.dentista.beans.PerfilEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPerfilDAO extends JpaRepository<Perfil, Long> {

    @Query("select p from Perfil p where p.perfilEnum = :perfilEnum ")
     Perfil findPerfil(PerfilEnum perfilEnum);
   
 }
