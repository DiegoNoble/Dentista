/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.tablemodels;

import com.dnsoft.dentista.beans.PlanTratamiento;
import com.dnsoft.dentista.beans.SituacionPlanTratamientoEnum;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diego Noble
 */
public class PlanTratamientoTableModel extends AbstractTableModel {

    //nome da coluna da table
    private final String[] colunas = new String[]{"Fecha", "Paciente", "Fecha confirmación", "Fecha finalizado", "Valor", "Obs.", "Situación"};
    //lista para a manipulacao do objeto
    private List<PlanTratamiento> listPlanTratamientos;

    public PlanTratamientoTableModel() {
        listPlanTratamientos = new LinkedList<PlanTratamiento>();
    }

    public PlanTratamientoTableModel(List<PlanTratamiento> listPlanTratamientos) {
        this.listPlanTratamientos = listPlanTratamientos;
    }

    //numero de linhas
    @Override
    public int getRowCount() {
        return listPlanTratamientos.size();
    }

    //numero de colunas
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    //define o que cada coluna conterï¿½ do objeto
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PlanTratamiento c = listPlanTratamientos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getFechaCreacion();
            case 1:
                return c.getPaciente();
            case 2:
                return c.getFechaConfirmacion();
            case 3:
                return c.getFechaFinalizado();
            case 4:
                return c.getMoneda().toString() + " " + c.getValorTotal();
            case 5:
                return c.getObservaciones();
            case 6:
                return c.getSituacionPlanTratamientoEnum();
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
                return String.class;
            case 2:
                return LocalDate.class;
            case 3:
                return LocalDate.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return SituacionPlanTratamientoEnum.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex
    ) {

        return false;

    }

    public void agregar(PlanTratamiento PlanTratamientos) {
        listPlanTratamientos.add(PlanTratamientos);

        this.fireTableRowsInserted(listPlanTratamientos.size() - 1, listPlanTratamientos.size() - 1);
    }

    public void eliminar(int row) {
        listPlanTratamientos.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    public void atualizar(int row, PlanTratamiento PlanTratamientos) {
        listPlanTratamientos.set(row, PlanTratamientos);
        this.fireTableRowsUpdated(row, row);
    }

    public PlanTratamiento getCliente(int row) {
        return listPlanTratamientos.get(row);
    }

}
