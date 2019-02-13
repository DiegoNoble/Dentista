package com.dnsoft.dentista.views;

import com.dnsoft.dentista.beans.CuentaPaciente;
import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.beans.PlanTratamiento;
import com.dnsoft.dentista.beans.SituacionPlanTratamientoEnum;
import com.dnsoft.dentista.beans.TrabajosTratamiento;
import com.dnsoft.dentista.daos.ICuentaPacienteDAO;
import com.dnsoft.dentista.daos.IPacienteDAO;
import com.dnsoft.dentista.daos.IPlanTratamientoDAO;
import com.dnsoft.dentista.daos.ITrabajosDAO;
import com.dnsoft.dentista.renderers.LocalDateCellRenderer;
import com.dnsoft.dentista.renderers.TabelaTextAreaRenderer;
import com.dnsoft.dentista.renderers.TableRendererColorSituacionPlanTratamiento;
import com.dnsoft.dentista.tablemodels.PlanTratamientoTableModel;
import com.dnsoft.dentista.tablemodels.TrabajosTratamientoTableModel;
import com.dnsoft.dentista.utiles.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    IPlanTratamientoDAO planTratamientoDAO;
    Container container;
    Paciente pacienteSeleccionado;

    public ConsultaPlanesTratamientoView() {
        initComponents();
        inicio();
        cargaComboPacientes();
        btnModificar.setEnabled(false);
        buscarPlanesPaciente();
    }

    public ConsultaPlanesTratamientoView(Paciente pacienteSeleccioando) {
        initComponents();
        cbPaciente.addItem(pacienteSeleccioando);
        inicio();
        btnModificar.setEnabled(false);
        buscarPlanesPaciente();
    }

    void inicio() {
        container = Container.getInstancia();
        trabajosDAO = container.getBean(ITrabajosDAO.class);
        pacienteDAO = container.getBean(IPacienteDAO.class);
        planTratamientoDAO = container.getBean(IPlanTratamientoDAO.class);
        cuentaPacienteDAO = container.getBean(ICuentaPacienteDAO.class);
        setLocationRelativeTo(null);
        defineModelo();

        cbPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPlanesPaciente();
            }
        });

    }

    void buscarPlanesPaciente() {
        listPlanTratamientos.clear();
        listPlanTratamientos.addAll(planTratamientoDAO.findByPaciente((Paciente) cbPaciente.getSelectedItem()));
        tableModelPlanTratamiento.fireTableDataChanged();

    }

    private void cargaComboPacientes() {
        AutoCompleteDecorator.decorate(cbPaciente);
        listPacientes = new ArrayList();
        listPacientes = pacienteDAO.findAll();
        cbPaciente.removeAllItems();

        for (Paciente p : listPacientes) {

            cbPaciente.addItem(p);
        }
    }

    void consultarPlanTratamiento() {

        PlanTratamientoView atenderPlanTratamiento = new PlanTratamientoView(planTratamientoSeleccionado);
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
        int[] anchos = {5, 5, 5, 5, 5, 400, 40};

        for (int i = 0; i < tblPlanesTratamiento.getColumnCount(); i++) {

            tblPlanesTratamiento.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }

        int[] anchos2 = {10, 100, 10};

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
                    listTrabajos.addAll((planTratamientoDAO.findPlanTratamientoFetch(planTratamientoSeleccionado.getId()).getTrabajosTratamientoList()));
                    tableModelTrabajos.fireTableDataChanged();
                    pacienteSeleccionado = planTratamientoSeleccionado.getPaciente();
                    btnFotos.setEnabled(true);
                    switch (planTratamientoSeleccionado.getSituacionPlanTratamientoEnum()) {
                        case PRESUPUESTO:
                            btnConfirmar.setEnabled(true);
                            btnModificar.setEnabled(true);
                            btnHistoria.setEnabled(false);
                            btnTrabajoProveedor.setEnabled(true);
                            break;
                        case CONFIRMA_PRESUPUESTO:
                            btnConfirmar.setEnabled(false);
                            btnModificar.setEnabled(true);
                            btnHistoria.setEnabled(true);
                            btnTrabajoProveedor.setEnabled(true);
                            break;
                        case TRATAMIENTO_EN_CURSO:
                            btnConfirmar.setEnabled(false);
                            btnModificar.setEnabled(true);
                            btnHistoria.setEnabled(true);
                            btnTrabajoProveedor.setEnabled(true);
                            break;
                        case TRATAMIENTO_FINALIZADO:
                            btnConfirmar.setEnabled(false);
                            btnModificar.setEnabled(false);
                            btnHistoria.setEnabled(true);
                            btnTrabajoProveedor.setEnabled(false);
                            break;
                        default:
                            throw new AssertionError();
                    }

                } else {
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

    }

    private void confirmarPlanTratamiento() {

        if (planTratamientoSeleccionado.getSituacionPlanTratamientoEnum() == SituacionPlanTratamientoEnum.PRESUPUESTO) {
            planTratamientoSeleccionado.setSituacionPlanTratamientoEnum(SituacionPlanTratamientoEnum.CONFIRMA_PRESUPUESTO);

            ConfirmaTratamiento confirmacion = new ConfirmaTratamiento(this, true);
            confirmacion.setVisible(true);
            confirmacion.toFront();

            planTratamientoSeleccionado.setFechaConfirmacion(confirmacion.getFechaConfirmacion());

            CuentaPaciente cp = new CuentaPaciente();
            cp.setDebe(new BigDecimal(planTratamientoSeleccionado.getValorTotal()));
            cp.setFechaMovimiento(confirmacion.getFechaConfirmacion());
            cp.setHaber(BigDecimal.ZERO);
            cp.setMoneda(planTratamientoSeleccionado.getMoneda());
            cp.setPaciente(planTratamientoSeleccionado.getPaciente());
            cp.setPlanTratamiento(planTratamientoSeleccionado);
            cp.setSaldo(BigDecimal.ZERO);

            cuentaPacienteDAO.save(cp);
            planTratamientoDAO.save(planTratamientoSeleccionado);
            ajustaSaldos(confirmacion.getFechaConfirmacion());
            JOptionPane.showMessageDialog(null, "Presupuesto confirmado!", "Correcto", JOptionPane.INFORMATION_MESSAGE);
            buscarPlanesPaciente();
        }

    }

    void ajustaSaldos(LocalDate fecha) {
        List<CuentaPaciente> todos = cuentaPacienteDAO.findByPacienteAndMonedaOrderFechaMovimientoAsc(pacienteSeleccionado, planTratamientoSeleccionado.getMoneda());
        BigDecimal saldo = new BigDecimal(BigInteger.ZERO);

        for (CuentaPaciente mov : todos) {
            saldo = saldo.add(mov.getHaber().subtract(mov.getDebe()));
            mov.setSaldo(saldo);

            cuentaPacienteDAO.save(mov);

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
        jPanel4 = new javax.swing.JPanel();
        btnConfirmar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnHistoria = new javax.swing.JButton();
        btnTrabajoProveedor = new javax.swing.JButton();
        btnFotos = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setTitle("Consultorio Odontológico - D.N.Soft .-");
        setPreferredSize(new java.awt.Dimension(1024, 600));
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

        btnConfirmar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/accept.png"))); // NOI18N
        btnConfirmar.setMnemonic('R');
        btnConfirmar.setText("Confirmar presupuesto");
        btnConfirmar.setEnabled(false);
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

        btnHistoria.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnHistoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/medical-history24.png"))); // NOI18N
        btnHistoria.setMnemonic('R');
        btnHistoria.setText(" Historia");
        btnHistoria.setEnabled(false);
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
        btnTrabajoProveedor.setText("Solicitar trabajo proveedor");
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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel4, gridBagConstraints);

        getAccessibleContext().setAccessibleName("Consultorio Odontológico - D.N.Soft .-");

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

        AgregarHistoriaPaciente historiaPaciente = new AgregarHistoriaPaciente(null, true, planTratamientoSeleccionado);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnFotos;
    private javax.swing.JButton btnHistoria;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnTrabajoProveedor;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbPaciente;
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
