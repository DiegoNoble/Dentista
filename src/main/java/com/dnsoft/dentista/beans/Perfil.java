/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "perfil")

public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15, columnDefinition = "varchar(15)")
    @Enumerated(EnumType.STRING)
    private PerfilEnum perfilEnum;
    
    private Boolean Consultas;
    private Boolean Movimientos;
    private Boolean Pacientes;
    private Boolean agenda;
    private Boolean consultarTrabajos;
    private Boolean consultarDeudores;
    private Boolean consultarTratamientos;
    private Boolean crearPlanTratamiento;
    private Boolean cuentadePacientes;
    private Boolean parametros;
    private Boolean permisos;
    private Boolean registrarProveedor;
    private Boolean respaldos;
    private Boolean trabajosPendientes;
    private Boolean tratamientos;
    private Boolean usuarios;
    private Boolean clasificaTratamientos;
    private Boolean registraMediosDePago;

    public Perfil() {
    }

    public Perfil(Long id) {
        this.id = id;
    }

    public Perfil(Long id, PerfilEnum perfilEnum) {
        this.id = id;

        this.perfilEnum = perfilEnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PerfilEnum getPerfilEnum() {
        return perfilEnum;
    }

    public void setPerfilEnum(PerfilEnum perfilEnum) {
        this.perfilEnum = perfilEnum;
    }

    public Boolean getConsultas() {
        return Consultas;
    }

    public void setConsultas(Boolean Consultas) {
        this.Consultas = Consultas;
    }

    public Boolean getMovimientos() {
        return Movimientos;
    }

    public void setMovimientos(Boolean Movimientos) {
        this.Movimientos = Movimientos;
    }

    public Boolean getPacientes() {
        return Pacientes;
    }

    public void setPacientes(Boolean Pacientes) {
        this.Pacientes = Pacientes;
    }

    public Boolean getAgenda() {
        return agenda;
    }

    public void setAgenda(Boolean agenda) {
        this.agenda = agenda;
    }

    public Boolean getConsultarTrabajos() {
        return consultarTrabajos;
    }

    public void setConsultarTrabajos(Boolean consultarTrabajos) {
        this.consultarTrabajos = consultarTrabajos;
    }

    public Boolean getConsultarDeudores() {
        return consultarDeudores;
    }

    public void setConsultarDeudores(Boolean consultarDeudores) {
        this.consultarDeudores = consultarDeudores;
    }

    public Boolean getConsultarTratamientos() {
        return consultarTratamientos;
    }

    public void setConsultarTratamientos(Boolean consultarTratamientos) {
        this.consultarTratamientos = consultarTratamientos;
    }

    public Boolean getCrearPlanTratamiento() {
        return crearPlanTratamiento;
    }

    public void setCrearPlanTratamiento(Boolean crearPlanTratamiento) {
        this.crearPlanTratamiento = crearPlanTratamiento;
    }

    public Boolean getCuentadePacientes() {
        return cuentadePacientes;
    }

    public void setCuentadePacientes(Boolean cuentadePacientes) {
        this.cuentadePacientes = cuentadePacientes;
    }

    public Boolean getParametros() {
        return parametros;
    }

    public void setParametros(Boolean parametros) {
        this.parametros = parametros;
    }

    public Boolean getPermisos() {
        return permisos;
    }

    public void setPermisos(Boolean permisos) {
        this.permisos = permisos;
    }

    public Boolean getRegistrarProveedor() {
        return registrarProveedor;
    }

    public void setRegistrarProveedor(Boolean registrarProveedor) {
        this.registrarProveedor = registrarProveedor;
    }

    public Boolean getRespaldos() {
        return respaldos;
    }

    public void setRespaldos(Boolean respaldos) {
        this.respaldos = respaldos;
    }

    public Boolean getTrabajosPendientes() {
        return trabajosPendientes;
    }

    public void setTrabajosPendientes(Boolean trabajosPendientes) {
        this.trabajosPendientes = trabajosPendientes;
    }

    public Boolean getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(Boolean tratamientos) {
        this.tratamientos = tratamientos;
    }

    public Boolean getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Boolean usuarios) {
        this.usuarios = usuarios;
    }

    public Boolean getClasificaTratamientos() {
        return clasificaTratamientos;
    }

    public void setClasificaTratamientos(Boolean clasificaTratamientos) {
        this.clasificaTratamientos = clasificaTratamientos;
    }

    public Boolean getRegistraMediosDePago() {
        return registraMediosDePago;
    }

    public void setRegistraMediosDePago(Boolean registraMediosDePago) {
        this.registraMediosDePago = registraMediosDePago;
    }
    
    

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return perfilEnum.toString();
    }

}
