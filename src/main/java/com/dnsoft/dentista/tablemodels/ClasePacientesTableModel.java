/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.tablemodels;

import com.dnsoft.dentista.beans.ClaseTratamiento;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diego Noble
 */
public class ClasePacientesTableModel extends AbstractTableModel {

    //nome da coluna da table
    private final String[] colunas = new String[]{"Nombre", "Color"};
    //lista para a manipulacao do objeto
    private List<ClaseTratamiento> listClasePacientess;

    public ClasePacientesTableModel() {
        listClasePacientess = new LinkedList<ClaseTratamiento>();
    }

    public ClasePacientesTableModel(List<ClaseTratamiento> listClasePacientess) {
        this.listClasePacientess = listClasePacientess;
    }

    //numero de linhas
    @Override
    public int getRowCount() {
        return listClasePacientess.size();
    }

    //numero de colunas
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    //define o que cada coluna conterï¿½ do objeto
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ClaseTratamiento c = listClasePacientess.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getNombre();
            case 1:
                return c.getColor();

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
                return String.class;

            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex
    ) {

        return true;

    }

    public void agregar(ClaseTratamiento ClasePacientess) {
        listClasePacientess.add(ClasePacientess);

        this.fireTableRowsInserted(listClasePacientess.size() - 1, listClasePacientess.size() - 1);
    }

    public void eliminar(int row) {
        listClasePacientess.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    public void atualizar(int row, ClaseTratamiento ClasePacientess) {
        listClasePacientess.set(row, ClasePacientess);
        this.fireTableRowsUpdated(row, row);
    }

    public ClaseTratamiento getCliente(int row) {
        return listClasePacientess.get(row);
    }

}
