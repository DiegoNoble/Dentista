/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.views;

import com.dnsoft.dentista.beans.Caja;
import com.dnsoft.dentista.beans.CuentaPaciente;
import com.dnsoft.dentista.beans.MonedaEnum;
import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.beans.Parametros;
import com.dnsoft.dentista.beans.Rubro;
import com.dnsoft.dentista.beans.TrabajoTratamientoEnum;
import com.dnsoft.dentista.beans.TrabajosTratamiento;
import com.dnsoft.dentista.controllers.ControlDeCajaController;
import com.dnsoft.dentista.daos.ICajaDAO;
import com.dnsoft.dentista.daos.ICuentaPacienteDAO;
import com.dnsoft.dentista.daos.IPacienteDAO;
import com.dnsoft.dentista.daos.IParametrosDAO;
import com.dnsoft.dentista.daos.ITrabajosTratamientoDAO;
import com.dnsoft.dentista.renderers.LocalDateCellRenderer;
import com.dnsoft.dentista.tablemodels.CuentaPacienteTableModel;
import com.dnsoft.dentista.utiles.ActualizaSaldos;
import com.dnsoft.dentista.utiles.Container;
import com.dnsoft.dentista.utiles.ExportarDatosExcel;
import com.dnsoft.dentista.utiles.LeeProperties;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Diego
 */
public class DetalleMovimientosCuentaPaciente2 extends javax.swing.JFrame {

    List<Paciente> listPacientes;
    IPacienteDAO pacienteDAO;
    Container container;
    Paciente pacienteSeleccionado;
    IPacienteDAO PacienteDAO;
    ICuentaPacienteDAO CuentaPacienteDAO;
    CuentaPacienteTableModel tableModelCCPesos;
    CuentaPacienteTableModel tableModelCCDolares;
    ControlDeCajaController cajaController;
    private static DetalleMovimientosCuentaPaciente2 instanciaUnica = null;
    List<CuentaPaciente> listCCPesos;
    CuentaPaciente ccPesosSeleccionado;
    CuentaPaciente ccDolaresSeleccionado;
    List<CuentaPaciente> listCCDolares;
    BigDecimal saldoPesos;
    BigDecimal saldoDolares;
    Rubro rubro;
    Parametros parametros;
    IParametrosDAO parametrosDAO;
    ICajaDAO cajaDAO;
    ITrabajosTratamientoDAO trabajosTratamientoDAO;
    DecimalFormat formatter = new DecimalFormat("###,###,###.00");
    ListSelectionModel listModelPesos;
    ListSelectionModel listModelDolares;
    LeeProperties props = new LeeProperties();

