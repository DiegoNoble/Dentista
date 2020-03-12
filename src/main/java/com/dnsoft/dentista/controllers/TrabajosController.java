/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.controllers;

import com.dnsoft.dentista.beans.Trabajos;
import com.dnsoft.dentista.daos.ITrabajosDAO;
import com.dnsoft.dentista.renderers.TableRendererColorClaseTratamiento;
import com.dnsoft.dentista.tablemodels.TrabajosTableModel;
import com.dnsoft.dentista.utiles.Container;
import com.dnsoft.dentista.utiles.ControlarEntradaTexto;
import com.dnsoft.dentista.utiles.ExportarDatosExcel;
import com.dnsoft.dentista.views.TrabajosDetalles;
import com.dnsoft.dentista.views.TrabajosView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author Diego Noble
 */
public class TrabajosController implements ActionListener {

    Container container;
    public TrabajosView view;
    TrabajosTableModel tableModel;
    ListSelectionModel listModel;
    List<Trabajos> list;
    ITrabajosDAO DAO;
    Trabajos seleccionado;
    JDesktopPane desktopPane;

    public TrabajosController(TrabajosView view, JDesktopPane desktopPane) {

        this.container = Container.getInstancia();
        DAO = container.getBean(ITrabajosDAO.class);
        this.desktopPane = desktopPane;
        this.view = view;
        inicia();
    }

    public TrabajosController(TrabajosView view) {

        this.container = Container.getInstancia();
        DAO = container.getBean(ITrabajosDAO.class);

        this.view = view;
        this.view.btnExcel.setVisible(false);

        inicia();
    }

    private void inicia() {
        Character chs[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', '-'};
        view.txtPorcentaje.setDocument(new ControlarEntradaTexto(10, chs));

        this.view.txtBusqueda.setActionCommand("txtBusqueda");
        this.view.txtBusqueda.addActionListener(this);
        PromptSupport.setPrompt("Buscar por nombre o clase tratamiento", view.txtBusqueda);
        tblModel();
        accionesBotones();
    }

    public void go() {
        this.view.setVisible(true);
        this.view.toFront();

    }

    void accionesBotones() {

        view.btnNuevo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                TrabajosDetalles nuevoTrabajo = new TrabajosDetalles(null, true);
                nuevoTrabajo.setVisible(true);
                nuevoTrabajo.toFront();
                actualizaTbl();
            }
        });

        view.btnModificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                editaSeleccionado();

            }
        });
        view.btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                view.dispose();

            }
        });
        view.btnExcel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
                    ExportarDatosExcel exportar = new ExportarDatosExcel(view.tbl, "Listado de precios");
                    exportar.exportarJTableExcel();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Error al exportar datos " + ex, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int c = JOptionPane.showConfirmDialog(view, "Confirma eliminación del trabajo? " + seleccionado.toString(), "Confirmación", JOptionPane.YES_NO_OPTION);
                if (c == JOptionPane.YES_OPTION) {
                    try {
                        DAO.delete(seleccionado);
                        actualizaTbl();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "No es posible eliminar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        view.btnAjustar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                ajustarPrecios();
            }
        });

    }

    void tblModel() {

        ((DefaultTableCellRenderer) view.tbl.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        list = new ArrayList<Trabajos>();
        list.addAll(DAO.findAll());

        tableModel = new TrabajosTableModel(list);
        view.tbl.setModel(tableModel);
        view.tbl.setDefaultRenderer(Object.class, new TableRendererColorClaseTratamiento(3));

        int[] anchos = {60, 100, 30, 1};

        for (int i = 0; i < view.tbl.getColumnCount(); i++) {

            view.tbl.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }

        view.tbl.getColumn("Color").setMaxWidth(0);
        view.tbl.getColumn("Color").setMinWidth(0);
        view.tbl.getColumn("Color").setPreferredWidth(0);
        view.tbl.getColumn("Color").setWidth(0);

        view.tbl.setRowHeight(25);
        view.tbl.setAutoCreateRowSorter(true);

        listModel = view.tbl.getSelectionModel();
        listModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (view.tbl.getSelectedRow() != -1) {

                    int viewRow = view.tbl.getSelectedRow();
                    int modelRow = view.tbl.convertRowIndexToModel(viewRow);

                    seleccionado = list.get(modelRow);
                    view.btnModificar.setEnabled(true);
                    view.btnEliminar.setEnabled(true);
                } else {
                    view.btnModificar.setEnabled(false);
                    view.btnEliminar.setEnabled(false);
                }
            }
        });

        view.tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {

                if (me.getClickCount() == 2) {
                    editaSeleccionado();
                }
            }
        });

    }

    void ajustarPrecios() {

        Double porcentaje = Double.valueOf(view.txtPorcentaje.getText());
        List<Trabajos> trabajos = DAO.findAll();
        for (Trabajos trabajo : trabajos) {
            Double nuevo_precio = trabajo.getValor() + ((trabajo.getValor() * porcentaje) / 100);
            BigDecimal nuevo = BigDecimal.valueOf(nuevo_precio);
            nuevo = nuevo.setScale(0, RoundingMode.CEILING);
            trabajo.setValor(nuevo.doubleValue());
        }

        try {
            DAO.save(trabajos);
            JOptionPane.showMessageDialog(null, "Precios ajustados correctamente!", "Correcto", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ajustar precios " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);

        }

        actualizaTbl();
    }

    void editaSeleccionado() {
        //inquilinoSeleccionado = listInquilinos.get(view.tbl.getSelectedRow());
        TrabajosDetalles editaInquilino = new TrabajosDetalles(null, true, seleccionado);
        editaInquilino.setVisible(true);
        editaInquilino.toFront();
        actualizaTbl();
    }

    public void actualizaTbl() {
        list.clear();
        list.addAll(DAO.findByNombreClase(view.txtBusqueda.getText()));
        tableModel.fireTableDataChanged();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "txtBusqueda":
                list.clear();
                list.addAll(DAO.findByNombreClase(view.txtBusqueda.getText()));
                tableModel.fireTableDataChanged();
                break;
            default:
                throw new AssertionError();
        }
    }

}
