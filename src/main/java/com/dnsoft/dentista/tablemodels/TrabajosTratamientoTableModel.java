/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.tablemodels;

import com.dnsoft.dentista.beans.TrabajoTratamientoEnum;
import com.dnsoft.dentista.beans.TrabajosTratamiento;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diego Noble
 */
public class TrabajosTratamientoTableModel extends AbstractTableModel {

    //nome da coluna da table
    private final String[] colunas = new String[]{"Pieza", "Nombre", "Valor", "Situación", "Fecha débito"};
    //lista para a manipulacao do objeto
    private List<TrabajosTratamiento> list;
    JTextField txtTotal;

    public TrabajosTratamientoTableModel(List<TrabajosTratamiento> list, JTextField txtTotal) {
        this.txtTotal = txtTotal;
        this.list = list;
    }

    public TrabajosTratamientoTableModel(List<TrabajosTratamiento> list) {
        this.list = list;
    }

    public TrabajosTratamientoTableModel() {
    }

    //numero de linhas
    @Override
    public int getRowCount() {
        return list.size();
    }

    //numero de colunas
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    //define o que cada coluna conterï¿½ do objeto
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TrabajosTratamiento c = list.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return c.getPieza();
            case 1:
                return c.getTrabajos();
            case 2:
                return c.getValor();
            case 3:
                return c.getTrabajoTratamientoEnum().toString();
            case 4:
                return c.getFechaDebito();
            default:
                return null;
        }
    }

    //determina o nome das colunas
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    //determina que tipo de objeto cada coluna irï¿½ suportar
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Double.class;
            case 3:
                return TrabajoTratamientoEnum.class;
            case 4:
                return LocalDate.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
              return false;
    }

    public void agregar(TrabajosTratamiento TrabajosTratamientos) {
        list.add(TrabajosTratamientos);

        this.fireTableRowsInserted(list.size() - 1, list.size() - 1);
        if (txtTotal != null) {
            CalculaTotalTratamiento();
        }

    }

    public void agregar(List<TrabajosTratamiento> listTrabajosTratamientos) {
        list.clear();
        list.addAll(listTrabajosTratamientos);

        this.fireTableRowsInserted(list.size() - 1, list.size() - 1);
        if (txtTotal != null) {
            CalculaTotalTratamiento();
        }
    }

    public void eliminar(int row) {
        list.remove(row);
        this.fireTableRowsDeleted(row, row);
        if (txtTotal != null) {
            CalculaTotalTratamiento();
        }
    }

    public void atualizar(int row, TrabajosTratamiento TrabajosTratamientos) {
        list.set(row, TrabajosTratamientos);
        this.fireTableRowsUpdated(row, row);
        if (txtTotal != null) {
            CalculaTotalTratamiento();
        }
    }

    public TrabajosTratamiento getCliente(int row) {
        return list.get(row);
    }

    public void CalculaTotalTratamiento() {
        Double total = 0.0;
        for (TrabajosTratamiento TrabajosTratamiento : list) {
            if (TrabajosTratamiento.getValor() != null) {
                total = total + TrabajosTratamiento.getValor();
            }
        }
        //BigDecimal to = new BigDecimal

        //DecimalFormat formato = new DecimalFormat("#,##");
        //formato.setRoundingMode(RoundingMode.CEILING);
        txtTotal.setText(String.format("%.2f", total));

    }
}
