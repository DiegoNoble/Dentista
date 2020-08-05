/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "historia_clinica")

public class HistoriaClinica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Paciente paciente;

    @Column(name = "esta_en_tratamiento")
    private Boolean estaEnTratamiento;

    private String detalleTratamiento;

    @Column(name = "toma_medicacion")
    private Boolean tomaMedicacion;

    @Column(name = "detalle_medicacion")
    private String DetalleMedicacion;

    private Boolean hipertension;

    private Boolean infarto;

    @Column(name = "angina_de_pecho")
    private Boolean anginaDePecho;

    @Column(name = "fiebre_reumatica")
    private Boolean fiebreReumatica;

    @Column(name = "protesis_valvulares")
    private Boolean protesisValvulares;

    @Column(name = "enfermedades_respiratorias")
    private Boolean enfermedadesRespiratorias;

    @Column(name = "detalle_enfermedades")
    private String DetalleEnfermedades;

    @Column(name = "alergico_medicamentos")
    private Boolean alergicoMedicamentos;

    @Column(name = "detalle_alergias")
    private String DetalleAlergias;

    @Column(name = "diabetico")
    private Boolean diabetico;

    @Column(name = "insulino_dependiente")
    private Boolean insulinoDependiente;

    @Column(name = "hepatitis")
    private Boolean hepatitis;

    @Column(name = "detalle_hepatitis")
    private String detalleHepatitis;

    @Column(name = "hiv")
    private Boolean hiv;

    @Column(name = "cirugias")
    private Boolean cirugias;

    @Column(name = "detalle_cirugia")
    private String detalleCirugias;

    @Column(name = "problemas_cicatrizacion")
    private Boolean problemas_cicatrizacion;

    @Column(name = "quimio_radio")
    private Boolean quimioRadioTerapia;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_quimio_radio")
    private Date FechaQuimioRadioTerapia;

    private Boolean aspirina;
    private Boolean anticoagulante;
    private Boolean sedante;
    private Boolean hipoglucemiente;
    private Boolean nitroglicerina;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Boolean getEstaEnTratamiento() {
        return estaEnTratamiento;
    }

    public void setEstaEnTratamiento(Boolean estaEnTratamiento) {
        this.estaEnTratamiento = estaEnTratamiento;
    }

    public String getDetalleTratamiento() {
        return detalleTratamiento;
    }

    public void setDetalleTratamiento(String detalleTratamiento) {
        this.detalleTratamiento = detalleTratamiento;
    }

    public Boolean getTomaMedicacion() {
        return tomaMedicacion;
    }

    public void setTomaMedicacion(Boolean tomaMedicacion) {
        this.tomaMedicacion = tomaMedicacion;
    }

    public Boolean getHipertension() {
        return hipertension;
    }

    public void setHipertension(Boolean hipertension) {
        this.hipertension = hipertension;
    }

    public String getDetalleMedicacion() {
        return DetalleMedicacion;
    }

    public void setDetalleMedicacion(String DetalleMedicacion) {
        this.DetalleMedicacion = DetalleMedicacion;
    }

    public Boolean getInfarto() {
        return infarto;
    }

    public void setInfarto(Boolean infarto) {
        this.infarto = infarto;
    }

    public Boolean getAnginaDePecho() {
        return anginaDePecho;
    }

    public void setAnginaDePecho(Boolean anginaDePecho) {
        this.anginaDePecho = anginaDePecho;
    }

    public Boolean getFiebreReumatica() {
        return fiebreReumatica;
    }

    public void setFiebreReumatica(Boolean fiebreReumatica) {
        this.fiebreReumatica = fiebreReumatica;
    }

    public Boolean getProtesisValvulares() {
        return protesisValvulares;
    }

    public void setProtesisValvulares(Boolean protesisValvulares) {
        this.protesisValvulares = protesisValvulares;
    }

    public Boolean getEnfermedadesRespiratorias() {
        return enfermedadesRespiratorias;
    }

    public void setEnfermedadesRespiratorias(Boolean enfermedadesRespiratorias) {
        this.enfermedadesRespiratorias = enfermedadesRespiratorias;
    }

    public String getDetalleEnfermedades() {
        return DetalleEnfermedades;
    }

    public void setDetalleEnfermedades(String DetalleEnfermedades) {
        this.DetalleEnfermedades = DetalleEnfermedades;
    }

    public Boolean getAlergicoMedicamentos() {
        return alergicoMedicamentos;
    }

    public void setAlergicoMedicamentos(Boolean alergicoMedicamentos) {
        this.alergicoMedicamentos = alergicoMedicamentos;
    }

    public String getDetalleAlergias() {
        return DetalleAlergias;
    }

    public void setDetalleAlergias(String DetalleAlergias) {
        this.DetalleAlergias = DetalleAlergias;
    }

    public Boolean getDiabetico() {
        return diabetico;
    }

    public void setDiabetico(Boolean diabetico) {
        this.diabetico = diabetico;
    }

    public Boolean getInsulinoDependiente() {
        return insulinoDependiente;
    }

    public void setInsulinoDependiente(Boolean insulinoDependiente) {
        this.insulinoDependiente = insulinoDependiente;
    }

    public Boolean getHepatitis() {
        return hepatitis;
    }

    public void setHepatitis(Boolean hepatitis) {
        this.hepatitis = hepatitis;
    }

    public String getDetalleHepatitis() {
        return detalleHepatitis;
    }

    public void setDetalleHepatitis(String detalleHepatitis) {
        this.detalleHepatitis = detalleHepatitis;
    }

    public Boolean getHiv() {
        return hiv;
    }

    public void setHiv(Boolean hiv) {
        this.hiv = hiv;
    }

    public Boolean getCirugias() {
        return cirugias;
    }

    public void setCirugias(Boolean cirugias) {
        this.cirugias = cirugias;
    }

    public String getDetalleCirugias() {
        return detalleCirugias;
    }

    public void setDetalleCirugias(String detalleCirugias) {
        this.detalleCirugias = detalleCirugias;
    }

    public Boolean getProblemas_cicatrizacion() {
        return problemas_cicatrizacion;
    }

    public void setProblemas_cicatrizacion(Boolean problemas_cicatrizacion) {
        this.problemas_cicatrizacion = problemas_cicatrizacion;
    }

    public Boolean getQuimioRadioTerapia() {
        return quimioRadioTerapia;
    }

    public void setQuimioRadioTerapia(Boolean quimioRadioTerapia) {
        this.quimioRadioTerapia = quimioRadioTerapia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaQuimioRadioTerapia() {
        return FechaQuimioRadioTerapia;
    }

    public void setFechaQuimioRadioTerapia(Date FechaQuimioRadioTerapia) {
        this.FechaQuimioRadioTerapia = FechaQuimioRadioTerapia;
    }

    public Boolean getAspirina() {
        return aspirina;
    }

    public void setAspirina(Boolean aspirina) {
        this.aspirina = aspirina;
    }

    public Boolean getAnticoagulante() {
        return anticoagulante;
    }

    public void setAnticoagulante(Boolean anticoagulante) {
        this.anticoagulante = anticoagulante;
    }

    public Boolean getSedante() {
        return sedante;
    }

    public void setSedante(Boolean sedante) {
        this.sedante = sedante;
    }

    public Boolean getHipoglucemiente() {
        return hipoglucemiente;
    }

    public void setHipoglucemiente(Boolean hipoglucemiente) {
        this.hipoglucemiente = hipoglucemiente;
    }

    public Boolean getNitroglicerina() {
        return nitroglicerina;
    }

    public void setNitroglicerina(Boolean nitroglicerina) {
        this.nitroglicerina = nitroglicerina;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HistoriaClinica other = (HistoriaClinica) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
