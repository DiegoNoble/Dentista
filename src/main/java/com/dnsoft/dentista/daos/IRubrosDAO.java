package com.dnsoft.dentista.daos;

import com.dnsoft.dentista.beans.Rubro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRubrosDAO extends JpaRepository<Rubro, Long> {
    
   
}
