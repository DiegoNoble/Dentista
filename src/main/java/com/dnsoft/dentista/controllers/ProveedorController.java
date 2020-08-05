/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.controllers;

import com.dnsoft.dentista.beans.Proveedor;
import com.dnsoft.dentista.daos.IProveedorDAO;
import com.dnsoft.dentista.renderers.TableRendererColorClaseTratamiento;
import com.dnsoft.dentista.tablemodels.ProveedorTableModel;
import com.dnsoft.dentista.utiles.Container;
import com.dnsoft.dentista.utiles.ExportarDatosExcel;
import com.dnsoft.dentista.views.ProveedorDetalles;
import com.dnsoft.dentista.views.ProveedorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class ProveedorController implements ActionListener {

    Container container;
    public ProveedorView view;
    ProveedorTableModel tableModel;
    ListSelectionModel listModel;
    List<Proveedor> list;
    IProveedorDAO DAO;
    Proveedor seleccionado;
    JDesktopPane desktopPane;

    public ProveedorController(ProveedorView view, JDesktopPane desktopPane) {

        this.container = Container.getInstancia();
        DAO = container.getBean(IProveedorDAO.class);
        this.desktopPane = desktopPane;
        this.view = view;
        inicia();
    }

    public ProveedorController(ProveedorView view) {

        this.container = Container.getInstancia();
        DAO = container.getBean(IProveedorDAO.class);

        this.view = view;
        this.view.btnExcel.setVisible(false);

        inicia();
    }

    private void inicia() {
        
        this.view.txtBusqueda.setActionCommand("txtBusqueda");
        this.view.txtBusqueda.addActionListener(this);
        PromptSupport.setPrompt("Buscar por nombre", view.txtBusqueda);
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
                ProveedorDetalles nuevoInquilino = new ProveedorDetalles(null, true);
                nuevoInquilino.setVisible(true);
                nuevoInquilino.toFront();
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
                    ExportarDatosExcel exportar = new ExportarDatosExcel(view.tbl, "Proveedores");
                    exportar.exportarJTableExcel();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Error al exportar datos " + ex, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int c = JOptionPane.showConfirmDialog(view, "Confirma eliminación del proveedor: " + seleccionado.toString(), "Confirmación", JOptionPane.YES_NO_OPTION);
                if (c == 0) {
                    try {
                        DAO.delete(seleccionado);
                        actualizaTbl();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "No es posible eliminar el proveedor", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
    }

    void tblModel() {

        ((DefaultTableCellRenderer) view.tbl.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        list = new ArrayList<Proveedor>();
        list.addAll(DAO.findAll());

        tableModel = new ProveedorTableModel(list);
        view.tbl.setModel(tableModel);

        int[] anchos = {100, 30, 300};

        for (int i = 0; i < view.tbl.getColumnCount(); i++) {

            view.tbl.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }

      

        view.tbl.setRowHeight(25);

        listModel = view.tbl.getSelectionModel();
        listModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (view.tbl.getSelectedRow() != -1) {
                    seleccionado = list.get(view.tbl.getSelectedRow());
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

    void editaSeleccionado() {
        //inquilinoSeleccionado = listInquilinos.get(view.tbl.getSelectedRow());
        ProveedorDetalles editaInquilino = new ProveedorDetalles(null, true, seleccionado);
        editaInquilino.setVisible(true);
        editaInquilino.toFront();
        actualizaTbl();
    }

    public void actualizaTbl() {
        list.clear();
        list.addAll(DAO.findByNombreLike(view.txtBusqueda.getText()));
        tableModel.fireTableDataChanged();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "txtBusqueda":
                list.clear();
                list.addAll(DAO.findByNombreLike(view.txtBusqueda.getText()));
                tableModel.fireTableDataChanged();
                break;
            default:
                throw new AssertionError();
        }
    }

}
