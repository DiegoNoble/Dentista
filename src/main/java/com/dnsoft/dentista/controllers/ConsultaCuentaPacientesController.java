/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.controllers;

import com.dnsoft.dentista.beans.CuentaPaciente;
import com.dnsoft.dentista.beans.MonedaEnum;
import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.beans.Parametros;
import com.dnsoft.dentista.beans.Rubro;
import com.dnsoft.dentista.daos.ICuentaPacienteDAO;
import com.dnsoft.dentista.daos.IPacienteDAO;
import com.dnsoft.dentista.daos.IParametrosDAO;
import com.dnsoft.dentista.tablemodels.PacienteTableModel;
import com.dnsoft.dentista.utiles.Container;
import com.dnsoft.dentista.views.ConsultaCCPacienteView;
import com.dnsoft.dentista.views.DetalleMovimientosCuentaPaciente;
import com.dnsoft.dentista.views.PacienteDetalles;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ConsultaCuentaPacientesController implements ActionListener {

    Container container;
    ControlDeCajaController cajaController;
    Paciente pacienteSeleccionado;
    List<Paciente> listPacientes;
    IPacienteDAO pacienteDAO;
    ICuentaPacienteDAO cPacienteDAO;
    PacienteTableModel tableModelPacientes;
    ConsultaCCPacienteView view;
    Rubro rubro;
    Parametros parametros;
    IParametrosDAO parametrosDAO;

    public ConsultaCuentaPacientesController(ConsultaCCPacienteView view) {

        this.view = view;

        inicio();
    }

   

    public ConsultaCuentaPacientesController(ConsultaCCPacienteView view, Rubro rubro, ControlDeCajaController cajaController) {

        this.view = view;
        this.rubro = rubro;
        this.cajaController = cajaController;
        inicio();

    }

    public void go() {
        try {
            this.view.setVisible(true);
            this.view.toFront();
            view.setMaximum(true);
            verificaResolucionDePantalla();
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ConsultaCuentaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void verificaResolucionDePantalla() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension tamTela = kit.getScreenSize();
        int larg = tamTela.width;
        System.out.println(larg);
        if (larg < 1280) {
            view.tbl.setFont(new java.awt.Font("Tahoma", 0, 10));
            view.tbl.setRowHeight(20);
            System.out.println("Fuent: 10 ");
        } else if (larg >= 1280 && larg < 1600) {
            view.tbl.setFont(new java.awt.Font("Tahoma", 0, 12));
            view.tbl.setRowHeight(23);
            System.out.println("Fuent: 12 ");
        } else if (larg >= 1600) {
            view.tbl.setFont(new java.awt.Font("Tahoma", 0, 14));
            view.tbl.setRowHeight(25);
            System.out.println("Fuent: 14 ");
        }
    }

    void saldos() {

        DecimalFormat formatter = new DecimalFormat("###,###,###.00");
        BigDecimal saldoPesos = new BigDecimal(BigInteger.ZERO);
        BigDecimal saldoDolares = new BigDecimal(BigInteger.ZERO);;

        for (Paciente Paciente : listPacientes) {
            CuentaPaciente ccPesos = cPacienteDAO.findUltimoMovimiento(MonedaEnum.PESOS, Paciente);
            if (ccPesos != null) {
                saldoPesos = saldoPesos.add(ccPesos.getSaldo());
            }
            CuentaPaciente ccDolares = cPacienteDAO.findUltimoMovimiento(MonedaEnum.DOLARES, Paciente);
            if (ccDolares != null) {
                saldoDolares = saldoDolares.add(ccDolares.getSaldo());
            }
        }
        view.txtDolares.setText(formatter.format(saldoDolares));
        view.txtPesos.setText(formatter.format(saldoPesos));

    }

    final void inicio() {
        this.container = Container.getInstancia();
        PromptSupport.setPrompt("Buscar por nombre, apellido o documento", view.txtBusqueda);
        this.view.txtBusqueda.setActionCommand("txtBusqueda");
        this.view.txtBusqueda.addActionListener(this);

        pacienteDAO = container.getBean(IPacienteDAO.class);
        cPacienteDAO = container.getBean(ICuentaPacienteDAO.class);
        parametrosDAO = container.getBean(IParametrosDAO.class);
        parametros = parametrosDAO.findAll().get(0);
        configuratbls();
        buscarPacientes();
        accionesBotones();
        saldos();
    }

    final void configuratbls() {
        ((DefaultTableCellRenderer) view.tbl.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        listPacientes = new ArrayList<>();

        tableModelPacientes = new PacienteTableModel(listPacientes);
        view.tbl.setModel(tableModelPacientes);
        view.tbl.setRowHeight(25);

        ListSelectionModel listModel = view.tbl.getSelectionModel();
        listModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (view.tbl.getSelectedRow() != -1) {
                    view.btnCuenta.setEnabled(true);
                    view.btnPaciente.setEnabled(true);
                } else {
                    view.btnCuenta.setEnabled(false);
                    view.btnPaciente.setEnabled(false);
                }
            }
        });

        view.tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {

                if (me.getClickCount() == 2) {
                    detallesCCPaciente();
                }
            }
        });

    }

    void buscarPacientes() {
        listPacientes.clear();
        listPacientes.addAll(pacienteDAO.findByNombreDocumento(view.txtBusqueda.getText()));
        tableModelPacientes.fireTableDataChanged();
    }

    void detallesCCPaciente() {

        pacienteSeleccionado = listPacientes.get(view.tbl.getSelectedRow());
        DetalleMovimientosCuentaPaciente movimientosCCPaciente = new DetalleMovimientosCuentaPaciente(null, false);
        DetalleCuentaPacientesController controller = new DetalleCuentaPacientesController(movimientosCCPaciente, pacienteSeleccionado);
        controller.go();

    }

    void verPacienteSeleccionado() {
        try {
            pacienteSeleccionado = pacienteDAO.findOne(listPacientes.get(view.tbl.getSelectedRow()).getId());
            PacienteDetalles Paciente = new PacienteDetalles(null, true, pacienteSeleccionado);
            Paciente.setVisible(true);
            Paciente.toFront();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    void accionesBotones() {
        view.botonVolver1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                ConsultaCuentaPacientesController.this.view.dispose();
            }
        });

        view.btnCuenta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {

                detallesCCPaciente();
            }
        });

        view.btnPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {

                verPacienteSeleccionado();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e
    ) {
        String comando = e.getActionCommand();

        switch (comando) {

            case "txtBusqueda":
                buscarPacientes();
                break;

            default:
                throw new AssertionError();
        }
    }

}
