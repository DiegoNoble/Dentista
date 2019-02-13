/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.renderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Diego Noble
 */
public class TableRendererColorClaseTratamiento extends DefaultTableCellRenderer {

    private int ColReferencia;

    public TableRendererColorClaseTratamiento(int ColReferencia) {
        this.ColReferencia = ColReferencia;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {

        table.setForeground(Color.black);//color de texto

        String color = (String) table.getValueAt(row, ColReferencia);
        if (color != null) {
            setBackground(Color.decode(color));
            setBorder(new LineBorder(Color.decode(color)));
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        } else {
            setBackground(Color.WHITE);
            setBorder(new LineBorder(Color.WHITE));
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        }
        return this;
    }

}
