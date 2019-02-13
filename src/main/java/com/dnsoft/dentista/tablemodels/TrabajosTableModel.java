/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.tablemodels;

import com.dnsoft.dentista.beans.ClaseTratamiento;
import com.dnsoft.dentista.beans.Trabajos;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diego Noble
 */
public class TrabajosTableModel extends AbstractTableModel {

    //nome da coluna da table
    private final String[] colunas = new String[]{"Tipo", "Nombre", "Valor", "Color"};
    //lista para a manipulacao do objeto
    private List<Trabajos> listTrabajoss;

    public TrabajosTableModel() {
        listTrabajoss = new LinkedList<Trabajos>();
    }

    public TrabajosTableModel(List<Trabajos> listTrabajoss) {
        this.listTrabajoss = listTrabajoss;
    }

    //numero de linhas
    @Override
    public int getRowCount() {
        return listTrabajoss.size();
    }

    //numero de colunas
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    //define o que cada coluna conterï¿½ do objeto
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Trabajos c = listTrabajoss.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getClaseTratamiento();
            case 1:
                return c.getNombre();
            case 2:
                return c.getMoneda() + " " + c.getValor();
            case 3:
                return c.getClaseTratamiento().getColor();
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
                return ClaseTratamiento.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
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

    public void agregar(Trabajos Trabajoss) {
        listTrabajoss.add(Trabajoss);

        this.fireTableRowsInserted(listTrabajoss.size() - 1, listTrabajoss.size() - 1);
    }

    public void eliminar(int row) {
        listTrabajoss.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    public void atualizar(int row, Trabajos Trabajoss) {
        listTrabajoss.set(row, Trabajoss);
        this.fireTableRowsUpdated(row, row);
    }

    public Trabajos getCliente(int row) {
        return listTrabajoss.get(row);
    }

}
