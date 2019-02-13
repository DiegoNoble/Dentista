package com.dnsoft.dentista.views;

import com.dnsoft.dentista.beans.Consulta;
import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.daos.IConsultaDAO;
import com.dnsoft.dentista.daos.IPacienteDAO;
import com.dnsoft.dentista.renderers.LocalDateCellRenderer;
import com.dnsoft.dentista.renderers.TabelaTextAreaRenderer;
import com.dnsoft.dentista.renderers.TableRendererColorAgenda;
import com.dnsoft.dentista.tablemodels.HistoriaConsultasTableModel;
import com.dnsoft.dentista.utiles.Container;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public final class HistoriaConsultasView extends javax.swing.JFrame {

    List<Paciente> listPacientes;
    HistoriaConsultasTableModel tableModel;
    List<Consulta> listAgenda = new ArrayList<Consulta>();
    Consulta seleccionado;
    IPacienteDAO pacienteDAO;
    IConsultaDAO consultasDAO;
    Container container;
    Paciente pacienteSeleccionado;

    public HistoriaConsultasView() {
        initComponents();
        inicio();
        cargaComboPacientes();
        buscaConsultas();
    }

    public HistoriaConsultasView(Paciente pacienteSeleccioando) {
        initComponents();
        inicio();
        cbPaciente.addItem(pacienteSeleccioando);
        buscaConsultas();
    }

    void inicio() {
        container = Container.getInstancia();
        pacienteDAO = container.getBean(IPacienteDAO.class);
        consultasDAO = container.getBean(IConsultaDAO.class);
        setLocationRelativeTo(null);
        defineModelo();

        cbPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscaConsultas();
            }
        });

    }

    void buscaConsultas() {
        listAgenda.clear();
        listAgenda.addAll(consultasDAO.findByPacienteOrderByFechaConsultaDesc((Paciente) cbPaciente.getSelectedItem()));
        tableModel.fireTableDataChanged();

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

        PlanTratamientoView atenderPlanTratamiento = new PlanTratamientoView(seleccionado);
        atenderPlanTratamiento.setVisible(true);
        atenderPlanTratamiento.toFront();

    }

    

    private void defineModelo() {
        ((DefaultTableCellRenderer) tbl.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        tableModel = new HistoriaConsultasTableModel(listAgenda);
        tbl.setModel(tableModel);

        tbl.getColumn("Fecha").setCellRenderer(new LocalDateCellRenderer());
        tbl.getColumn("Comentarios").setCellRenderer(new TabelaTextAreaRenderer());
        tbl.getColumn("Situación").setCellRenderer(new TableRendererColorAgenda(3));

        tbl.setRowHeight(40);
        int[] anchos = {5, 5, 400, 40};

        for (int i = 0; i < tbl.getColumnCount(); i++) {

            tbl.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }

        ListSelectionModel listModel = tbl.getSelectionModel();
        listModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tbl.getSelectedRow() != -1) {
                    seleccionado = listAgenda.get(tbl.getSelectedRow());

                }

            }
        });

        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {

                if (me.getClickCount() == 2) {
                    consultarPlanTratamiento();
                }
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
        jLabel7 = new javax.swing.JLabel();
        cbPaciente = new javax.swing.JComboBox();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnHistoria = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setTitle("Consultorio Odontológico - D.N.Soft .-");
        setPreferredSize(new java.awt.Dimension(850, 550));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Historia de consultas");
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

        tbl.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tbl.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel12.add(jScrollPane1, gridBagConstraints);

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

    private void btnHistoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoriaActionPerformed


    }//GEN-LAST:event_btnHistoriaActionPerformed

    private void cbPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPacienteActionPerformed
        buscaConsultas();

    }//GEN-LAST:event_cbPacienteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHistoria;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tbl;
    // End of variables declaration//GEN-END:variables

}
