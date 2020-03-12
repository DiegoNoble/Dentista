/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.controllers;

import com.dnsoft.dentista.beans.Caja;
import com.dnsoft.dentista.beans.MonedaEnum;
import com.dnsoft.dentista.daos.ICajaDAO;
import com.dnsoft.dentista.daos.IRubrosDAO;
import com.dnsoft.dentista.renderers.LocalDateCellRenderer;
import com.dnsoft.dentista.tablemodels.CajaTableModel;
import com.dnsoft.dentista.utiles.Container;
import com.dnsoft.dentista.utiles.ExportarDatosExcel;
import com.dnsoft.dentista.views.ConsultaDeCajaView;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Diego Noble
 */
public class ConsultaDeCajaController implements ActionListener {

    Container container;
    ConsultaDeCajaView view;
    CajaTableModel tableModel;
    ListSelectionModel listModel;
    List<Caja> listMovimientos;
    ICajaDAO cajaDAO;
    IRubrosDAO ruboDAO;
    Caja movimientoSeleccionado;
    JDesktopPane desktopPane;

    public ConsultaDeCajaController(ConsultaDeCajaView view, JDesktopPane desktopPane) {

        this.container = Container.getInstancia();
        cajaDAO = container.getBean(ICajaDAO.class);
        ruboDAO = container.getBean(IRubrosDAO.class);
        this.desktopPane = desktopPane;

        this.view = view;

        cargaMonedas();
        inicia();
    }

    void fechas() {

        LocalDate hoy = LocalDate.now();
        LocalDate inicio = hoy.minusDays(hoy.get(ChronoField.DAY_OF_MONTH) - 1);
        this.view.dpFin.setDate(hoy);
        this.view.dpInicio.setDate(inicio);
    }

    private void inicia() {

        fechas();
        TableModel();
        actualizaTbl();
        saldos();
        accionesBotones();
    }

    public void go() {
        this.view.setVisible(true);
        this.view.toFront();

    }

    void accionesBotones() {

        view.btnExcel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
                    ExportarDatosExcel exportar = new ExportarDatosExcel(view.tblMovimientos, "Movimientos de Caja");
                    exportar.exportarJTableExcel();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Error al exportar datos " + ex, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.view.dpInicio.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent event) {
                actualizaTbl();
            }
        });

        this.view.dpFin.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent event) {
                actualizaTbl();
            }
        });
    }

    final void cargaMonedas() {
        view.cbMoneda.setModel(new DefaultComboBoxModel(MonedaEnum.values()));
        view.cbMoneda.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                actualizaTbl();
            }
        });
    }

    private void TableModel() {

        ((DefaultTableCellRenderer) view.tblMovimientos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        listMovimientos = new ArrayList<>();
        tableModel = new CajaTableModel(listMovimientos);
        view.tblMovimientos.setModel(tableModel);
        view.tblMovimientos.getColumn("Fecha").setCellRenderer(new LocalDateCellRenderer());
        view.tblMovimientos.setRowHeight(25);
        int[] anchos = {50, 100, 300, 20, 20, 20};

        for (int i = 0; i < view.tblMovimientos.getColumnCount(); i++) {

            view.tblMovimientos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }

        view.tblMovimientos.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {

                if (me.getClickCount() == 2) {
                    //detalles();

                }
            }
        });

    }

    void saldos() {
        try {
            BigDecimal ingresos = new BigDecimal(0.0);
            BigDecimal egresos = new BigDecimal(0.0);
            for (Caja listMovimiento : listMovimientos) {
                ingresos = ingresos.add(listMovimiento.getEntrada());
                egresos = egresos.add(listMovimiento.getSalida());
            }

            view.txtIngresos.setText(ingresos.toString());
            view.txtSalidas.setText(egresos.toString());
            view.txtSaldo.setText(ingresos.subtract(egresos).toString());
        } catch (Exception e) {
            view.txtIngresos.setText(new BigDecimal(0.0).toString());
            view.txtSalidas.setText(new BigDecimal(0.0).toString());
            view.txtSaldo.setText(new BigDecimal(0.0).toString());
        }
    }

    /*void detalles() {

        movimientoSeleccionado = listMovimientos.get(view.tblMovimientos.getSelectedRow());
        DetalleMovimiento detalle = new DetalleMovimiento(null, true, movimientoSeleccionado);
        detalle.setVisible(true);
        detalle.toFront();

    }*/
    public void actualizaTbl() {
        listMovimientos.clear();
        listMovimientos.addAll(cajaDAO.findByMonedaAndFechaBetweenOrderByFechaDesc((MonedaEnum) view.cbMoneda.getSelectedItem(),
                view.dpInicio.getDate(), view.dpFin.getDate()));
        tableModel.fireTableDataChanged();
        saldos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {

            default:
                throw new AssertionError();
        }
    }

}
