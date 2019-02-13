/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.renderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

public class TabelaTextAreaRenderer extends JTextArea implements TableCellRenderer {
    // This method is called each time a cell in a column
    // using this renderer needs to be rendered.

    public TabelaTextAreaRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);

    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {
        // 'value' is value contained in the cell located at
        // (rowIndex, vColIndex)

        if (isSelected) {
            setBackground((Color) UIManager.get("Table.selectionBackground"));
            setForeground((Color) UIManager.get("Table.selectionForeground"));
            setBorder(BorderFactory.createEmptyBorder());
        } else {
            setForeground((Color) UIManager.get("Table.foreground"));
            setBackground((Color) UIManager.get("Table.background"));
            setBorder(BorderFactory.createEmptyBorder());
        }
        if (hasFocus) {
            // this cell is the anchor and the table has the focus
        }
        // Configure the component with the specified value
        setText(value.toString());
        // Set tool tip if desired
        setToolTipText((String) value);
        // Since the renderer is a component, return itself
        return this;
    }
}
