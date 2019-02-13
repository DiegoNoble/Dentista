/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "consulta")

public class Consulta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha_consulta")
    private LocalDate fechaConsulta;

    @Column(name = "hora_consulta_desde")
    private LocalTime horaConsultaDesde;

    @Column(name = "hora_consulta_hasta")
    private LocalTime horaConsultaHasta;

    @Column(name = "confirma_tratamiento")
    private Boolean confirmaTratamiento;
    @Column(name = "fecha_confirma_tratamiento")
    @Temporal(TemporalType.DATE)
    private Date fechaConfirmaTratamiento;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "hora_desde")
    private String hora_desde;
    @Column(name = "hora_hasta")
    private String hora_hasta;

    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Paciente paciente;
    @Enumerated(EnumType.STRING)
    private SituacionConsultaEnum situacionConsulta;

    public Consulta() {
    }

    public Consulta(String hora_desde, String hora_hasta) {
        this.hora_desde = hora_desde;
        this.hora_hasta = hora_hasta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(LocalDate fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public Boolean getConfirmaTratamiento() {
        return confirmaTratamiento;
    }

    public void setConfirmaTratamiento(Boolean confirmaTratamiento) {
        this.confirmaTratamiento = confirmaTratamiento;
    }

    public Date getFechaConfirmaTratamiento() {
        return fechaConfirmaTratamiento;
    }

    public void setFechaConfirmaTratamiento(Date fechaConfirmaTratamiento) {
        this.fechaConfirmaTratamiento = fechaConfirmaTratamiento;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalTime getHoraConsultaDesde() {
        return horaConsultaDesde;
    }

    public void setHoraConsultaDesde(LocalTime horaConsultaDesde) {
        this.horaConsultaDesde = horaConsultaDesde;
    }

    public LocalTime getHoraConsultaHasta() {
        return horaConsultaHasta;
    }

    public void setHoraConsultaHasta(LocalTime horaConsultaHasta) {
        this.horaConsultaHasta = horaConsultaHasta;
    }

    public String getHora_desde() {
        return hora_desde;
    }

    public void setHora_desde(String hora_desde) {
        this.hora_desde = hora_desde;
    }

    public String getHora_hasta() {
        return hora_hasta;
    }

    public void setHora_hasta(String hora_hasta) {
        this.hora_hasta = hora_hasta;
    }

    public SituacionConsultaEnum getSituacionConsulta() {
        return situacionConsulta;
    }

    public void setSituacionConsulta(SituacionConsultaEnum situacionConsulta) {
        this.situacionConsulta = situacionConsulta;
    }

    @Override
    public String toString() {
        return "Consulta: "+fechaConsulta.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+" "+paciente;
    }

    
}
