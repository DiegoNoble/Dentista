/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.controllers;

import com.dnsoft.dentista.beans.Caja;
import com.dnsoft.dentista.beans.MonedaEnum;
import com.dnsoft.dentista.beans.Parametros;
import com.dnsoft.dentista.beans.Proveedor;
import com.dnsoft.dentista.beans.Rubro;
import com.dnsoft.dentista.daos.ICajaDAO;
import com.dnsoft.dentista.daos.IParametrosDAO;
import com.dnsoft.dentista.daos.IRubrosDAO;
import com.dnsoft.dentista.renderers.LocalDateCellRenderer;
import com.dnsoft.dentista.tablemodels.CajaTableModel;
import com.dnsoft.dentista.utiles.Container;
import com.dnsoft.dentista.views.ControlDeCajaView;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Diego Noble
 */
public class ControlDeCajaController implements ActionListener {

    Container container;
    ControlDeCajaView view;
    CajaTableModel tableModel;
    ListSelectionModel listModel;
    List<Caja> listMovimientos;
    ICajaDAO cajaDAO;
    IRubrosDAO ruboDAO;
    IParametrosDAO parametrosDAO;
    Parametros parametros;
    Caja movimientoSeleccionado;
    JDesktopPane desktopPane;

    public ControlDeCajaController(ControlDeCajaView view, JDesktopPane desktopPane) {

        this.container = Container.getInstancia();
        this.desktopPane = desktopPane;
        this.view = view;
        this.
                inicia();
    }

    private void inicia() {

        Dimension d = this.desktopPane.getSize();
        this.view.setLocation((d.width - this.view.getSize().width) / 2, (d.height - this.view.getSize().height) / 2);

        accionesBotones();

        this.view.dpFecha.setDate(LocalDate.now());

       // this.view.dpFecha.setEnabled(false);
        cajaDAO = container.getBean(ICajaDAO.class);
        parametrosDAO = container.getBean(IParametrosDAO.class);
        ruboDAO = container.getBean(IRubrosDAO.class);
        parametros = parametrosDAO.findAll().get(0);

        TableModel();

        cargaMonedas();
        cargaRubros();

        saldos();
        actualizaTbl();
    }

    public void go() {
        /*
        view.btnOtrosGastosTerceros.setActionCommand("btnOtrosGastosTerceros");
        view.btnOtrosGastosTerceros.addActionListener(this);*/

        this.view.setVisible(true);
        this.view.toFront();

    }

    void cargaRubros() {
        AutoCompleteDecorator.decorate(view.cbRubro);
        view.cbRubro.removeAllItems();
        for (Rubro rubro : ruboDAO.findAll()) {
            view.cbRubro.addItem(rubro);
        }

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

    void accionesBotones() {

        view.btnEntradas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Caja movCaja = new Caja();
                movCaja.setDescripcion(view.txtConcepto.getText());
                movCaja.setFecha(view.dpFecha.getDate());
                movCaja.setMoneda((MonedaEnum) view.cbMoneda.getSelectedItem());
                movCaja.setRubro((Rubro) view.cbRubro.getSelectedItem());
                if (view.rbEntrada.isSelected()) {
                    movCaja.setEntrada(new BigDecimal(Double.valueOf(view.txtValor.getText())));
                    movCaja.setSalida(new BigDecimal(0.0));
                }
                if (view.rbSalida.isSelected()) {
                    movCaja.setSalida(new BigDecimal(Double.valueOf(view.txtValor.getText())));
                    movCaja.setEntrada(new BigDecimal(0.0));
                }

                cajaDAO.save(movCaja);

                actualizaTbl();
            }
        });

    }

    final void cargaMonedas() {
        view.cbMoneda.addItem(MonedaEnum.PESOS);
        view.cbMoneda.addItem(MonedaEnum.DOLARES);
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
        listMovimientos.addAll(cajaDAO.findByFechaAfterOrFechaEqualAndMonedaOrderByFechaDesc(view.dpFecha.getDate(),
                (MonedaEnum) view.cbMoneda.getSelectedItem()));

        tableModel = new CajaTableModel(listMovimientos);
        view.tblMovimientos.setModel(tableModel);
        view.tblMovimientos.getColumn("Fecha").setCellRenderer(new LocalDateCellRenderer());
        view.tblMovimientos.setRowHeight(25);
        int[] anchos = {50, 100, 50, 20, 20};

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

    /*void detalles() {

        movimientoSeleccionado = listMovimientos.get(view.tblMovimientos.getSelectedRow());
        DetalleMovimiento detalle = new DetalleMovimiento(null, true, movimientoSeleccionado);
        detalle.setVisible(true);
        detalle.toFront();

    }*/
    public void actualizaTbl() {
        listMovimientos.clear();
        listMovimientos.addAll(cajaDAO.findByFechaAfterOrFechaEqualAndMonedaOrderByFechaDesc(view.dpFecha.getDate(),
                (MonedaEnum) view.cbMoneda.getSelectedItem()));
        tableModel.fireTableDataChanged();
       saldos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {


            /*case "btnOtrosGastosTerceros":
                OtrosGastosTerceros otrosGastosTerceros = new OtrosGastosTerceros(null, true, parametros.getOtrosGastosTerceros(), this, (TipoDeCaja) view.cbTipoDeCaja.getSelectedItem());
                otrosGastosTerceros.setVisible(true);
                otrosGastosTerceros.toFront();
                break;*/
            default:
                throw new AssertionError();
        }
    }

}
