/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.tablemodels;

import com.dnsoft.dentista.beans.CuentaPaciente;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diego Noble
 */
public class CuentaPacienteTableModel extends AbstractTableModel {

    //nome da coluna da table
    private final String[] colunas = new String[]{"Fecha", "Debe", "Paga", "Saldo", "OBS."};
    //lista para a manipulacao do objeto
    private final List<CuentaPaciente> list;

    public CuentaPacienteTableModel() {
        list = new LinkedList<>();
    }

    public CuentaPacienteTableModel(List<CuentaPaciente> list) {
        this.list = list;
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
        CuentaPaciente c = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getFechaMovimiento();
            case 1:
                return c.getDebe();
            case 2:
                return c.getHaber();
            case 3:
                return c.getSaldo();
            case 4:
                return c.getObservacion();
            default:
                return null;
        }

    }

    //determina o nome das colunas
    @Override
    public String getColumnName(int column
    ) {
        return colunas[column];
    }

    //determina que tipo de objeto cada coluna irï¿½ suportar
    @Override
    public Class<?> getColumnClass(int columnIndex
    ) {
        switch (columnIndex) {
            case 0:
                return Date.class;
            case 1:
                return BigDecimal.class;
            case 2:
                return BigDecimal.class;
            case 3:
                return BigDecimal.class;
            case 4:
                return String.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return false;
    }

    public void agregar(CuentaPaciente gastoInmueblePaciente) {
        list.add(gastoInmueblePaciente);

        this.fireTableRowsInserted(list.size() - 1, list.size() - 1);
    }

    public void eliminar(int row) {
        list.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    public void atualizar(int row, CuentaPaciente gastoInmueblePaciente) {
        list.set(row, gastoInmueblePaciente);
        this.fireTableRowsUpdated(row, row);
    }

    public CuentaPaciente getCliente(int row) {
        return list.get(row);
    }

}
