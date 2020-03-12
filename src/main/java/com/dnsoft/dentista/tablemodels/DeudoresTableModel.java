/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.tablemodels;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Tuple;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diego Noble
 */
public class DeudoresTableModel extends AbstractTableModel {

    //nome da coluna da table
    private final String[] colunas = new String[]{"Nombre", "Debe", "Haber", "Saldo"};
    //lista para a manipulacao do objeto
    private final List<Tuple> list;

    public DeudoresTableModel() {
        list = new LinkedList<>();
    }

    public DeudoresTableModel(List<Tuple> list) {
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

        Tuple c = list.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return c.get(0);
            case 1:
                return c.get(1);
            case 2:
                return c.get(2);
            case 3:
                return c.get(3);
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
                return String.class;
            case 1:
                return BigDecimal.class;
            case 2:
                return BigDecimal.class;
            case 3:
                return BigDecimal.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return false;
    }

    public void agregar(Tuple gastoInmueblePaciente) {
        list.add(gastoInmueblePaciente);

        this.fireTableRowsInserted(list.size() - 1, list.size() - 1);
    }

    public void eliminar(int row) {
        list.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    public void atualizar(int row, Tuple gastoInmueblePaciente) {
        list.set(row, gastoInmueblePaciente);
        this.fireTableRowsUpdated(row, row);
    }

    public Tuple getCliente(int row) {
        return list.get(row);
    }

}
