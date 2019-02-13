/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.beans;

/**
 *
 * @author Diego Noble
 */
public enum SituacionPlanTratamientoEnum {
    PRESUPUESTO("Presupuesto"),
    CONFIRMA_PRESUPUESTO("Confirmado"),
    TRATAMIENTO_EN_CURSO("En curso"),
    TRATAMIENTO_FINALIZADO("Finalizado");

    private final String string;


private SituacionPlanTratamientoEnum(String name) {
        string = name;
    }

    @Override
        public String toString() {
        return string;
    }
}
