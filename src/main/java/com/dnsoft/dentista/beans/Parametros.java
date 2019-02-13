/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Diego Noble
 */
@Entity
@Table(name = "parametros")

public class Parametros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String MySql_Path;
    private String nombreBasesDatos;
    private Integer idWebCam;
    private String direccionConsultorio;
    private String directorioCredencialesGoogleCalendar;
    private int activaGoogleCalendar;
    @ManyToOne
    private Rubro cobroCuentaPaciente;
    private String usuario_SMS;
    private String psw_SMS;

    public Parametros() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMySql_Path() {
        return MySql_Path;
    }

    public void setMySql_Path(String MySql_Path) {
        this.MySql_Path = MySql_Path;
    }

    public String getNombreBasesDatos() {
        return nombreBasesDatos;
    }

    public void setNombreBasesDatos(String nombreBasesDatos) {
        this.nombreBasesDatos = nombreBasesDatos;
    }

    public Integer getIdWebCam() {
        return idWebCam;
    }

    public void setIdWebCam(Integer idWebCam) {
        this.idWebCam = idWebCam;
    }

    public String getDireccionConsultorio() {
        return direccionConsultorio;
    }

    public void setDireccionConsultorio(String direccionConsultorio) {
        this.direccionConsultorio = direccionConsultorio;
    }

    public String getDirectorioCredencialesGoogleCalendar() {
        return directorioCredencialesGoogleCalendar;
    }

    public void setDirectorioCredencialesGoogleCalendar(String directorioCredencialesGoogleCalendar) {
        this.directorioCredencialesGoogleCalendar = directorioCredencialesGoogleCalendar;
    }

    public int getActivaGoogleCalendar() {
        return activaGoogleCalendar;
    }

    public void setActivaGoogleCalendar(int activaGoogleCalendar) {
        this.activaGoogleCalendar = activaGoogleCalendar;
    }

    public Rubro getCobroCuentaPaciente() {
        return cobroCuentaPaciente;
    }

    public void setCobroCuentaPaciente(Rubro cobroCuentaPaciente) {
        this.cobroCuentaPaciente = cobroCuentaPaciente;
    }

    public String getUsuario_SMS() {
        return usuario_SMS;
    }

    public void setUsuario_SMS(String usuario_SMS) {
        this.usuario_SMS = usuario_SMS;
    }

    public String getPsw_SMS() {
        return psw_SMS;
    }

    public void setPsw_SMS(String psw_SMS) {
        this.psw_SMS = psw_SMS;
    }
    
    

}
