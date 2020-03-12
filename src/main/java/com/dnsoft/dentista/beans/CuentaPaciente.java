/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.beans;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "cuenta_paciente")

public class CuentaPaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "debe")
    private BigDecimal debe;
    @Column(name = "haber")
    private BigDecimal haber;
    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "fecha_movimiento")
    private LocalDate fechaMovimiento;

    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paciente paciente;

    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private MonedaEnum moneda;

    @JoinColumn(name = "plan_tratamiento_id", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private PlanTratamiento planTratamiento;

    @JoinColumn(name = "trabajos_tratamiento_id", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private TrabajosTratamiento trabajosTratamiento;

    private String observacion;
    
    @JoinColumn(name = "medios_de_pago_id", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private MediosDePago mediosDePago;
    
    private Integer cuotas;

    

    public CuentaPaciente() {
    }
    
    public CuentaPaciente(String nombre, Double debe, Double haber, Double saldo) {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDebe() {
        return debe;
    }

    public void setDebe(BigDecimal debe) {
        this.debe = debe;
    }

    public BigDecimal getHaber() {
        return haber;
    }

    public void setHaber(BigDecimal haber) {
        this.haber = haber;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDate fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public PlanTratamiento getPlanTratamiento() {
        return planTratamiento;
    }

    public void setPlanTratamiento(PlanTratamiento planTratamiento) {
        this.planTratamiento = planTratamiento;
    }

    public MonedaEnum getMoneda() {
        return moneda;
    }

    public void setMoneda(MonedaEnum moneda) {
        this.moneda = moneda;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public TrabajosTratamiento getTrabajoTratamiento() {
        return trabajosTratamiento;
    }

    public void setTrabajoTratamiento(TrabajosTratamiento trabajoTratamiento) {
        this.trabajosTratamiento = trabajoTratamiento;
    }

    public MediosDePago getMediosDePago() {
        return mediosDePago;
    }

    public void setMediosDePago(MediosDePago mediosDePago) {
        this.mediosDePago = mediosDePago;
    }

    public Integer getCuotas() {
        return cuotas;
    }

    public void setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
    }

    
}
