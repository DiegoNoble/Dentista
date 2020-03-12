/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.tablemodels;

import com.dnsoft.dentista.beans.ClaseTratamiento;
import com.dnsoft.dentista.beans.Paciente;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diego Noble
 */
public class PacienteTableModel extends AbstractTableModel {

    //nome da coluna da table
    private final String[] colunas = new String[]{"id","Nombre", "Celular", "Tipo", "Color"};
    //lista para a manipulacao do objeto
    private List<Paciente> listPacientes;

    public PacienteTableModel() {
        listPacientes = new LinkedList<Paciente>();
    }

    public PacienteTableModel(List<Paciente> listPacientes) {
        this.listPacientes = listPacientes;
    }

    //numero de linhas
    @Override
    public int getRowCount() {
        return listPacientes.size();
    }

    //numero de colunas
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    //define o que cada coluna conterï¿½ do objeto
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Paciente c = listPacientes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getId();
            case 1:
                return c.getNombre();
            case 2:
                return c.getCelular();
            case 3:
                return c.getClaseTratamiento();
            case 4:
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
                return Long.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return ClaseTratamiento.class;
            case 4:
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

    public void agregar(Paciente Pacientes) {
        listPacientes.add(Pacientes);

        this.fireTableRowsInserted(listPacientes.size() - 1, listPacientes.size() - 1);
    }

    public void eliminar(int row) {
        listPacientes.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    public void atualizar(int row, Paciente Pacientes) {
        listPacientes.set(row, Pacientes);
        this.fireTableRowsUpdated(row, row);
    }

    public Paciente getCliente(int row) {
        return listPacientes.get(row);
    }

}
