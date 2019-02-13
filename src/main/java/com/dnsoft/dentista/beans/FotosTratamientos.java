/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "fotos_tratamientos")

public class FotosTratamientos implements Serializable {

    private static final long serialVersionUID = 1L;
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(name = "foto")
    private byte[] foto;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "comentarios")
    private String comentarios;
    @Column(name = "fotos_tratamientoscol")
    private String fotosTratamientoscol;
    
    @JoinColumn(name = "trabajos_tratamiento_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TrabajosTratamiento trabajosTratamiento;

    public FotosTratamientos() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getFotosTratamientoscol() {
        return fotosTratamientoscol;
    }

    public void setFotosTratamientoscol(String fotosTratamientoscol) {
        this.fotosTratamientoscol = fotosTratamientoscol;
    }

    public TrabajosTratamiento getTrabajosTratamiento() {
        return trabajosTratamiento;
    }

    public void setTrabajosTratamiento(TrabajosTratamiento trabajosTratamiento) {
        this.trabajosTratamiento = trabajosTratamiento;
    }

}
