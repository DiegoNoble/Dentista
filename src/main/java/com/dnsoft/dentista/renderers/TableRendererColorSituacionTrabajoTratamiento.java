/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.renderers;

import com.dnsoft.dentista.beans.TrabajoTratamientoEnum;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Diego Noble
 */
public class TableRendererColorSituacionTrabajoTratamiento extends DefaultTableCellRenderer {

    public TableRendererColorSituacionTrabajoTratamiento() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {

        String trabajoTratamiento = (String) value;
        //setBackground(Color.white);//color de fondo
        table.setForeground(Color.black);//color de texto
        //Si la celda corresponde a una fila con estado FALSE, se cambia el color de fondo a rojo
        if (trabajoTratamiento.equals(TrabajoTratamientoEnum.P.toString())) {
            setBackground(Color.decode("#F6CECE"));
        }
        if (trabajoTratamiento.equals(TrabajoTratamientoEnum.D.toString())) {
            setBackground(Color.decode("#81F7BE"));
        }
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
    }

}
