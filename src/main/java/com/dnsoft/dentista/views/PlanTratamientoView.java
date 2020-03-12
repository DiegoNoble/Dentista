package com.dnsoft.dentista.views;

import com.dnsoft.dentista.beans.Consulta;
import com.dnsoft.dentista.beans.CuentaPaciente;
import com.dnsoft.dentista.beans.MonedaEnum;
import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.beans.PlanTratamiento;
import com.dnsoft.dentista.beans.SituacionPlanTratamientoEnum;
import com.dnsoft.dentista.beans.TrabajoTratamientoEnum;
import com.dnsoft.dentista.beans.Trabajos;
import com.dnsoft.dentista.beans.TrabajosTratamiento;
import com.dnsoft.dentista.daos.ICuentaPacienteDAO;
import com.dnsoft.dentista.daos.IPacienteDAO;
import com.dnsoft.dentista.daos.IPlanTratamientoDAO;
import com.dnsoft.dentista.daos.ITrabajosDAO;
import com.dnsoft.dentista.daos.ITrabajosTratamientoDAO;
import com.dnsoft.dentista.tablemodels.TrabajosTratamientoTableModel;
import com.dnsoft.dentista.tablemodels.TrabajosTratamientoTableModel2;
import com.dnsoft.dentista.utiles.Container;
import com.dnsoft.dentista.utiles.ControlarEntradaTexto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class PlanTratamientoView extends javax.swing.JFrame {

    List<Paciente> listPacientes;
    List<Trabajos> listTrabajos;
    TrabajosTratamientoTableModel2 tableModel;
    List<TrabajosTratamiento> listTrabajosTratamiento;
    List<TrabajosTratamiento> listTrabajosTratamientoToRemove;
    PlanTratamiento plan;
    ITrabajosDAO trabajosDAO;
    IPacienteDAO pacienteDAO;
    ITrabajosTratamientoDAO trabajosTratamientoDAO;
    IPlanTratamientoDAO planTratamientoDAO;
    ICuentaPacienteDAO cuentaPacienteDAO;
    Paciente pacienteSeleccionado;
    Container container;
    Consulta consulta;
    ConsultaPlanesTratamientoView consultaPlanesTratamientoView;
    private static PlanTratamientoView instanciaUnica = null;

    public PlanTratamientoView() {
        initComponents();
        plan = new PlanTratamiento();
        AutoCompleteDecorator.decorate(cbPaciente);
        AutoCompleteDecorator.decorate(cbTrabajo);
        cbMoneda.setModel(new DefaultComboBoxModel(MonedaEnum.values()));
        inicio();
        
        cargaComboPacientes();
        btnRegistraModificaciones.setVisible(false);
        cbSituacionTratamiento.setEnabled(false);

    }

    public PlanTratamientoView(Consulta consulta) {
        initComponents();
        this.consulta = consulta;

        plan = new PlanTratamiento();
        AutoCompleteDecorator.decorate(cbTrabajo);
        cbPaciente.addItem(consulta.getPaciente());
        inicio();
        cbMoneda.setModel(new DefaultComboBoxModel(MonedaEnum.values()));
        cbSituacionTratamiento.setEnabled(false);
        btnRegistraModificaciones.setVisible(false);
    }

    public PlanTratamientoView(PlanTratamiento planTratamiento, ConsultaPlanesTratamientoView consultaPlanesTratamientoView) {
        initComponents();
        this.plan = planTratamiento;
        this.consultaPlanesTratamientoView = consultaPlanesTratamientoView;
        btnRegistraVenta.setVisible(false);
        if (plan.getMoneda() == MonedaEnum.DOLARES) {
            cbMoneda.addItem(MonedaEnum.DOLARES);
            cbMoneda.setSelectedItem(MonedaEnum.DOLARES);
        } else {
            cbMoneda.addItem(MonedaEnum.PESOS);
            cbMoneda.setSelectedItem(MonedaEnum.PESOS);
        }

        cbPaciente.addItem(planTratamiento.getPaciente());
        cbPaciente.setEnabled(false);
        //AutoCompleteDecorator.decorate(cbTrabajo);
        inicio();
        List<TrabajosTratamiento> findByPlanTratamiento = trabajosTratamientoDAO.findByPlanTratamiento(planTratamiento);
        dpFecha.setDate(planTratamiento.getFechaCreacion());
        txtObservaciones.setText(planTratamiento.getObservaciones());
        cbSituacionTratamiento.setSelectedItem(planTratamiento.getSituacionPlanTratamientoEnum());
        cbSituacionTratamiento.setEnabled(false);

        txtTotal.setText(planTratamiento.getValorTotal().toString());

        listTrabajosTratamiento.addAll(findByPlanTratamiento);
        tableModel.fireTableDataChanged();
    }

     public static PlanTratamientoView getInstancia() {

        if (instanciaUnica == null) {
            instanciaUnica = new PlanTratamientoView();
        }
        return instanciaUnica;
    }
     
    void inicio() {
        setLocationRelativeTo(null);

        dpFecha.setDate(LocalDate.now());
        container = Container.getInstancia();
        trabajosDAO = container.getBean(ITrabajosDAO.class);
        planTratamientoDAO = container.getBean(IPlanTratamientoDAO.class);
        trabajosTratamientoDAO = container.getBean(ITrabajosTratamientoDAO.class);
        pacienteDAO = container.getBean(IPacienteDAO.class);
        cuentaPacienteDAO = container.getBean(ICuentaPacienteDAO.class);
        cbSituacionTratamiento.setModel(new DefaultComboBoxModel(SituacionPlanTratamientoEnum.values()));
        defineModelo();
        cargaComboTrabajos();
        comboMonedaListener();

    }

    private void cargaComboPacientes() {

        listPacientes = new ArrayList();
        listPacientes = pacienteDAO.findAll();
        cbPaciente.removeAllItems();

        for (Paciente p : listPacientes) {

            cbPaciente.addItem(p);
        }
    }

    void cargaComboTrabajos() {

        cbTrabajo.removeAllItems();
        MonedaEnum moneda = (MonedaEnum) cbMoneda.getSelectedItem();
        listTrabajos = trabajosDAO.findByMoneda((MonedaEnum) cbMoneda.getSelectedItem());

        for (Trabajos t : listTrabajos) {

            cbTrabajo.addItem(t);
        }

    }

    private void defineModelo() {

        Character chs[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'};
        txtValor.setDocument(new ControlarEntradaTexto(10, chs));

        ((DefaultTableCellRenderer) tblArticulosPedido.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        listTrabajosTratamiento = new ArrayList<TrabajosTratamiento>();
        listTrabajosTratamientoToRemove = new ArrayList<TrabajosTratamiento>();
        tableModel = new TrabajosTratamientoTableModel2(listTrabajosTratamiento, txtTotal);

        tblArticulosPedido.setModel(tableModel);

        int[] anchos = {20, 60, 10};

        for (int i = 0; i < tblArticulosPedido.getColumnCount(); i++) {

            tblArticulosPedido.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }

        ListSelectionModel listModel = tblArticulosPedido.getSelectionModel();
        listModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tblArticulosPedido.getSelectedRow() != -1) {
                    btnEliminar.setEnabled(true);
                } else {
                    btnEliminar.setEnabled(false);
                }
            }
        });

    }

    private void registrarTratamiento() {

        try {
            if (listTrabajosTratamiento.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un trabajo", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                //plan = new PlanTratamiento();
                plan.setConsulta(consulta);
                plan.setSituacionPlanTratamientoEnum((SituacionPlanTratamientoEnum) cbSituacionTratamiento.getSelectedItem());
                plan.setMoneda((MonedaEnum) cbMoneda.getSelectedItem());

                plan.setObservaciones(txtObservaciones.getText());
                Double total = 0.0;
                for (TrabajosTratamiento trabajos : listTrabajosTratamiento) {
                    trabajos.setPlanTratamiento(plan);
                    total = total + trabajos.getValor();

                }
                plan.setPaciente((Paciente) cbPaciente.getSelectedItem());
                plan.setTrabajosTratamientoList(listTrabajosTratamiento);
                plan.setValorTotal(total);

                plan.setFechaCreacion(dpFecha.getDate());
                planTratamientoDAO.save(plan);

            }
            JOptionPane.showMessageDialog(this, "Plan de tratamiento registrado correctamente!", "Información", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al salvar en base de datos: " + ex);
        }
    }

    private void registrarModificaciones() {

        try {
            if (listTrabajosTratamiento.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un trabajo", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                //plan = new PlanTratamiento();
                plan.setConsulta(consulta);
                plan.setSituacionPlanTratamientoEnum((SituacionPlanTratamientoEnum) cbSituacionTratamiento.getSelectedItem());
                plan.setMoneda((MonedaEnum) cbMoneda.getSelectedItem());

                plan.setObservaciones(txtObservaciones.getText());
                Double total = 0.0;
                for (TrabajosTratamiento trabajos : listTrabajosTratamiento) {
                    trabajos.setPlanTratamiento(plan);
                    total = total + trabajos.getValor();

                }
                plan.setPaciente((Paciente) cbPaciente.getSelectedItem());
                plan.setTrabajosTratamientoList(listTrabajosTratamiento);
                plan.setValorTotal(total);

                plan.setFechaCreacion(dpFecha.getDate());
                planTratamientoDAO.save(plan);

                if (!listTrabajosTratamientoToRemove.isEmpty()) {
                    trabajosTratamientoDAO.delete(listTrabajosTratamientoToRemove);
                }

                /*if (plan.getSituacionPlanTratamientoEnum() == SituacionPlanTratamientoEnum.CONFIRMA_PRESUPUESTO) {

                    CuentaPaciente cuentaPlanTratamiento = cuentaPacienteDAO.findByPlanTratamiento(plan);
                    cuentaPlanTratamiento.setDebe(new BigDecimal(plan.getValorTotal()));
                    cuentaPlanTratamiento.setSaldo(BigDecimal.ZERO);
                    cuentaPacienteDAO.save(cuentaPlanTratamiento);

                    ajustaSaldos(plan.getFechaConfirmacion());

                } else if (plan.getSituacionPlanTratamientoEnum() == SituacionPlanTratamientoEnum.TRATAMIENTO_EN_CURSO) {

                    CuentaPaciente cuentaPlanTratamiento = cuentaPacienteDAO.findByPlanTratamiento(plan);
                    cuentaPlanTratamiento.setDebe(new BigDecimal(plan.getValorTotal()));
                    cuentaPlanTratamiento.setSaldo(BigDecimal.ZERO);
                    cuentaPacienteDAO.save(cuentaPlanTratamiento);

                    ajustaSaldos(plan.getFechaConfirmacion());

                }*/
            }
            JOptionPane.showMessageDialog(this, "Plan de tratamiento modificado correctamente!", "Información", JOptionPane.INFORMATION_MESSAGE);
            consultaPlanesTratamientoView.buscarPlanesPaciente();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al salvar en base de datos: " + ex);
        }
    }

    void ajustaSaldos(LocalDate fecha) {
        List<CuentaPaciente> todos = cuentaPacienteDAO.findByPacienteAndMonedaOrderFechaMovimientoAsc(plan.getPaciente(), (MonedaEnum) cbMoneda.getSelectedItem());
        BigDecimal saldo = new BigDecimal(BigInteger.ZERO);

        for (CuentaPaciente mov : todos) {
            saldo = saldo.add(mov.getHaber().subtract(mov.getDebe()));
            mov.setSaldo(saldo);

            cuentaPacienteDAO.save(mov);

        }
    }

    void limbiaCampo() {
        txtValor.setText("");
        txtTotal.setText("");
        txtObservaciones.setText("");
    }

    private void retirarArticulo() {

        if (tblArticulosPedido.getSelectedRow() != -1) {
            tableModel.eliminar(tblArticulosPedido.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item da lista!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void agregarArticuloPedido(TrabajosTratamiento trabajo) {
        if (listTrabajos.contains(trabajo)) {
            cbTrabajo.setSelectedItem(trabajo);
        } else {
            cargaComboTrabajos();
            cbTrabajo.setSelectedItem(trabajo);
        }
    }

    public void agregarCliente(Paciente paciente) {
        if (listPacientes.contains(paciente)) {
            cbPaciente.setSelectedItem(paciente);
        } else {
            cbPaciente.addItem(paciente);
            cbPaciente.setSelectedItem(paciente);
        }
    }

    void comboMonedaListener() {
        cbMoneda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargaComboTrabajos();
                listTrabajosTratamiento.clear();
                txtTotal.setText("");
                tableModel.fireTableDataChanged();
            }
        });
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
        cbPaciente = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        cbSituacionTratamiento = new javax.swing.JComboBox();
        cbMoneda = new javax.swing.JComboBox();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblArticulosPedido = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        cbTrabajo = new javax.swing.JComboBox();
        txtValor = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtPieza = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        dpFecha = new com.github.lgooddatepicker.components.DatePicker();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        btnRegistraVenta = new javax.swing.JButton();
        btnRegistraModificaciones = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();

        jTextField1.setText("jTextField1");

        setPreferredSize(new java.awt.Dimension(980, 450));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Plan de tratamiento");
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel11.setLayout(new java.awt.GridBagLayout());

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
        jPanel11.add(cbPaciente, gridBagConstraints);

        jLabel11.setText("Paciente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel11.add(jLabel11, gridBagConstraints);

        cbSituacionTratamiento.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        cbSituacionTratamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSituacionTratamientoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel11.add(cbSituacionTratamiento, gridBagConstraints);

        cbMoneda.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        cbMoneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMonedaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel11.add(cbMoneda, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jPanel11, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        tblArticulosPedido.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tblArticulosPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tblArticulosPedido.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblArticulosPedido);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jScrollPane1, gridBagConstraints);

        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel13.setLayout(new java.awt.GridBagLayout());

        cbTrabajo.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        cbTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTrabajoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel13.add(cbTrabajo, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel13.add(txtValor, gridBagConstraints);

        jLabel8.setText("Valor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel13.add(jLabel8, gridBagConstraints);

        jLabel13.setText("Trabajo: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel13.add(jLabel13, gridBagConstraints);

        jLabel14.setText("Pieza");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel13.add(jLabel14, gridBagConstraints);

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add.png"))); // NOI18N
        btnNuevo.setToolTipText("Nueva posición");
        btnNuevo.setBorder(null);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel13.add(btnNuevo, gridBagConstraints);

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar_chico.png"))); // NOI18N
        btnBuscar.setToolTipText("Buscar articulo");
        btnBuscar.setBorder(null);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel13.add(btnBuscar, gridBagConstraints);

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete.png"))); // NOI18N
        btnEliminar.setBorder(null);
        btnEliminar.setEnabled(false);
        btnEliminar.setToolTipText("Eliminar posición");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel13.add(btnEliminar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel13.add(txtPieza, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jPanel13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jPanel12, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel10.add(dpFecha, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel3.setText("Fecha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel10.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        jPanel3.add(jPanel10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel10.setText("Total tratamiento ");
        jPanel8.add(jLabel10, new java.awt.GridBagConstraints());

        txtTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("$ #,##0.00"))));
        txtTotal.setEnabled(false);
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 140;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel8.add(txtTotal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(jPanel8, gridBagConstraints);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnRegistraVenta.setMnemonic('R');
        btnRegistraVenta.setText("Registrar");
        btnRegistraVenta.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnRegistraVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistraVentaActionPerformed(evt);
            }
        });
        jPanel4.add(btnRegistraVenta);

        btnRegistraModificaciones.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnRegistraModificaciones.setMnemonic('R');
        btnRegistraModificaciones.setText("Registrar modificaciones");
        btnRegistraModificaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistraModificacionesActionPerformed(evt);
            }
        });
        jPanel4.add(btnRegistraModificaciones);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(jPanel4, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Observaciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setLayout(new java.awt.GridBagLayout());

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane2.setViewportView(txtObservaciones);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistraVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistraVentaActionPerformed

        registrarTratamiento();
        listTrabajosTratamiento.clear();
        tableModel.fireTableDataChanged();
        dispose();

    }//GEN-LAST:event_btnRegistraVentaActionPerformed

    private void cbTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTrabajoActionPerformed

        if (cbTrabajo.getSelectedItem() != null) {
            txtValor.setText(((Trabajos) cbTrabajo.getSelectedItem()).getValor().toString());
        }
    }//GEN-LAST:event_cbTrabajoActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

        Double valor = Double.valueOf(txtValor.getText());

        tableModel.agregar(new TrabajosTratamiento(valor, txtPieza.getText(), (Trabajos) cbTrabajo.getSelectedItem(), TrabajoTratamientoEnum.P));
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        /*TrabajosView view = new TrabajosView();

        TrabajosController controller = new TrabajosController(view, desktopPane);
        desktopPane.add(view);
        controller.go();
         */
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        listTrabajosTratamientoToRemove.add(listTrabajosTratamiento.get(tblArticulosPedido.getSelectedRow()));

        tableModel.eliminar(tblArticulosPedido.getSelectedRow());

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cbPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPacienteActionPerformed

    private void cbSituacionTratamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSituacionTratamientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSituacionTratamientoActionPerformed

    private void btnRegistraModificacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistraModificacionesActionPerformed
        registrarModificaciones();
        listTrabajosTratamiento.clear();
        tableModel.fireTableDataChanged();
        dispose();
    }//GEN-LAST:event_btnRegistraModificacionesActionPerformed

    private void cbMonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMonedaActionPerformed
        //cargaComboTrabajos();
/*        listTrabajosTratamiento.clear();
        txtTotal.setText("");
        tableModel.fireTableDataChanged();*/
    }//GEN-LAST:event_cbMonedaActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegistraModificaciones;
    private javax.swing.JButton btnRegistraVenta;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbMoneda;
    private javax.swing.JComboBox cbPaciente;
    private javax.swing.JComboBox cbSituacionTratamiento;
    private javax.swing.JComboBox cbTrabajo;
    private com.github.lgooddatepicker.components.DatePicker dpFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblArticulosPedido;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtPieza;
    private javax.swing.JFormattedTextField txtTotal;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables

}
