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
public enum PerfilEnum {
    ADMINISTRADOR("ADMINISTRADOR"), USUARIO("USUARIO");
    
     private final String string;

    private PerfilEnum(String name) {
        string = name;
    }

    @Override
    public String toString() {
        return string;
    }
   
}
