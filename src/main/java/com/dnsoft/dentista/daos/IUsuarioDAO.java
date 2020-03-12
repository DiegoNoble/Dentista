package com.dnsoft.dentista.daos;


import com.dnsoft.dentista.beans.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioDAO extends JpaRepository<Usuario, Long> {

    
     @Query("select u from Usuario u where u.nombre = :nombre and u.pass = :pass")
     Usuario isUsernameAndPasswordExists(String nombre, String pass);

    
   

}
