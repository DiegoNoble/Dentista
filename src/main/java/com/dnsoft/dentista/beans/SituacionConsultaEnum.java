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
public enum SituacionConsultaEnum {
    PENDIENTE("Confirmada"),
    CONSULTA_CANCELADA("Cancelada");

    private final String string;

    private SituacionConsultaEnum(String name) {
        string = name;
    }

    @Override
    public String toString() {
        return string;
    }
}
