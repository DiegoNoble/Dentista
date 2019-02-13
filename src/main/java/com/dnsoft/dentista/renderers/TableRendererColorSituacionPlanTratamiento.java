/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.renderers;

import com.dnsoft.dentista.beans.SituacionPlanTratamientoEnum;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Diego Noble
 */
public class TableRendererColorSituacionPlanTratamiento extends DefaultTableCellRenderer {

    public TableRendererColorSituacionPlanTratamiento() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {

        SituacionPlanTratamientoEnum planTratamiento = (SituacionPlanTratamientoEnum) value;
        //setBackground(Color.white);//color de fondo
        table.setForeground(Color.black);//color de texto
        //Si la celda corresponde a una fila con estado FALSE, se cambia el color de fondo a rojo
        if (planTratamiento.equals(SituacionPlanTratamientoEnum.PRESUPUESTO)) {
            setForeground(Color.red);
            setBorder(new LineBorder(Color.red));
        }
        if (planTratamiento.equals(SituacionPlanTratamientoEnum.CONFIRMA_PRESUPUESTO)) {
            setForeground(Color.GRAY);
            setBorder(new LineBorder(Color.GRAY));
        }
        if (planTratamiento.equals(SituacionPlanTratamientoEnum.TRATAMIENTO_EN_CURSO)) {
            setForeground(Color.green);
            setBorder(new LineBorder(Color.green));
        }
        if (planTratamiento.equals(SituacionPlanTratamientoEnum.TRATAMIENTO_FINALIZADO)) {
            setForeground(Color.blue);
            setBorder(new LineBorder(Color.blue));
        }
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
    }

}
