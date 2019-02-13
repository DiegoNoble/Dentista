/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.tablemodels;

import com.dnsoft.dentista.beans.TrabajosProveedor;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diego Noble
 */
public class RecordatorioTrabajosProveedorTableModel extends AbstractTableModel {

    //nome da coluna da table
    private final String[] colunas = new String[]{"Fecha programada", "Proveedor", "Paciente", "Fecha solicitud", "Comentarios"};
    //lista para a manipulacao do objeto
    private List<TrabajosProveedor> listClasePacientess;

    public RecordatorioTrabajosProveedorTableModel() {
        listClasePacientess = new LinkedList<TrabajosProveedor>();
    }

    public RecordatorioTrabajosProveedorTableModel(List<TrabajosProveedor> listClasePacientess) {
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
        TrabajosProveedor c = listClasePacientess.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getFechaEntregaProgramada();
            case 1:
                return c.getProveedor();
            case 2:
                return c.getPlanTratamiento().getConsulta();
            case 3:
                return c.getFechaSolicitud();
            case 4:
                return c.getNombre() + ", " + c.getDescripcion();

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
                return LocalDate.class;
            case 1:
                return LocalDate.class;
            case 2:
                return LocalDate.class;
            case 3:
                return String.class;

            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return false;

    }

    public void agregar(TrabajosProveedor ClasePacientess) {
        listClasePacientess.add(ClasePacientess);

        this.fireTableRowsInserted(listClasePacientess.size() - 1, listClasePacientess.size() - 1);
    }

    public void eliminar(int row) {
        listClasePacientess.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    public void atualizar(int row, TrabajosProveedor ClasePacientess) {
        listClasePacientess.set(row, ClasePacientess);
        this.fireTableRowsUpdated(row, row);
    }

    public TrabajosProveedor getCliente(int row) {
        return listClasePacientess.get(row);
    }

}
