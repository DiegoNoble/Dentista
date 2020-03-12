/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.beans;

import java.io.Serializable;
import java.time.LocalDate;
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

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "trabajos_tratamiento")

public class TrabajosTratamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "valor")
    private Double valor;
    @Column(name = "finalizado")
    private Boolean finalizado;

    @Column(name = "fecha_finalizado")
    private LocalDate fechaFinalizado;

    @Column(name = "fecha_debito")
    private LocalDate fechaDebito;

    private String pieza;

    @JoinColumn(name = "plan_tratamiento_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PlanTratamiento planTratamiento;

    @JoinColumn(name = "trabajos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Trabajos trabajos;

    @Column(length = 32, columnDefinition = "varchar(32) default 'P'")
    @Enumerated(EnumType.STRING)
    private TrabajoTratamientoEnum trabajoTratamientoEnum = TrabajoTratamientoEnum.D;

    public TrabajosTratamiento() {
    }

    public TrabajosTratamiento(Double valor, String pieza, Trabajos trabajos, TrabajoTratamientoEnum trabajoTratamientoEnum) {
        this.valor = valor;
        this.pieza = pieza;
        this.trabajos = trabajos;
        this.trabajoTratamientoEnum = trabajoTratamientoEnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public LocalDate getFechaFinalizado() {
        return fechaFinalizado;
    }

    public void setFechaFinalizado(LocalDate fechaFinalizado) {
        this.fechaFinalizado = fechaFinalizado;
    }

    public PlanTratamiento getPlanTratamiento() {
        return planTratamiento;
    }

    public void setPlanTratamiento(PlanTratamiento planTratamiento) {
        this.planTratamiento = planTratamiento;
    }

    public Trabajos getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(Trabajos trabajos) {
        this.trabajos = trabajos;
    }

    public String getPieza() {
        return pieza;
    }

    public void setPieza(String pieza) {
        this.pieza = pieza;
    }

    public TrabajoTratamientoEnum getTrabajoTratamientoEnum() {
        return trabajoTratamientoEnum;
    }

    public void setTrabajoTratamientoEnum(TrabajoTratamientoEnum trabajoTratamientoEnum) {
        this.trabajoTratamientoEnum = trabajoTratamientoEnum;
    }

    public LocalDate getFechaDebito() {
        return fechaDebito;
    }

    public void setFechaDebito(LocalDate fechaDebito) {
        this.fechaDebito = fechaDebito;
    }

}
