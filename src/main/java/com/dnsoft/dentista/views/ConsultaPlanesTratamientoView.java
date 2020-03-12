package com.dnsoft.dentista.views;

import com.dnsoft.dentista.beans.CuentaPaciente;
import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.beans.PlanTratamiento;
import com.dnsoft.dentista.beans.SituacionPlanTratamientoEnum;
import com.dnsoft.dentista.beans.TrabajoTratamientoEnum;
import com.dnsoft.dentista.beans.TrabajosTratamiento;
import com.dnsoft.dentista.daos.ICuentaPacienteDAO;
import com.dnsoft.dentista.daos.IHistoriaPlanTratamientoDAO;
import com.dnsoft.dentista.daos.IPacienteDAO;
import com.dnsoft.dentista.daos.IPlanTratamientoDAO;
import com.dnsoft.dentista.daos.ITrabajosDAO;
import com.dnsoft.dentista.daos.ITrabajosTratamientoDAO;
import com.dnsoft.dentista.renderers.LocalDateCellRenderer;
import com.dnsoft.dentista.renderers.TabelaTextAreaRenderer;
import com.dnsoft.dentista.renderers.TableRendererColorSituacionPlanTratamiento;
import com.dnsoft.dentista.renderers.TableRendererColorSituacionTrabajoTratamiento;
import com.dnsoft.dentista.tablemodels.PlanTratamientoTableModel;
import com.dnsoft.dentista.tablemodels.TrabajosTratamientoTableModel;
import com.dnsoft.dentista.utiles.ButtonColumnBorrar;
import com.dnsoft.dentista.utiles.ButtonColumnConfirmar;
import com.dnsoft.dentista.utiles.Container;
import com.dnsoft.dentista.utiles.LeeProperties;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.sql.Connection;
import java.sql.DriverManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.io.InputStream;
import org.springframework.dao.DataIntegrityViolationException;

public final class ConsultaPlanesTratamientoView extends javax.swing.JFrame {

    List<Paciente> listPacientes;
    PlanTratamientoTableModel tableModelPlanTratamiento;
    TrabajosTratamientoTableModel tableModelTrabajos;
    List<PlanTratamiento> listPlanTratamientos;
    List<TrabajosTratamiento> listTrabajos;
    PlanTratamiento planTratamientoSeleccionado;
    ITrabajosDAO trabajosDAO;
    IPacienteDAO pacienteDAO;
    ICuentaPacienteDAO cuentaPacienteDAO;
    IHistoriaPlanTratamientoDAO historiaPlanTratamientoDAO;
    IPlanTratamientoDAO planTratamientoDAO;
    ITrabajosTratamientoDAO trabajosTratamientoDAO;
    Container container;
    Paciente pacienteSeleccionado;
    TrabajosTratamiento trabajosTratamientoSeleccionado;
    LeeProperties props = new LeeProperties();
    private static ConsultaPlanesTratamientoView instanciaUnica = null;

