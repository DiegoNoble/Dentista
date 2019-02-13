/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.renderers;

import java.awt.Color;
import java.awt.Component;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Diego Noble
 */
public class TableRendererColorTrabajoProveedor extends DefaultTableCellRenderer {

    private int ColReferencia;

    public TableRendererColorTrabajoProveedor(int ColReferencia) {
        this.ColReferencia = ColReferencia;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {

        table.setForeground(Color.black);//color de texto

        LocalDate fechaProgramada = (LocalDate) table.getValueAt(row, ColReferencia);
        long diferencia = Duration.between(LocalDate.now().atStartOfDay(), fechaProgramada.atStartOfDay()).toDays();
        /*if (diferencia < new Long(5)) {
            setForeground(Color.red);
            setBorder(new LineBorder(Color.red));
        }*/
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
    }

    @Override
    protected void setValue(Object value) {
        if (value != null) {
            LocalDate formatoRecibido = (LocalDate) value;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            super.setValue(formatoRecibido.format(formatter));

        } else {
            super.setValue("--");
        }
    }

}