    public DetalleMovimientosCuentaPaciente2() {

        initComponents();
        inicio();

        btnPagoPesos.setVisible(true);
        btnPagoDolares.setVisible(true);
        anulaMovimientoDolares.setEnabled(false);
        anulaMovimientoDolares.setEnabled(false);

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowActivated(WindowEvent e) {

                actualizaComboPacientes();
                buscaMovimientosCC();
            }

        });

    }

    /*public DetalleMovimientosCuentaPaciente2(Paciente PacienteSeleccionado) {

        initComponents();
        this.pacienteSeleccionado = PacienteSeleccionado;

        inicio();

        btnPagoPesos.setVisible(true);
        btnPagoDolares.setVisible(true);
        anulaMovimientoDolares.setEnabled(false);
        anulaMovimientoDolares.setEnabled(false);

        cbPaciente.setSelectedItem(PacienteSeleccionado);
    }

    public DetalleMovimientosCuentaPaciente2(Rubro rubro, ControlDeCajaController cajaController) {

        initComponents();
        this.rubro = rubro;
        this.cajaController = cajaController;
        inicio();
    }
     */
    private void cargaComboPacientes() {

        listPacientes.clear();
        listPacientes.addAll(pacienteDAO.findAll());
        cbPaciente.removeAllItems();

        for (Paciente p : listPacientes) {

            cbPaciente.addItem(p);
        }

    }

    private void actualizaComboPacientes() {
        List<Paciente> nuevaLista = new ArrayList();

        nuevaLista.addAll(pacienteDAO.findAll());
        for (Paciente p : nuevaLista) {
            if (!listPacientes.contains(p)) {
                listPacientes.add(p);
                cbPaciente.addItem(p);
            }
        }
    }

    public static DetalleMovimientosCuentaPaciente2 getInstancia() {

        if (instanciaUnica == null) {
            instanciaUnica = new DetalleMovimientosCuentaPaciente2();

        }
        return instanciaUnica;
    }

    public void setPacienteSeleccionado(Paciente pacienteSeleccionado) {
        actualizaComboPacientes();
        this.pacienteSeleccionado = pacienteSeleccionado;
        buscaMovimientosCC();
    }

    public void inicio() {

        setLocationRelativeTo(null);
        btnExcelPesos.setVisible(true);
        btnExcelDolares.setVisible(true);

        btnActualizaSaldoD.setVisible(false);
        btnActualizaSaldoP.setVisible(false);

        this.container = Container.getInstancia();

        PacienteDAO = container.getBean(IPacienteDAO.class
        );
        CuentaPacienteDAO = container.getBean(ICuentaPacienteDAO.class
        );
        parametrosDAO = container.getBean(IParametrosDAO.class
        );
        pacienteDAO = container.getBean(IPacienteDAO.class
        );
        trabajosTratamientoDAO = container.getBean(ITrabajosTratamientoDAO.class
        );
        cajaDAO = container.getBean(ICajaDAO.class
        );
        parametros = parametrosDAO.findAll().get(0);
        AutoCompleteDecorator.decorate(cbPaciente);
        listPacientes = new ArrayList();

        fechas();
        configuraTblCCPesos();
        configuraTblCCDolares();
        cargaComboPacientes();

        buscaMovimientosCC();
        accionesBotones();
        verificaResolucionDePantalla();
        //pacienteSeleccionado = (Paciente) cbPaciente.getSelectedItem();

        setVisible(true);
        toFront();

    }

    void fechas() {
        dpFin.setDate(LocalDate.now());
        dpInicio.setDate(LocalDate.now());

        dpFin.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent event) {
                buscaMovimientosCC();
            }
        });

        dpInicio.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent event) {
                buscaMovimientosCC();
            }
        });

    }

    void verificaResolucionDePantalla() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension tamTela = kit.getScreenSize();
        int larg = tamTela.width;
        if (larg < 1280) {
            tblCCDolares.setFont(new java.awt.Font("Tahoma", 0, 10));
            tblCCDolares.setRowHeight(20);

            tblCCPesos.setFont(new java.awt.Font("Tahoma", 0, 10));
            tblCCPesos.setRowHeight(20);
        } else if (larg >= 1280 && larg < 1600) {
            tblCCDolares.setFont(new java.awt.Font("Tahoma", 0, 12));
            tblCCDolares.setRowHeight(23);

            tblCCPesos.setFont(new java.awt.Font("Tahoma", 0, 12));
            tblCCPesos.setRowHeight(23);

        } else if (larg >= 1600) {
            tblCCDolares.setFont(new java.awt.Font("Tahoma", 0, 14));
            tblCCDolares.setRowHeight(25);

            tblCCPesos.setFont(new java.awt.Font("Tahoma", 0, 14));
            tblCCPesos.setRowHeight(25);
        }
    }

    final void configuraTblCCPesos() {
        ((DefaultTableCellRenderer) tblCCPesos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        listCCPesos = new ArrayList<>();

        tableModelCCPesos = new CuentaPacienteTableModel(listCCPesos);
        tblCCPesos.setModel(tableModelCCPesos);
        tblCCPesos.getColumn("Fecha").setCellRenderer(new LocalDateCellRenderer());

        int[] anchos = {20, 5, 5, 10, 200, 50, 5};

        for (int i = 0; i < tblCCPesos.getColumnCount(); i++) {

            tblCCPesos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }
        tblCCPesos.setRowHeight(16);
        listModelPesos = tblCCPesos.getSelectionModel();
        listModelPesos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tblCCPesos.getSelectedRow() != -1) {
                    anulaMovimientoPesos.setEnabled(true);
                    ccPesosSeleccionado = listCCPesos.get(tblCCPesos.getSelectedRow());
                    btnReciboPesos.setEnabled(true);
                } else {
                    anulaMovimientoPesos.setEnabled(false);
                    btnReciboPesos.setEnabled(false);
                }
            }
        });

    }

    final void configuraTblCCDolares() {
        ((DefaultTableCellRenderer) tblCCDolares.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        listCCDolares = new ArrayList<>();

        tableModelCCDolares = new CuentaPacienteTableModel(listCCDolares);
        tblCCDolares.setModel(tableModelCCDolares);
        tblCCDolares.getColumn("Fecha").setCellRenderer(new LocalDateCellRenderer());

        int[] anchos = {20, 5, 5, 20, 200, 50 ,5};

        for (int i = 0; i < tblCCDolares.getColumnCount(); i++) {

            tblCCDolares.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }
        tblCCDolares.setRowHeight(16);
        tblCCDolares.setRowHeight(16);
        listModelDolares = tblCCDolares.getSelectionModel();
        listModelDolares.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tblCCDolares.getSelectedRow() != -1) {
                    anulaMovimientoDolares.setEnabled(true);
                    ccDolaresSeleccionado = listCCDolares.get(tblCCDolares.getSelectedRow());
                    btnReciboDolar.setEnabled(true);
                } else {
                    anulaMovimientoDolares.setEnabled(false);
                    btnReciboDolar.setEnabled(false);
                }
            }
        });

    }

    void buscaMovimientosCC() {

        pacienteSeleccionado = (Paciente) cbPaciente.getSelectedItem();
        listCCPesos.clear();
        List movimientos = CuentaPacienteDAO.findByPacienteAndMonedaAndFechaBetween(pacienteSeleccionado, MonedaEnum.PESOS, dpInicio.getDate(), dpFin.getDate());
        listCCPesos.addAll(movimientos);
        tableModelCCPesos.fireTableDataChanged();
        List<CuentaPaciente> cuentaCorriente = CuentaPacienteDAO.findByPacienteAndMonedaOrderFechaMovimientoAsc(pacienteSeleccionado, MonedaEnum.PESOS);
        if (!cuentaCorriente.isEmpty()) {

            saldoPesos = cuentaCorriente.get(cuentaCorriente.size() - 1).getSaldo();
            txtSaldoPesos.setText(formatter.format(saldoPesos));
        } else {

            txtSaldoPesos.setText(BigDecimal.ZERO.toString());
            saldoPesos = BigDecimal.ZERO;
        }

        listCCDolares.clear();
        listCCDolares.addAll(CuentaPacienteDAO.findByPacienteAndMonedaAndFechaBetween(pacienteSeleccionado, MonedaEnum.DOLARES, dpInicio.getDate(), dpFin.getDate()));
        tableModelCCDolares.fireTableDataChanged();
        List<CuentaPaciente> cuentaCorrienteDolares = CuentaPacienteDAO.findByPacienteAndMonedaOrderFechaMovimientoAsc(pacienteSeleccionado, MonedaEnum.DOLARES);
        if (!cuentaCorrienteDolares.isEmpty()) {

            saldoDolares = cuentaCorrienteDolares.get(cuentaCorrienteDolares.size() - 1).getSaldo();
            txtSaldoDolares.setText(formatter.format(saldoDolares));
        } else {

            txtSaldoDolares.setText(BigDecimal.ZERO.toString());
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
            parametros.put("fechaInicio", dpInicio.getDate());
            parametros.put("fechaFin", dpFin.getDate());
            parametros.put("saldo", saldoPesos.toString());
            parametros.put("logo", logo);

            JasperPrint jasperPrint = JasperFillManager.fillReport(resource, parametros, beanCollectionDataSource);
            JasperViewer.viewReport(jasperPrint, false);

            //JRExporter exporter = new JRXlsExporter();
            //exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            //exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reporte2XLS2.xls"));
        } catch (Exception ex) {
            System.out.println("Error " + ex);
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
            parametros.put("fechaInicio", dpInicio.getDate());
            parametros.put("fechaFin", dpFin.getDate());
            parametros.put("saldo", saldoDolares.toString());
            parametros.put("logo", logo);

            JasperPrint jasperPrint = JasperFillManager.fillReport(resource, parametros, beanCollectionDataSource);
            JasperViewer.viewReport(jasperPrint, false);

            //JRExporter exporter = new JRXlsExporter();
            //exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            //exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reporte2XLS2.xls"));
        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
    }

    void accionesBotones() {
        btnExcelPesos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
                    ExportarDatosExcel exportar = new ExportarDatosExcel(tblCCPesos, "Cuenta paciente");
                    exportar.exportarJTableExcel();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al exportar datos " + ex, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnExcelDolares.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
                    ExportarDatosExcel exportar = new ExportarDatosExcel(tblCCDolares, "Cuenta Corriente");
                    exportar.exportarJTableExcel();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al exportar datos " + ex, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnPagoPesos.addMouseListener(new MouseAdapter() {
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

        btnPagoDolares.addMouseListener(
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

        btnReciboPesos.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                imprimeRecibo(ccPesosSeleccionado.getId());
            }
        }
        );

        btnReciboDolar.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                imprimeRecibo(ccDolaresSeleccionado.getId());
            }
        }
        );

        botonVolver1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                dispose();

            }
        }
        );

        cbPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                buscaMovimientosCC();
            }
        });

        anulaMovimientoPesos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                anulaMovimientoPesos();
            }
        });

        anulaMovimientoDolares.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                anulaMovimientoDolares();
            }
        });
    }

    private void anulaMovimientoPesos() {

        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Esta seguro que desea elminar el movimiento seleccionado?", "Atención", JOptionPane.YES_NO_OPTION);
        if (showConfirmDialog == JOptionPane.YES_OPTION) {

            if (ccPesosSeleccionado.getTrabajoTratamiento() != null) {
                TrabajosTratamiento trabajoTratamiento = ccPesosSeleccionado.getTrabajoTratamiento();
                trabajoTratamiento.setFechaDebito(null);
                trabajoTratamiento.setTrabajoTratamientoEnum(TrabajoTratamientoEnum.P);
                trabajosTratamientoDAO.save(trabajoTratamiento);

                CuentaPacienteDAO.delete(ccPesosSeleccionado);
            } else {
                CuentaPacienteDAO.delete(ccPesosSeleccionado);

                Caja movimientoCaja = new Caja();
                movimientoCaja.setDescripcion("Devolución  Paciente " + pacienteSeleccionado.getNombre());
                movimientoCaja.setSalida(ccPesosSeleccionado.getHaber());
                movimientoCaja.setFecha(ccPesosSeleccionado.getFechaMovimiento());
                movimientoCaja.setMoneda(MonedaEnum.PESOS);
                movimientoCaja.setRubro(parametros.getCobroCuentaPaciente());
                movimientoCaja.setObs("Devolución  Paciente " + pacienteSeleccionado.getNombre());
                movimientoCaja.setEntrada(BigDecimal.ZERO);
                cajaDAO.save(movimientoCaja);

            }

            //Ajusta saldo cc Paciente
            ActualizaSaldos acSaldo = new ActualizaSaldos();
            List<CuentaPaciente> ccPaciente = CuentaPacienteDAO.findByPacienteAndMonedaOrderFechaMovimientoAsc(pacienteSeleccionado, MonedaEnum.PESOS);

            CuentaPacienteDAO.save(acSaldo.ActualizaSaldosPacientes(ccPaciente));
            JOptionPane.showMessageDialog(
                    null, "Movimiento anulado correctamente");
            buscaMovimientosCC();
        }
    }

    private void anulaMovimientoDolares() {
        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Esta seguro que desea elminar el movimiento seleccionado?", "Atención", JOptionPane.YES_NO_OPTION);
        if (showConfirmDialog == JOptionPane.YES_OPTION) {

            if (ccDolaresSeleccionado.getTrabajoTratamiento() != null) {
                TrabajosTratamiento trabajoTratamiento = ccDolaresSeleccionado.getTrabajoTratamiento();
                trabajoTratamiento.setFechaDebito(null);
                trabajoTratamiento.setTrabajoTratamientoEnum(TrabajoTratamientoEnum.P);
                trabajosTratamientoDAO.save(trabajoTratamiento);
                CuentaPacienteDAO.delete(ccDolaresSeleccionado);
            } else {

                CuentaPacienteDAO.delete(ccDolaresSeleccionado);
                Caja movimientoCaja = new Caja();
                movimientoCaja.setDescripcion("Devolución  Paciente " + pacienteSeleccionado.getNombre());
                movimientoCaja.setSalida(ccPesosSeleccionado.getHaber());
                movimientoCaja.setFecha(ccPesosSeleccionado.getFechaMovimiento());
                movimientoCaja.setMoneda(MonedaEnum.DOLARES);
                movimientoCaja.setRubro(parametros.getCobroCuentaPaciente());
                movimientoCaja.setObs("Devolución  Paciente " + pacienteSeleccionado.getNombre());
                movimientoCaja.setEntrada(BigDecimal.ZERO);
                cajaDAO.save(movimientoCaja);
            }
            //Ajusta saldo cc Paciente
            ActualizaSaldos acSaldo = new ActualizaSaldos();
            List<CuentaPaciente> ccPaciente = CuentaPacienteDAO.findByPacienteAndMonedaOrderFechaMovimientoAsc(pacienteSeleccionado, MonedaEnum.DOLARES);

            CuentaPacienteDAO.save(acSaldo.ActualizaSaldosPacientes(ccPaciente));
            JOptionPane.showMessageDialog(null, "Movimiento anulado correctamente");
            buscaMovimientosCC();
        }
    }

    public void imprimeRecibo(Long idRecibo) {

        try {
            HashMap parametros = new HashMap();
            parametros.clear();
            parametros.put("recibo", idRecibo);

            Connection conexion = DriverManager.getConnection(props.getUrl(), props.getUsr(), props.getPsw());

            InputStream resource = getClass().getClassLoader().getResourceAsStream("reportes/recibo_dentista.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(resource, parametros, conexion);
            JasperViewer reporte = new JasperViewer(jasperPrint, false);
            reporte.setVisible(true);

            reporte.toFront();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! " + e, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btnActualizaSaldoP = new javax.swing.JButton();
        botonVolver1 = new botones.BotonVolver();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCCPesos = new javax.swing.JTable();
        txtSaldoPesos = new javax.swing.JTextField();
        btnPagoPesos = new botones.BotonPagar();
        jLabel6 = new javax.swing.JLabel();
        btnExcelPesos = new botones.BotonExcel();
        anulaMovimientoPesos = new botones.BotonEliminar();
        btnReciboPesos = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblCCDolares = new javax.swing.JTable();
        txtSaldoDolares = new javax.swing.JTextField();
        btnPagoDolares = new botones.BotonPagar();
        jLabel7 = new javax.swing.JLabel();
        btnExcelDolares = new botones.BotonExcel();
        anulaMovimientoDolares = new botones.BotonEliminar();
        btnReciboDolar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dpInicio = new com.github.lgooddatepicker.components.DatePicker();
        dpFin = new com.github.lgooddatepicker.components.DatePicker();
        cbPaciente = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        btnActualizaSaldoD = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(900, 800));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        btnActualizaSaldoP.setText("Actualiza Saldos Pesos");
        btnActualizaSaldoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizaSaldoPActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 0, 0, 0);
        getContentPane().add(btnActualizaSaldoP, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(botonVolver1, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel3.setFont(new java.awt.Font("Arial", 3, 48)); // NOI18N
        jLabel3.setText("Cuenta Paciente");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(jLabel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jPanel2, gridBagConstraints);

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel8.setLayout(new java.awt.GridBagLayout());

        tblCCPesos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCCPesos.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jScrollPane3.setViewportView(tblCCPesos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel8.add(jScrollPane3, gridBagConstraints);

        txtSaldoPesos.setEnabled(false);
        txtSaldoPesos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel8.add(txtSaldoPesos, gridBagConstraints);

        btnPagoPesos.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnPagoPesos.setText("Pago en PESOS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel8.add(btnPagoPesos, gridBagConstraints);

        jLabel6.setText("Saldo disponible en PESOS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel8.add(jLabel6, gridBagConstraints);

        btnExcelPesos.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnExcelPesos.setPreferredSize(new java.awt.Dimension(80, 100));
        btnExcelPesos.setText("Excel cuenta Pesos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel8.add(btnExcelPesos, gridBagConstraints);

        anulaMovimientoPesos.setText("Anular movimiento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel8.add(anulaMovimientoPesos, gridBagConstraints);

        btnReciboPesos.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnReciboPesos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editar_grande.png"))); // NOI18N
        btnReciboPesos.setMnemonic('R');
        btnReciboPesos.setText("Recibo ");
        btnReciboPesos.setEnabled(false);
        btnReciboPesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReciboPesosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel8.add(btnReciboPesos, gridBagConstraints);

        jTabbedPane1.addTab("Cuenta PESOS", jPanel8);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        tblCCDolares.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tblCCDolares.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tblCCDolares);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(jScrollPane5, gridBagConstraints);

        txtSaldoDolares.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtSaldoDolares.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(txtSaldoDolares, gridBagConstraints);

        btnPagoDolares.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnPagoDolares.setText("Pago en DOLARES");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(btnPagoDolares, gridBagConstraints);

        jLabel7.setText("Saldo disponible en DOLARES");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel6.add(jLabel7, gridBagConstraints);

        btnExcelDolares.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnExcelDolares.setPreferredSize(new java.awt.Dimension(80, 100));
        btnExcelDolares.setText("Excel cuenta DOLARES");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(btnExcelDolares, gridBagConstraints);

        anulaMovimientoDolares.setText("Anular movimiento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(anulaMovimientoDolares, gridBagConstraints);

        btnReciboDolar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnReciboDolar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editar_grande.png"))); // NOI18N
        btnReciboDolar.setMnemonic('R');
        btnReciboDolar.setText("Recibo ");
        btnReciboDolar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReciboDolarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(btnReciboDolar, gridBagConstraints);

        jTabbedPane1.addTab("Cuenta DOLARES", jPanel6);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Cuenta"));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("Período:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jLabel5, gridBagConstraints);

        jLabel4.setText("al");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(dpInicio, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(dpFin, gridBagConstraints);

        cbPaciente = new JComboBox<Object>();
        cbPaciente.setActionCommand("pacienteSeleccionado");
        cbPaciente.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        cbPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPacienteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(cbPaciente, gridBagConstraints);

        jLabel11.setText("Paciente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jLabel11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        jPanel5.add(jPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        getContentPane().add(jPanel5, gridBagConstraints);

        btnActualizaSaldoD.setText("Actualiza Saldos Dolares");
        btnActualizaSaldoD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizaSaldoDActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        getContentPane().add(btnActualizaSaldoD, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReciboPesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReciboPesosActionPerformed

    }//GEN-LAST:event_btnReciboPesosActionPerformed

    private void btnReciboDolarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReciboDolarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReciboDolarActionPerformed

    private void cbPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPacienteActionPerformed

    private void btnActualizaSaldoDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizaSaldoDActionPerformed
        ActualizaSaldos as = new ActualizaSaldos();
        CuentaPacienteDAO.save(as.ActualizaSaldosPacientes(listCCDolares));

    }//GEN-LAST:event_btnActualizaSaldoDActionPerformed

    private void btnActualizaSaldoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizaSaldoPActionPerformed
        ActualizaSaldos asp = new ActualizaSaldos();
        CuentaPacienteDAO.save(asp.ActualizaSaldosPacientes(listCCPesos));
    }//GEN-LAST:event_btnActualizaSaldoPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public botones.BotonEliminar anulaMovimientoDolares;
    public botones.BotonEliminar anulaMovimientoPesos;
    public botones.BotonVolver botonVolver1;
    public javax.swing.JButton btnActualizaSaldoD;
    public javax.swing.JButton btnActualizaSaldoP;
    public botones.BotonExcel btnExcelDolares;
    public botones.BotonExcel btnExcelPesos;
    public botones.BotonPagar btnPagoDolares;
    public botones.BotonPagar btnPagoPesos;
    public javax.swing.JButton btnReciboDolar;
    public javax.swing.JButton btnReciboPesos;
    public javax.swing.JComboBox cbPaciente;
    public com.github.lgooddatepicker.components.DatePicker dpFin;
    public com.github.lgooddatepicker.components.DatePicker dpInicio;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    public javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTable tblCCDolares;
    public javax.swing.JTable tblCCPesos;
    public javax.swing.JTextField txtSaldoDolares;
    public javax.swing.JTextField txtSaldoPesos;
    // End of variables declaration//GEN-END:variables

}
