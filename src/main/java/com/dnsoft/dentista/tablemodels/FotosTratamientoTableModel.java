/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.tablemodels;

import com.dnsoft.dentista.beans.FotosTratamiento;
import java.time.LocalDate;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diego Noble
 */
public class FotosTratamientoTableModel extends AbstractTableModel {

    //nome da coluna da table
    private final String[] colunas = new String[]{"Fecha", "Comentarios"};
    //lista para a manipulacao do objeto
    private List<FotosTratamiento> list;

    public FotosTratamientoTableModel(List<FotosTratamiento> list) {
        this.list = list;
    }

    public FotosTratamientoTableModel() {
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
        FotosTratamiento c = list.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return c.getFecha();
            case 1:
                return c.getComentarios();
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
                return LocalDate.class;
            case 1:
                return String.class;

            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void agregar(FotosTratamiento FotosTratamientos) {
        list.add(FotosTratamientos);

        this.fireTableRowsInserted(list.size() - 1, list.size() - 1);

    }

    public void agregar(List<FotosTratamiento> listFotosTratamientos) {
        list.clear();
        list.addAll(listFotosTratamientos);

        this.fireTableRowsInserted(list.size() - 1, list.size() - 1);

    }

    public void eliminar(int row) {
        list.remove(row);
        this.fireTableRowsDeleted(row, row);

    }

    public void atualizar(int row, FotosTratamiento FotosTratamientos) {
        list.set(row, FotosTratamientos);
        this.fireTableRowsUpdated(row, row);

    }

    public FotosTratamiento getCliente(int row) {
        return list.get(row);
    }

}
