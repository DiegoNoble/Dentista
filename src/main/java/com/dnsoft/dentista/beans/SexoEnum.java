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
public enum SexoEnum {
    F("Femenino"), M("Masculino");
    
     private final String string;

    private SexoEnum(String name) {
        string = name;
    }

    @Override
    public String toString() {
        return string;
    }
}