    public ConsultaPlanesTratamientoView() {
        initComponents();
        inicio();

        btnModificar.setEnabled(false);

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowActivated(WindowEvent e) {

                actualizaComboPacientes();
            }

        });

    }


    public static ConsultaPlanesTratamientoView getInstancia() {

        if (instanciaUnica == null) {
            instanciaUnica = new ConsultaPlanesTratamientoView();

        }
        return instanciaUnica;
    }

    void inicio() {
        container = Container.getInstancia();
        trabajosDAO = container.getBean(ITrabajosDAO.class);
        pacienteDAO = container.getBean(IPacienteDAO.class);
        planTratamientoDAO = container.getBean(IPlanTratamientoDAO.class);
        cuentaPacienteDAO = container.getBean(ICuentaPacienteDAO.class);
        trabajosTratamientoDAO = container.getBean(ITrabajosTratamientoDAO.class);
        historiaPlanTratamientoDAO = container.getBean(IHistoriaPlanTratamientoDAO.class);
        setLocationRelativeTo(null);
        AutoCompleteDecorator.decorate(cbPaciente);
        listPacientes = new ArrayList();
        defineModelo();

        cargaComboPacientes();

        cbPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pacienteSeleccionado = (Paciente) cbPaciente.getSelectedItem();
                buscarPlanesPaciente();
            }
        });

    }

    public void buscarPlanesPaciente() {
        listPlanTratamientos.clear();
        listPlanTratamientos.addAll(planTratamientoDAO.findByPaciente((Paciente) cbPaciente.getSelectedItem()));
        tableModelPlanTratamiento.fireTableDataChanged();

    }

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

    void consultarPlanTratamiento() {

        PlanTratamientoView atenderPlanTratamiento = new PlanTratamientoView(planTratamientoSeleccionado, this);
        atenderPlanTratamiento.setVisible(true);
        atenderPlanTratamiento.toFront();

    }

    private void defineModelo() {
        ((DefaultTableCellRenderer) tblPlanesTratamiento.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer) tblTrabajos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        listPlanTratamientos = new ArrayList<PlanTratamiento>();
        tableModelPlanTratamiento = new PlanTratamientoTableModel(listPlanTratamientos);
        tblPlanesTratamiento.setModel(tableModelPlanTratamiento);

        listTrabajos = new ArrayList<TrabajosTratamiento>();
        tableModelTrabajos = new TrabajosTratamientoTableModel(listTrabajos);
        tblTrabajos.setModel(tableModelTrabajos);

        tblPlanesTratamiento.getColumn("Fecha").setCellRenderer(new LocalDateCellRenderer());
        tblPlanesTratamiento.getColumn("Fecha confirmación").setCellRenderer(new LocalDateCellRenderer());
        tblPlanesTratamiento.getColumn("Fecha finalizado").setCellRenderer(new LocalDateCellRenderer());
        tblPlanesTratamiento.getColumn("Obs.").setCellRenderer(new TabelaTextAreaRenderer());
        tblPlanesTratamiento.getColumn("Situación").setCellRenderer(new TableRendererColorSituacionPlanTratamiento());

        tblPlanesTratamiento.setRowHeight(40);
        int[] anchos = {5, 5, 5, 5, 400, 40};

        for (int i = 0; i < tblPlanesTratamiento.getColumnCount(); i++) {

            tblPlanesTratamiento.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }

        tblTrabajos.setRowHeight(25);

        tblTrabajos.getColumn("Situación").setCellRenderer(new TableRendererColorSituacionTrabajoTratamiento());
        tblTrabajos.getColumn("Fecha débito").setCellRenderer(new LocalDateCellRenderer());

        int[] anchos2 = {10, 500, 10, 10, 10};

        for (int i = 0; i < tblTrabajos.getColumnCount(); i++) {

            tblTrabajos.getColumnModel().getColumn(i).setPreferredWidth(anchos2[i]);

        }

        ListSelectionModel listModel = tblPlanesTratamiento.getSelectionModel();
        listModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tblPlanesTratamiento.getSelectedRow() != -1) {
                    planTratamientoSeleccionado = listPlanTratamientos.get(tblPlanesTratamiento.getSelectedRow());
                    listTrabajos.clear();

                    List<TrabajosTratamiento> trabajosTratamiento = trabajosTratamientoDAO.findByPlanTratamiento(planTratamientoSeleccionado);
                    if (trabajosTratamiento != null) {

                        listTrabajos.addAll(trabajosTratamiento);
                    }
                    tableModelTrabajos.fireTableDataChanged();
                    pacienteSeleccionado = planTratamientoSeleccionado.getPaciente();
                    btnFotos.setEnabled(true);
                    btnEliminar.setEnabled(true);
                    switch (planTratamientoSeleccionado.getSituacionPlanTratamientoEnum()) {
                        case PRESUPUESTO:
                            btnConfirmar.setEnabled(true);
                            btnModificar.setEnabled(true);
                            btnHistoria.setEnabled(false);
                            btnTrabajoProveedor.setEnabled(true);
                            btnImprimir.setEnabled(true);
                            break;
                        case CONFIRMA_PRESUPUESTO:
                            btnConfirmar.setEnabled(false);
                            btnModificar.setEnabled(true);
                            btnHistoria.setEnabled(true);
                            btnTrabajoProveedor.setEnabled(true);
                            btnImprimir.setEnabled(true);
                            break;
                        case TRATAMIENTO_EN_CURSO:
                            btnConfirmar.setEnabled(false);
                            btnModificar.setEnabled(true);
                            btnHistoria.setEnabled(true);
                            btnTrabajoProveedor.setEnabled(true);
                            btnImprimir.setEnabled(true);
                            break;
                        case TRATAMIENTO_FINALIZADO:
                            btnConfirmar.setEnabled(false);
                            btnModificar.setEnabled(false);
                            btnHistoria.setEnabled(true);
                            btnTrabajoProveedor.setEnabled(false);
                            btnImprimir.setEnabled(true);
                            break;
                        default:
                            throw new AssertionError();
                    }

                } else {
                    btnModificar.setEnabled(false);
                    btnHistoria.setEnabled(false);
                    btnTrabajoProveedor.setEnabled(false);
                    btnImprimir.setEnabled(false);
                    btnEliminar.setEnabled(false);

                    btnFotos.setEnabled(false);
                    listTrabajos.clear();
                    tableModelTrabajos.fireTableDataChanged();

                }

            }
        });

        tblPlanesTratamiento.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {

                if (me.getClickCount() == 2) {
                    consultarPlanTratamiento();
                }
            }
        });

        ListSelectionModel listModel2 = tblTrabajos.getSelectionModel();
        listModel2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tblTrabajos.getSelectedRow() != -1) {
                    trabajosTratamientoSeleccionado = listTrabajos.get(tblTrabajos.getSelectedRow());

                    if (trabajosTratamientoSeleccionado.getTrabajoTratamientoEnum() == TrabajoTratamientoEnum.P) {
                        btnDebitar.setEnabled(true);
                    } else {
                        btnDebitar.setEnabled(false);
                    }
                } else {
                    btnDebitar.setEnabled(false);
                }
            }
        });

    }

    private void confirmarPlanTratamiento() {

        if (planTratamientoSeleccionado.getSituacionPlanTratamientoEnum() == SituacionPlanTratamientoEnum.PRESUPUESTO) {
            planTratamientoSeleccionado.setSituacionPlanTratamientoEnum(SituacionPlanTratamientoEnum.CONFIRMA_PRESUPUESTO);

            ConfirmaTratamiento confirmacion = new ConfirmaTratamiento(this, true);
            confirmacion.setVisible(true);
            confirmacion.toFront();

            if (confirmacion.getFechaConfirmacion() != null) {
                planTratamientoSeleccionado.setFechaConfirmacion(confirmacion.getFechaConfirmacion());
                planTratamientoDAO.save(planTratamientoSeleccionado);

                JOptionPane.showMessageDialog(null, "Presupuesto confirmado!", "Correcto", JOptionPane.INFORMATION_MESSAGE);
            }
            buscarPlanesPaciente();
        }

    }

    private void debitarTrabajo() {

        if (planTratamientoSeleccionado.getSituacionPlanTratamientoEnum() == SituacionPlanTratamientoEnum.PRESUPUESTO) {

            JOptionPane.showMessageDialog(null, "No se pueden debitar trabajos de un plan que no esta confirmado", "Atención", JOptionPane.ERROR_MESSAGE);

        } else {

            ConfirmaTratamiento confirmacion = new ConfirmaTratamiento(this, true);
            confirmacion.setVisible(true);
            confirmacion.toFront();

                if (confirmacion.getFechaConfirmacion() != null) {
                    planTratamientoSeleccionado = listPlanTratamientos.get(tblPlanesTratamiento.getSelectedRow());
                    trabajosTratamientoSeleccionado = listTrabajos.get(tblTrabajos.getSelectedRow());
                    
                    trabajosTratamientoSeleccionado.setPlanTratamiento(planTratamientoSeleccionado);
                    trabajosTratamientoSeleccionado.setFechaDebito(confirmacion.getFechaConfirmacion());
                    trabajosTratamientoSeleccionado.setTrabajoTratamientoEnum(TrabajoTratamientoEnum.D);
                    trabajosTratamientoDAO.saveAndFlush(trabajosTratamientoSeleccionado);

                    CuentaPaciente cp = new CuentaPaciente();
                    cp.setDebe(new BigDecimal(trabajosTratamientoSeleccionado.getValor()));
                    cp.setFechaMovimiento(confirmacion.getFechaConfirmacion());
                    cp.setHaber(BigDecimal.ZERO);
                    cp.setMoneda(planTratamientoSeleccionado.getMoneda());
                    cp.setPaciente(planTratamientoSeleccionado.getPaciente());
                    cp.setPlanTratamiento(planTratamientoSeleccionado);
                    cp.setTrabajoTratamiento(trabajosTratamientoSeleccionado);
                    cp.setObservacion("Tratamiento: " + trabajosTratamientoSeleccionado.getTrabajos().getNombre());
                    cp.setSaldo(BigDecimal.ZERO);

                    cuentaPacienteDAO.saveAndFlush(cp);

                    ajustaSaldos();
                    JOptionPane.showMessageDialog(null, "Trabajo debitado en la cuenta del paciente!", "Correcto", JOptionPane.INFORMATION_MESSAGE);
                    buscarPlanesPaciente();
                } else {
                    JOptionPane.showMessageDialog(null, "Transacción cancelada por usuario", "Atención", JOptionPane.ERROR);
                }
           
        }
    }

    private void devolverTrabajo() {

        CuentaPaciente findByTrabajosTratamiento = cuentaPacienteDAO.findByTrabajosTratamiento(trabajosTratamientoSeleccionado);
        if (findByTrabajosTratamiento == null) {
            trabajosTratamientoDAO.delete(trabajosTratamientoSeleccionado);
            JOptionPane.showMessageDialog(null, "Trabajo eliminado del plan", "Correcto", JOptionPane.INFORMATION_MESSAGE);
            buscarPlanesPaciente();
        } else {

            ConfirmaTratamiento confirmacion = new ConfirmaTratamiento(this, true);
            confirmacion.setVisible(true);
            confirmacion.toFront();

            trabajosTratamientoSeleccionado.setFechaDebito(null);

            CuentaPaciente cp = new CuentaPaciente();
            cp.setDebe(BigDecimal.ZERO);
            cp.setFechaMovimiento(confirmacion.getFechaConfirmacion());
            cp.setHaber(new BigDecimal(trabajosTratamientoSeleccionado.getValor()));
            cp.setMoneda(planTratamientoSeleccionado.getMoneda());
            cp.setPaciente(planTratamientoSeleccionado.getPaciente());
            cp.setPlanTratamiento(planTratamientoSeleccionado);
            cp.setTrabajoTratamiento(trabajosTratamientoSeleccionado);
            cp.setObservacion("Devolución " + trabajosTratamientoSeleccionado.getTrabajos().getNombre());
            cp.setSaldo(BigDecimal.ZERO);

            cuentaPacienteDAO.save(cp);

            trabajosTratamientoSeleccionado.setTrabajoTratamientoEnum(TrabajoTratamientoEnum.P);
            trabajosTratamientoDAO.save(trabajosTratamientoSeleccionado);

            ajustaSaldos();
            JOptionPane.showMessageDialog(null, "Trabajo anulado de la cuenta del paciente!", "Correcto", JOptionPane.INFORMATION_MESSAGE);
            buscarPlanesPaciente();
        }
    }

    void ajustaSaldos() {
        List<CuentaPaciente> todos = cuentaPacienteDAO.findByPacienteAndMonedaOrderFechaMovimientoAsc(pacienteSeleccionado, planTratamientoSeleccionado.getMoneda());
        BigDecimal saldo = new BigDecimal(BigInteger.ZERO);

        for (CuentaPaciente mov : todos) {
            saldo = saldo.add(mov.getHaber().subtract(mov.getDebe()));
            mov.setSaldo(saldo);

            cuentaPacienteDAO.save(mov);

        }
    }

    public void reporte() {

        try {
            HashMap parametros = new HashMap();
            parametros.clear();
            parametros.put("paciente_id", pacienteSeleccionado.getId());

            Connection conexion = DriverManager.getConnection(props.getUrl(), props.getUsr(), props.getPsw());

            InputStream resource = getClass().getClassLoader().getResourceAsStream("reportes/hist_paciente.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(resource, parametros, conexion);
            JasperViewer reporte = new JasperViewer(jasperPrint, false);
            reporte.setVisible(true);

            reporte.toFront();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! " + e, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cbPaciente = new javax.swing.JComboBox();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPlanesTratamiento = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTrabajos = new javax.swing.JTable();
        btnDebitar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnConfirmar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnHistoria = new javax.swing.JButton();
        btnTrabajoProveedor = new javax.swing.JButton();
        btnFotos = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setTitle("Consultorio Odontológico - D.N.Soft .-");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Planes de tratamientos");
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel3.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel11.setLayout(new java.awt.GridBagLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Paciente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel11.add(jLabel7, gridBagConstraints);

        cbPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPacienteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel11.add(cbPaciente, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        jPanel3.add(jPanel11, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        tblPlanesTratamiento.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tblPlanesTratamiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tblPlanesTratamiento.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblPlanesTratamiento);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel12.add(jScrollPane1, gridBagConstraints);

        tblTrabajos.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tblTrabajos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane2.setViewportView(tblTrabajos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel12.add(jScrollPane2, gridBagConstraints);

        btnDebitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add.png"))); // NOI18N
        btnDebitar.setText("Debitar trabajo");
        btnDebitar.setEnabled(false);
        btnDebitar.setName(""); // NOI18N
        btnDebitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDebitarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel12.add(btnDebitar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jPanel12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/accept.png"))); // NOI18N
        btnConfirmar.setMnemonic('R');
        btnConfirmar.setText("Confirmar presupuesto");
        btnConfirmar.setEnabled(false);
        btnConfirmar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(btnConfirmar, gridBagConstraints);

        btnModificar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editar_normal.png"))); // NOI18N
        btnModificar.setMnemonic('R');
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(btnModificar, gridBagConstraints);

        btnHistoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/medical-history24.png"))); // NOI18N
        btnHistoria.setMnemonic('R');
        btnHistoria.setText(" Historia");
        btnHistoria.setEnabled(false);
        btnHistoria.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnHistoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoriaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(btnHistoria, gridBagConstraints);

        btnTrabajoProveedor.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnTrabajoProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/denturex24.png"))); // NOI18N
        btnTrabajoProveedor.setMnemonic('R');
        btnTrabajoProveedor.setText("Solicitar trabajo");
        btnTrabajoProveedor.setEnabled(false);
        btnTrabajoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrabajoProveedorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(btnTrabajoProveedor, gridBagConstraints);

        btnFotos.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnFotos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/camera-icon-24.png"))); // NOI18N
        btnFotos.setMnemonic('R');
        btnFotos.setText("Fotos");
        btnFotos.setEnabled(false);
        btnFotos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFotosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(btnFotos, gridBagConstraints);

        btnImprimir.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/medical-history24.png"))); // NOI18N
        btnImprimir.setMnemonic('R');
        btnImprimir.setText(" Imprimir ");
        btnImprimir.setEnabled(false);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(btnImprimir, gridBagConstraints);

        btnEliminar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete.png"))); // NOI18N
        btnEliminar.setMnemonic('R');
        btnEliminar.setText("Eliminar plan");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(btnEliminar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbCodPlanTratamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCodPlanTratamientoActionPerformed

    }//GEN-LAST:event_rbCodPlanTratamientoActionPerformed

    private void rbPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPacienteActionPerformed


    }//GEN-LAST:event_rbPacienteActionPerformed

    private void btnAtenderPlanTratamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtenderPlanTratamientoActionPerformed

    }//GEN-LAST:event_btnAtenderPlanTratamientoActionPerformed

    private void btnExportarPlanTratamientoXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarPlanTratamientoXMLActionPerformed


    }//GEN-LAST:event_btnExportarPlanTratamientoXMLActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed

        confirmarPlanTratamiento();

    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

        consultarPlanTratamiento();

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnHistoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoriaActionPerformed

        AgregarHistoriaPaciente historiaPaciente = new AgregarHistoriaPaciente(null, true, planTratamientoSeleccionado, this);
        historiaPaciente.setVisible(true);
        historiaPaciente.toFront();

    }//GEN-LAST:event_btnHistoriaActionPerformed

    private void btnTrabajoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrabajoProveedorActionPerformed

        TrabajoProveedor trabajoProveedor = new TrabajoProveedor(this, true, planTratamientoSeleccionado);
        trabajoProveedor.setVisible(true);
        trabajoProveedor.toFront();

    }//GEN-LAST:event_btnTrabajoProveedorActionPerformed

    private void btnFotosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFotosActionPerformed
        FotosTratamientoView fotos = new FotosTratamientoView(planTratamientoSeleccionado);
        fotos.setVisible(true);
        fotos.toFront();

    }//GEN-LAST:event_btnFotosActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        reporte();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void cbPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPacienteActionPerformed

    }//GEN-LAST:event_cbPacienteActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        List<TrabajosTratamiento> trabajosTratamientoList = planTratamientoSeleccionado.getTrabajosTratamientoList();
        int verificador = 0;
        for (TrabajosTratamiento trabajo : trabajosTratamientoList) {
            if (trabajo.getTrabajoTratamientoEnum() == TrabajoTratamientoEnum.D) {
                verificador = 1;
            }
        }
        if (verificador == 1) {
            JOptionPane.showMessageDialog(null, "No se puede elminar el plan, posee trabajos debidatos", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                trabajosTratamientoDAO.delete(trabajosTratamientoList);
                historiaPlanTratamientoDAO.delete(historiaPlanTratamientoDAO.findByPlanTratamientoOrderByFechaDesc(planTratamientoSeleccionado));
                planTratamientoDAO.delete(planTratamientoSeleccionado);
                JOptionPane.showMessageDialog(null, "Plan eliminado correctamente!", "Eliminado", JOptionPane.INFORMATION_MESSAGE);
            } catch (DataIntegrityViolationException e) {
                JOptionPane.showMessageDialog(null, "No se puede elminar el plan, posee movimiento en la cuenta del paciente", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }

        }
        buscarPlanesPaciente();

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnDebitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDebitarActionPerformed
        debitarTrabajo();
    }//GEN-LAST:event_btnDebitarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnDebitar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFotos;
    private javax.swing.JButton btnHistoria;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnTrabajoProveedor;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JComboBox cbPaciente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblPlanesTratamiento;
    private javax.swing.JTable tblTrabajos;
    // End of variables declaration//GEN-END:variables

}
