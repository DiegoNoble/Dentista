/**
 * MyDateCellRenderer.java
 *
 * $Id$
 *
 */
package com.dnsoft.dentista.renderers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Dyego Souza do Carmo
 * @since
 */
public class LocalDateCellRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;

    public LocalDateCellRenderer() {
        super();
        setHorizontalAlignment(CENTER);

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
