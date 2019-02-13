/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "plan_tratamiento")

public class PlanTratamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_total")
    private Double valorTotal;

    @Column(name = "moneda")
    @Enumerated(EnumType.STRING)
    private MonedaEnum moneda;

    private LocalDate fechaCreacion;

    private LocalDate fechaFinalizado;

    private LocalDate fechaConfirmacion;

    private LocalDate fechaInicio;

    private String observaciones;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planTratamiento", fetch = FetchType.EAGER)
    private List<TrabajosTratamiento> trabajosTratamientoList;

    @JoinColumn(name = "consulta_id", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Consulta consulta;

    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Paciente paciente;

    @Enumerated(EnumType.STRING)
    private SituacionPlanTratamientoEnum situacionPlanTratamientoEnum;

    public PlanTratamiento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SituacionPlanTratamientoEnum getSituacionPlanTratamientoEnum() {
        return situacionPlanTratamientoEnum;
    }

    public void setSituacionPlanTratamientoEnum(SituacionPlanTratamientoEnum situacionPlanTratamientoEnum) {
        this.situacionPlanTratamientoEnum = situacionPlanTratamientoEnum;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<TrabajosTratamiento> getTrabajosTratamientoList() {
        return trabajosTratamientoList;
    }

    public void setTrabajosTratamientoList(List<TrabajosTratamiento> trabajosTratamientoList) {
        this.trabajosTratamientoList = trabajosTratamientoList;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public LocalDate getFechaFinalizado() {
        return fechaFinalizado;
    }

    public void setFechaFinalizado(LocalDate fechaFinalizado) {
        this.fechaFinalizado = fechaFinalizado;
    }

    public LocalDate getFechaConfirmacion() {
        return fechaConfirmacion;
    }

    public void setFechaConfirmacion(LocalDate fechaConfirmacion) {
        this.fechaConfirmacion = fechaConfirmacion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public MonedaEnum getMoneda() {
        return moneda;
    }

    public void setMoneda(MonedaEnum moneda) {
        this.moneda = moneda;
    }

}
