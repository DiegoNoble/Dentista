/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.tablemodels;

import com.dnsoft.dentista.beans.Consulta;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diego Noble
 */
public class HistoriaConsultasTableModel extends AbstractTableModel {

    //nome da coluna da table
    private final String[] colunas = new String[]{"Fecha", "Hora", "Comentarios", "Situación"};
    //lista para a manipulacao do objeto
    private List<Consulta> listConsultas;
    //ArrayList<LocalTime> horarios;

    public HistoriaConsultasTableModel() {
        listConsultas = new LinkedList<Consulta>();
    }

    public HistoriaConsultasTableModel(List<Consulta> listConsultas) {
        this.listConsultas = listConsultas;
        /*this.horarios = horarios;
        if (listConsultas.isEmpty()) {
            for (LocalTime horario : horarios) {
                listConsultas.add(new Consulta(horario.toString()));
            }
        } else {
            for (int i = 0; i < 48; i++) {
                Consulta c = listConsultas.get(i);
                LocalTime h = horarios.get(i);
                if (!c.getHora().equals(h.toString())) {
                    listConsultas.add(new Consulta(h.toString()));
                }
            }
        }*/
    }

    //numero de linhas
    @Override
    public int getRowCount() {
        return listConsultas.size();
    }

    //numero de colunas
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    //define o que cada coluna conterï¿½ do objeto
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Consulta c = listConsultas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getFechaConsulta();
            case 1:
                return c.getHora_desde() + " - " + c.getHora_hasta();
            case 2:
                return c.getComentario();
            case 3:
                return c.getSituacionConsulta();
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
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return LocalDate.class;
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
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return false;

    }

    public void agregar(Consulta ClasePacientess) {
        listConsultas.add(ClasePacientess);

        this.fireTableRowsInserted(listConsultas.size() - 1, listConsultas.size() - 1);
    }

    public void eliminar(int row) {
        listConsultas.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    public void atualizar(int row, Consulta ClasePacientess) {
        listConsultas.set(row, ClasePacientess);
        this.fireTableRowsUpdated(row, row);
    }

    public Consulta getCliente(int row) {
        return listConsultas.get(row);
    }

}
