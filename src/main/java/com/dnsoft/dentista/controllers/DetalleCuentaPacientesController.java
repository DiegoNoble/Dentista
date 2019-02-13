/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.controllers;

import com.dnsoft.dentista.beans.Caja;
import com.dnsoft.dentista.beans.CuentaPaciente;
import com.dnsoft.dentista.beans.MonedaEnum;
import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.beans.Parametros;
import com.dnsoft.dentista.beans.Rubro;
import com.dnsoft.dentista.daos.ICuentaPacienteDAO;
import com.dnsoft.dentista.daos.IPacienteDAO;
import com.dnsoft.dentista.daos.IParametrosDAO;
import com.dnsoft.dentista.renderers.LocalDateCellRenderer;
import com.dnsoft.dentista.tablemodels.CuentaPacienteTableModel;
import com.dnsoft.dentista.utiles.ActualizaSaldos;
import com.dnsoft.dentista.utiles.Container;
import com.dnsoft.dentista.utiles.ExportarDatosExcel;
import com.dnsoft.dentista.views.CobroDeudaPaciente;
import com.dnsoft.dentista.views.DetalleMovimientosCuentaPaciente;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Diego Noble
 */
public class DetalleCuentaPacientesController implements ActionListener {

    List<Paciente> listPacientes;
    IPacienteDAO pacienteDAO;
    Container container;
    Paciente pacienteSeleccionado;
    IPacienteDAO PacienteDAO;
    ICuentaPacienteDAO CuentaPacienteDAO;
    CuentaPacienteTableModel tableModelCCPesos;
    CuentaPacienteTableModel tableModelCCDolares;
    ControlDeCajaController cajaController;
    DetalleMovimientosCuentaPaciente view;
    List<CuentaPaciente> listCCPesos;
    CuentaPaciente ccPesosSeleccionado;
    CuentaPaciente ccDolaresSeleccionado;
    List<CuentaPaciente> listCCDolares;
    BigDecimal saldoPesos;
    BigDecimal saldoDolares;
    Rubro rubro;
    Parametros parametros;
    IParametrosDAO parametrosDAO;
    DecimalFormat formatter = new DecimalFormat("###,###,###.00");
    ListSelectionModel listModelPesos;
    ListSelectionModel listModelDolares;

    public DetalleCuentaPacientesController(DetalleMovimientosCuentaPaciente view, Paciente PacienteSeleccionado) {

        this.view = view;
        view.btnPagoPesos.setVisible(true);
        view.btnPagoDolares.setVisible(true);
        view.anulaMovimientoDolares.setEnabled(false);
        view.anulaMovimientoDolares.setEnabled(false);
        this.pacienteSeleccionado = PacienteSeleccionado;

        inicio();
        view.cbPaciente.setSelectedItem(PacienteSeleccionado);
    }

    public DetalleCuentaPacientesController(DetalleMovimientosCuentaPaciente view) {

        this.view = view;
        view.btnPagoPesos.setVisible(true);
        view.btnPagoDolares.setVisible(true);
        view.anulaMovimientoDolares.setEnabled(false);
        view.anulaMovimientoDolares.setEnabled(false);

        inicio();
        buscaMovimientosCC();
    }

    public DetalleCuentaPacientesController(DetalleMovimientosCuentaPaciente view, Rubro rubro, ControlDeCajaController cajaController) {

        this.view = view;
        this.rubro = rubro;
        this.cajaController = cajaController;
        inicio();
    }

    public void go() {
        this.view.setVisible(true);
        this.view.toFront();

    }

    private void cargaComboPacientes() {

        listPacientes = new ArrayList();
        listPacientes = pacienteDAO.findAll();
        view.cbPaciente.removeAllItems();

        for (Paciente p : listPacientes) {

            view.cbPaciente.addItem(p);
        }
    }

