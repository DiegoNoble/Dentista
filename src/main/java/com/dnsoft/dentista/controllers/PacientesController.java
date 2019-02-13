/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.controllers;

import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.daos.IPacienteDAO;
import com.dnsoft.dentista.renderers.TableRendererColorClaseTratamiento;
import com.dnsoft.dentista.tablemodels.PacienteTableModel;
import com.dnsoft.dentista.utiles.Container;
import com.dnsoft.dentista.views.AgendaView;
import com.dnsoft.dentista.views.ConsultaPlanesTratamientoView;
import com.dnsoft.dentista.views.DetalleMovimientosCuentaPaciente;
import com.dnsoft.dentista.views.HistoriaConsultasView;
import com.dnsoft.dentista.views.Odontograma;
import com.dnsoft.dentista.views.PacienteDetalles;
import com.dnsoft.dentista.views.PacientesView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import org.jdesktop.swingx.prompt.PromptSupport;
import org.springframework.stereotype.Component;

/**
 *
 * @author Diego Noble
 */
@Component
public class PacientesController implements ActionListener {

    Container container;
    public PacientesView view;
    PacienteTableModel tableModel;
    ListSelectionModel listModel;
    List<Paciente> list;
    IPacienteDAO DAO;
    Paciente seleccionado;
    JDesktopPane desktopPane;

    public PacientesController() {
    }

    public PacientesController(PacientesView view, JDesktopPane desktopPane) {

        this.container = Container.getInstancia();
        DAO = container.getBean(IPacienteDAO.class);
        this.desktopPane = desktopPane;
        this.view = view;
        inicia();
    }

    public PacientesController(PacientesView view) {

        this.container = Container.getInstancia();
        DAO = container.getBean(IPacienteDAO.class);

        this.view = view;

        inicia();
    }

    private void inicia() {
        view.btnEnviarSMS.setVisible(false);
        view.btnAnamnesis.setVisible(false);
        view.btnTrabajosLaboratorio.setVisible(false);
        view.btnImagenes.setVisible(false);
        this.view.txtBusqueda.setActionCommand("txtBusqueda");
        this.view.txtBusqueda.addActionListener(this);
        PromptSupport.setPrompt("Buscar por nombre, apellido o documento", view.txtBusqueda);
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
                PacienteDetalles nuevoInquilino = new PacienteDetalles(null, true);
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
        view.btnCuentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DetalleMovimientosCuentaPaciente cCPacienteView = new DetalleMovimientosCuentaPaciente(null, true);
                DetalleCuentaPacientesController controller = new DetalleCuentaPacientesController(cCPacienteView, seleccionado);
                controller.go();
            }
        });

        view.btnTratamientos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsultaPlanesTratamientoView consultaPlanesTratamientoView = new ConsultaPlanesTratamientoView(seleccionado);
                consultaPlanesTratamientoView.setVisible(true);
                consultaPlanesTratamientoView.toFront();

            }
        });
        view.btnOdontograma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Odontograma odontograma = new Odontograma();
                odontograma.toFront();
                odontograma.setVisible(true);
            }
        });
        view.btnHistoriaConsultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistoriaConsultasView consultasView = new HistoriaConsultasView(seleccionado);

                consultasView.setVisible(true);
                consultasView.toFront();
            }
        });
        view.btnProximaConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgendaView agenda = new AgendaView(seleccionado);
                desktopPane.add(agenda);
                agenda.setVisible(true);
                agenda.toFront();

            }
        });
        view.btnModificarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PacienteDetalles detalles = new PacienteDetalles(null, true, seleccionado);
                detalles.toFront();
                detalles.setVisible(true);

            }
        });
    }

    void tblModel() {

        ((DefaultTableCellRenderer) view.tbl.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        list = new ArrayList<Paciente>();
        list.addAll(DAO.findAll());

        tableModel = new PacienteTableModel(list);
        view.tbl.setModel(tableModel);
        view.tbl.setDefaultRenderer(Object.class, new TableRendererColorClaseTratamiento(3));

        int[] anchos = {200, 30, 30, 30};

        for (int i = 0; i < view.tbl.getColumnCount(); i++) {

            view.tbl.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }

        view.tbl.getColumn("Color").setMaxWidth(0);
        view.tbl.getColumn("Color").setMinWidth(0);
        view.tbl.getColumn("Color").setPreferredWidth(0);
        view.tbl.getColumn("Color").setWidth(0);

        view.tbl.setRowHeight(25);

        listModel = view.tbl.getSelectionModel();
        listModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (view.tbl.getSelectedRow() != -1) {
                    seleccionado = list.get(view.tbl.getSelectedRow());
                    view.btnModificar.setEnabled(true);
                } else {
                    view.btnModificar.setEnabled(false);
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
        PacienteDetalles editaInquilino = new PacienteDetalles(null, true, seleccionado);
        editaInquilino.setVisible(true);
        editaInquilino.toFront();
        actualizaTbl();
    }

    public void actualizaTbl() {
        list.clear();
        list.addAll(DAO.findByNombreDocumento(view.txtBusqueda.getText()));
        tableModel.fireTableDataChanged();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "txtBusqueda":
                list.clear();
                list.addAll(DAO.findByNombreDocumento(view.txtBusqueda.getText()));
                tableModel.fireTableDataChanged();
                break;
            default:
                throw new AssertionError();
        }
    }

}
