/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.tablemodels;

import com.dnsoft.dentista.beans.ClaseTratamiento;
import com.dnsoft.dentista.beans.Proveedor;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diego Noble
 */
public class ProveedorTableModel extends AbstractTableModel {

    //nome da coluna da table
    private final String[] colunas = new String[]{"Nombre", "Celular", "Obs"};
    //lista para a manipulacao do objeto
    private List<Proveedor> listProveedors;

    public ProveedorTableModel() {
        listProveedors = new LinkedList<Proveedor>();
    }

    public ProveedorTableModel(List<Proveedor> listProveedors) {
        this.listProveedors = listProveedors;
    }

    //numero de linhas
    @Override
    public int getRowCount() {
        return listProveedors.size();
    }

    //numero de colunas
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    //define o que cada coluna conterï¿½ do objeto
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proveedor c = listProveedors.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getNombre();
            case 1:
                return c.getCelular();
            case 2:
                return c.getObservaciones();

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
            case 2:
                return String.class;

            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex
    ) {

        return false;

    }

    public void agregar(Proveedor Proveedors) {
        listProveedors.add(Proveedors);

        this.fireTableRowsInserted(listProveedors.size() - 1, listProveedors.size() - 1);
    }

    public void eliminar(int row) {
        listProveedors.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    public void atualizar(int row, Proveedor Proveedors) {
        listProveedors.set(row, Proveedors);
        this.fireTableRowsUpdated(row, row);
    }

    public Proveedor getCliente(int row) {
        return listProveedors.get(row);
    }

}