    void verificaResolucionDePantalla() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension tamTela = kit.getScreenSize();
        int larg = tamTela.width;
        if (larg < 1280) {
            view.tblCCDolares.setFont(new java.awt.Font("Tahoma", 0, 10));
            view.tblCCDolares.setRowHeight(20);

            view.tblCCPesos.setFont(new java.awt.Font("Tahoma", 0, 10));
            view.tblCCPesos.setRowHeight(20);
        } else if (larg >= 1280 && larg < 1600) {
            view.tblCCDolares.setFont(new java.awt.Font("Tahoma", 0, 12));
            view.tblCCDolares.setRowHeight(23);

            view.tblCCPesos.setFont(new java.awt.Font("Tahoma", 0, 12));
            view.tblCCPesos.setRowHeight(23);

        } else if (larg >= 1600) {
            view.tblCCDolares.setFont(new java.awt.Font("Tahoma", 0, 14));
            view.tblCCDolares.setRowHeight(25);

            view.tblCCPesos.setFont(new java.awt.Font("Tahoma", 0, 14));
            view.tblCCPesos.setRowHeight(25);
        }
    }

    final void inicio() {

        view.btnExcelPesos.setVisible(true);
        view.btnExcelDolares.setVisible(true);

        view.btnActualizaSaldoD.setActionCommand("actualizaSaldosD");
        view.btnActualizaSaldoD.addActionListener(this);

        view.btnActualizaSaldoP.setActionCommand("actualizaSaldosP");
        view.btnActualizaSaldoP.addActionListener(this);

        this.container = Container.getInstancia();

        PacienteDAO = container.getBean(IPacienteDAO.class);
        CuentaPacienteDAO = container.getBean(ICuentaPacienteDAO.class);
        parametrosDAO = container.getBean(IParametrosDAO.class);
        pacienteDAO = container.getBean(IPacienteDAO.class);
        parametros = parametrosDAO.findAll().get(0);
        fechas();
        configuraTblCCPesos();
        configuraTblCCDolares();
        cargaComboPacientes();
        accionesBotones();
        buscaMovimientosCC();
        verificaResolucionDePantalla();
        pacienteSeleccionado = (Paciente) view.cbPaciente.getSelectedItem();
    }

    void fechas() {
        view.dpFin.setDate(LocalDate.now());
        view.dpInicio.setDate(LocalDate.now());

        view.dpFin.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent event) {
                buscaMovimientosCC();
            }
        });

        view.dpInicio.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent event) {
                buscaMovimientosCC();
            }
        });

    }

    final void configuraTblCCPesos() {
        ((DefaultTableCellRenderer) view.tblCCPesos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        listCCPesos = new ArrayList<>();

        tableModelCCPesos = new CuentaPacienteTableModel(listCCPesos);
        view.tblCCPesos.setModel(tableModelCCPesos);
        view.tblCCPesos.getColumn("Fecha").setCellRenderer(new LocalDateCellRenderer());

        int[] anchos = {5, 5, 5, 5, 100};

        for (int i = 0; i < view.tblCCPesos.getColumnCount(); i++) {

            view.tblCCPesos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }
        view.tblCCPesos.setRowHeight(16);
        listModelPesos = view.tblCCPesos.getSelectionModel();
        listModelPesos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (view.tblCCPesos.getSelectedRow() != -1) {
                    view.anulaMovimientoPesos.setEnabled(true);
                    ccPesosSeleccionado = listCCPesos.get(view.tblCCPesos.getSelectedRow());
                } else {
                    view.anulaMovimientoPesos.setEnabled(false);
                }
            }
        });

    }

    final void configuraTblCCDolares() {
        ((DefaultTableCellRenderer) view.tblCCDolares.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        listCCDolares = new ArrayList<>();

        tableModelCCDolares = new CuentaPacienteTableModel(listCCDolares);
        view.tblCCDolares.setModel(tableModelCCDolares);
        view.tblCCDolares.getColumn("Fecha").setCellRenderer(new LocalDateCellRenderer());

        int[] anchos = {5, 5, 5, 5, 100};

        for (int i = 0; i < view.tblCCDolares.getColumnCount(); i++) {

            view.tblCCDolares.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }
        view.tblCCDolares.setRowHeight(16);
        view.tblCCDolares.setRowHeight(16);
        listModelDolares = view.tblCCDolares.getSelectionModel();
        listModelDolares.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (view.tblCCDolares.getSelectedRow() != -1) {
                    view.anulaMovimientoDolares.setEnabled(true);
                    ccDolaresSeleccionado = listCCDolares.get(view.tblCCDolares.getSelectedRow());
                } else {
                    view.anulaMovimientoDolares.setEnabled(false);
                }
            }
        });

    }

    void buscaMovimientosCC() {

        listCCPesos.clear();
        List movimientos = CuentaPacienteDAO.findByPacienteAndMonedaAndFechaBetween(pacienteSeleccionado, MonedaEnum.PESOS, view.dpInicio.getDate(), view.dpFin.getDate());
        listCCPesos.addAll(movimientos);
        tableModelCCPesos.fireTableDataChanged();
        List<CuentaPaciente> cuentaCorriente = CuentaPacienteDAO.findByPacienteAndMonedaOrderFechaMovimientoAsc(pacienteSeleccionado, MonedaEnum.PESOS);
        if (!cuentaCorriente.isEmpty()) {

            saldoPesos = cuentaCorriente.get(cuentaCorriente.size() - 1).getSaldo();
            view.txtSaldoPesos.setText(formatter.format(saldoPesos));
        } else {

            view.txtSaldoPesos.setText(BigDecimal.ZERO.toString());
            saldoPesos = BigDecimal.ZERO;
        }

        listCCDolares.clear();
        listCCDolares.addAll(CuentaPacienteDAO.findByPacienteAndMonedaAndFechaBetween(pacienteSeleccionado, MonedaEnum.DOLARES, view.dpInicio.getDate(), view.dpFin.getDate()));
        tableModelCCDolares.fireTableDataChanged();
        List<CuentaPaciente> cuentaCorrienteDolares = CuentaPacienteDAO.findByPacienteAndMonedaOrderFechaMovimientoAsc(pacienteSeleccionado, MonedaEnum.DOLARES);
        if (!cuentaCorrienteDolares.isEmpty()) {

            saldoDolares = cuentaCorrienteDolares.get(cuentaCorrienteDolares.size() - 1).getSaldo();
            view.txtSaldoDolares.setText(formatter.format(saldoDolares));
        } else {

            view.txtSaldoDolares.setText(BigDecimal.ZERO.toString());
            saldoDolares = BigDecimal.ZERO;
        }

    }

    void informePesos() {

        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listCCPesos);
            InputStream resource = getClass().getClassLoader().getResourceAsStream("reportes/CuentaPaciente.jasper");

            BufferedImage logo = ImageIO.read(getClass().getResource("/imagenes/logo.png"));

            HashMap parametros = new HashMap<>();
            parametros.put("Paciente", pacienteSeleccionado.toString());
            parametros.put("moneda", MonedaEnum.PESOS.toString());
            parametros.put("fechaInicio", view.dpInicio.getDate());
            parametros.put("fechaFin", view.dpFin.getDate());
            parametros.put("saldo", saldoPesos.toString());
            parametros.put("logo", logo);

            JasperPrint jasperPrint = JasperFillManager.fillReport(resource, parametros, beanCollectionDataSource);
            JasperViewer.viewReport(jasperPrint, false);

            //JRExporter exporter = new JRXlsExporter();
            //exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            //exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reporte2XLS2.xls"));
        } catch (JRException ex) {
            Logger.getLogger(DetalleCuentaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DetalleCuentaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void informeDolares() {

        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listCCDolares);
            InputStream resource = getClass().getClassLoader().getResourceAsStream("reportes/CuentaPaciente.jasper");

            BufferedImage logo = ImageIO.read(getClass().getResource("/imagenes/logo.png"));

            HashMap parametros = new HashMap<>();
            parametros.put("Paciente", pacienteSeleccionado.toString());
            parametros.put("moneda", MonedaEnum.DOLARES.toString());
            parametros.put("fechaInicio", view.dpInicio.getDate());
            parametros.put("fechaFin", view.dpFin.getDate());
            parametros.put("saldo", saldoDolares.toString());
            parametros.put("logo", logo);

            JasperPrint jasperPrint = JasperFillManager.fillReport(resource, parametros, beanCollectionDataSource);
            JasperViewer.viewReport(jasperPrint, false);

            //JRExporter exporter = new JRXlsExporter();
            //exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            //exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reporte2XLS2.xls"));
        } catch (JRException ex) {
            Logger.getLogger(DetalleCuentaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DetalleCuentaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void accionesBotones() {
        view.btnExcelPesos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
                    ExportarDatosExcel exportar = new ExportarDatosExcel(view.tblCCPesos, "Cuenta paciente");
                    exportar.exportarJTableExcel();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Error al exportar datos " + ex, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.btnExcelDolares.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
                    ExportarDatosExcel exportar = new ExportarDatosExcel(view.tblCCDolares, "Cuenta Corriente");
                    exportar.exportarJTableExcel();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Error al exportar datos " + ex, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.btnPagoPesos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                CobroDeudaPaciente retiro = new CobroDeudaPaciente(null, true, parametros.getCobroCuentaPaciente(),
                        pacienteSeleccionado, MonedaEnum.PESOS);
                retiro.setVisible(true);
                retiro.toFront();
                buscaMovimientosCC();
                if (cajaController != null) {
                    cajaController.actualizaTbl();
                }
            }
        }
        );

        view.btnPagoDolares.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                CobroDeudaPaciente retiro = new CobroDeudaPaciente(null, true, parametros.getCobroCuentaPaciente(),
                        pacienteSeleccionado, MonedaEnum.DOLARES);
                retiro.setVisible(true);
                retiro.toFront();
                buscaMovimientosCC();
                if (cajaController != null) {
                    cajaController.actualizaTbl();
                }
            }
        }
        );

        view.botonVolver1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                DetalleCuentaPacientesController.this.view.dispose();
            }
        }
        );

        view.cbPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pacienteSeleccionado = (Paciente) view.cbPaciente.getSelectedItem();
                buscaMovimientosCC();
            }
        });

        view.anulaMovimientoPesos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                anulaMovimientoPesos();
            }
        });

        view.anulaMovimientoDolares.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                anulaMovimientoDolares();
            }
        });
    }

    private void anulaMovimientoPesos() {

        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Esta seguro que desea elminar el movimiento seleccionado?", "Atención", JOptionPane.YES_NO_OPTION);
        if (showConfirmDialog == JOptionPane.YES_OPTION) {
            CuentaPacienteDAO.delete(ccPesosSeleccionado);

            //Ajusta saldo cc Paciente
            ActualizaSaldos acSaldo = new ActualizaSaldos();
            List<CuentaPaciente> ccPaciente = CuentaPacienteDAO.findByPacienteAndMonedaOrderFechaMovimientoAsc(pacienteSeleccionado, MonedaEnum.PESOS);

            CuentaPacienteDAO.save(acSaldo.ActualizaSaldosPacientes(ccPaciente));
            JOptionPane.showMessageDialog(null, "Movimiento anulado correctamente");
            buscaMovimientosCC();
        }
    }

    private void anulaMovimientoDolares() {
        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Esta seguro que desea elminar el movimiento seleccionado?", "Atención", JOptionPane.YES_NO_OPTION);
        if (showConfirmDialog == JOptionPane.YES_OPTION) {
            CuentaPacienteDAO.delete(ccDolaresSeleccionado);

            //Ajusta saldo cc Paciente
            ActualizaSaldos acSaldo = new ActualizaSaldos();
            List<CuentaPaciente> ccPaciente = CuentaPacienteDAO.findByPacienteAndMonedaOrderFechaMovimientoAsc(pacienteSeleccionado, MonedaEnum.DOLARES);

            CuentaPacienteDAO.save(acSaldo.ActualizaSaldosPacientes(ccPaciente));
            JOptionPane.showMessageDialog(null, "Movimiento anulado correctamente");
            buscaMovimientosCC();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {

            case "actualizaSaldosD":

                ActualizaSaldos as = new ActualizaSaldos();
                CuentaPacienteDAO.save(as.ActualizaSaldosPacientes(listCCDolares));

                break;

            case "actualizaSaldosP":

                ActualizaSaldos asp = new ActualizaSaldos();
                CuentaPacienteDAO.save(asp.ActualizaSaldosPacientes(listCCPesos));

                break;

            default:
                throw new AssertionError();
        }
    }

}