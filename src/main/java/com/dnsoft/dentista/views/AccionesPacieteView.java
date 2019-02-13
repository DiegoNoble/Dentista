/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.views;

import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.controllers.DetalleCuentaPacientesController;
import com.dnsoft.dentista.daos.IPacienteDAO;
import com.dnsoft.dentista.utiles.Container;
import com.dnsoft.dentista.utiles.InternalFrameEstandar;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Diego
 */
public class AccionesPacieteView extends InternalFrameEstandar {

    IPacienteDAO pacienteDAO;
    Container container;
    Paciente pacienteSelecionado;
    JDesktopPane desktopPane;

    public AccionesPacieteView(JDesktopPane desktopPane) {
        initComponents();
        this.desktopPane = desktopPane;
        btnEnviarSMS.setVisible(false);
        btnAnamnesis.setVisible(false);
        btnTrabajosLaboratorio.setVisible(false);
        btnImagenes.setVisible(false);
        inicio();
    }

    void inicio() {
        this.container = Container.getInstancia();
        this.pacienteDAO = container.getBean(IPacienteDAO.class);

        configuraComboBox();

    }

    void configuraComboBox() {

        cbPacientes.removeAllItems();
        AutoCompleteDecorator.decorate(cbPacientes);
        for (Paciente paciente : pacienteDAO.findAll()) {
            cbPacientes.addItem(paciente);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        menuEntradas = new javax.swing.JPopupMenu();
        btnEnviarSMS = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        btnCuentas = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        btnTratamientos = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        btnOdontograma = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        btnImagenes = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        btnAnamnesis = new javax.swing.JMenuItem();
        jSeparator16 = new javax.swing.JPopupMenu.Separator();
        btnHistoriaConsultas = new javax.swing.JMenuItem();
        jSeparator17 = new javax.swing.JPopupMenu.Separator();
        btnProximaConsulta = new javax.swing.JMenuItem();
        jSeparator18 = new javax.swing.JPopupMenu.Separator();
        btnTrabajosLaboratorio = new javax.swing.JMenuItem();
        jSeparator19 = new javax.swing.JPopupMenu.Separator();
        btnModificarDatos = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbPacientes = new JComboBox<Paciente>();
        btnAcciones = new javax.swing.JButton();

        btnEnviarSMS.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnEnviarSMS.setText("Enviar SMS");
        btnEnviarSMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarSMSActionPerformed(evt);
            }
        });
        menuEntradas.add(btnEnviarSMS);
        menuEntradas.add(jSeparator1);

        btnCuentas.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnCuentas.setText("Cuenta");
        btnCuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuentasActionPerformed(evt);
            }
        });
        menuEntradas.add(btnCuentas);
        menuEntradas.add(jSeparator12);

        btnTratamientos.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnTratamientos.setText("Tratamientos y planes");
        btnTratamientos.setActionCommand("Tratamientos");
        btnTratamientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTratamientosActionPerformed(evt);
            }
        });
        menuEntradas.add(btnTratamientos);
        menuEntradas.add(jSeparator13);

        btnOdontograma.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnOdontograma.setText("Odontograma");
        btnOdontograma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOdontogramaActionPerformed(evt);
            }
        });
        menuEntradas.add(btnOdontograma);
        menuEntradas.add(jSeparator14);

        btnImagenes.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnImagenes.setText("Imagenes");
        menuEntradas.add(btnImagenes);
        menuEntradas.add(jSeparator15);

        btnAnamnesis.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnAnamnesis.setText("Anamnesis");
        menuEntradas.add(btnAnamnesis);
        menuEntradas.add(jSeparator16);

        btnHistoriaConsultas.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnHistoriaConsultas.setText("Historia de Consultas");
        btnHistoriaConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoriaConsultasActionPerformed(evt);
            }
        });
        menuEntradas.add(btnHistoriaConsultas);
        menuEntradas.add(jSeparator17);

        btnProximaConsulta.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnProximaConsulta.setText("Próxima consulta");
        btnProximaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProximaConsultaActionPerformed(evt);
            }
        });
        menuEntradas.add(btnProximaConsulta);
        menuEntradas.add(jSeparator18);

        btnTrabajosLaboratorio.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnTrabajosLaboratorio.setText("Trabajos de laboratorio");
        btnTrabajosLaboratorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrabajosLaboratorioActionPerformed(evt);
            }
        });
        menuEntradas.add(btnTrabajosLaboratorio);
        menuEntradas.add(jSeparator19);

        btnModificarDatos.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnModificarDatos.setText("Modificar Datos");
        btnModificarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarDatosActionPerformed(evt);
            }
        });
        menuEntradas.add(btnModificarDatos);

        setPreferredSize(new java.awt.Dimension(350, 130));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel3.setFont(new java.awt.Font("Arial", 3, 48)); // NOI18N
        jLabel3.setText("Pacientes");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(jLabel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Seleccione: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel5.add(jLabel1, gridBagConstraints);

        cbPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPacientesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(cbPacientes, gridBagConstraints);

        btnAcciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add.png"))); // NOI18N
        btnAcciones.setText("Funciones ");
        btnAcciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccionesActionPerformed(evt);
            }
        });
        jPanel5.add(btnAcciones, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel5, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarSMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarSMSActionPerformed

    }//GEN-LAST:event_btnEnviarSMSActionPerformed

    private void btnAccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccionesActionPerformed

        menuEntradas.show(btnAcciones, btnAcciones.getWidth() / 2, btnAcciones.getHeight() / 2);

    }//GEN-LAST:event_btnAccionesActionPerformed

    private void btnTratamientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTratamientosActionPerformed

        ConsultaPlanesTratamientoView consultaPlanesTratamientoView = new ConsultaPlanesTratamientoView(pacienteSelecionado);
        consultaPlanesTratamientoView.setVisible(true);
        consultaPlanesTratamientoView.toFront();

    }//GEN-LAST:event_btnTratamientosActionPerformed

    private void cbPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPacientesActionPerformed

        configuraComboBox();

        pacienteSelecionado = (Paciente) cbPacientes.getSelectedItem();

    }//GEN-LAST:event_cbPacientesActionPerformed

    private void btnCuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuentasActionPerformed

        DetalleMovimientosCuentaPaciente cCPacienteView = new DetalleMovimientosCuentaPaciente(null, true);
        DetalleCuentaPacientesController controller = new DetalleCuentaPacientesController(cCPacienteView, pacienteSelecionado);
        controller.go();
    }//GEN-LAST:event_btnCuentasActionPerformed

    private void btnOdontogramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOdontogramaActionPerformed
        Odontograma odontograma = new Odontograma();
        odontograma.toFront();
        odontograma.setVisible(true);
    }//GEN-LAST:event_btnOdontogramaActionPerformed

    private void btnHistoriaConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoriaConsultasActionPerformed

        HistoriaConsultasView consultasView = new HistoriaConsultasView(pacienteSelecionado);
        
        consultasView.setVisible(true);
        consultasView.toFront();

    }//GEN-LAST:event_btnHistoriaConsultasActionPerformed

    private void btnProximaConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximaConsultaActionPerformed

        AgendaView agenda = new AgendaView(pacienteSelecionado);
        desktopPane.add(agenda);
        agenda.setVisible(true);
        agenda.toFront();


    }//GEN-LAST:event_btnProximaConsultaActionPerformed

    private void btnTrabajosLaboratorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrabajosLaboratorioActionPerformed


    }//GEN-LAST:event_btnTrabajosLaboratorioActionPerformed

    private void btnModificarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarDatosActionPerformed

        PacienteDetalles detalles = new PacienteDetalles(null, true, pacienteSelecionado);
        detalles.toFront();
        detalles.setVisible(true);

    }//GEN-LAST:event_btnModificarDatosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcciones;
    public javax.swing.JMenuItem btnAnamnesis;
    public javax.swing.JMenuItem btnCuentas;
    public javax.swing.JMenuItem btnEnviarSMS;
    public javax.swing.JMenuItem btnHistoriaConsultas;
    public javax.swing.JMenuItem btnImagenes;
    public javax.swing.JMenuItem btnModificarDatos;
    public javax.swing.JMenuItem btnOdontograma;
    public javax.swing.JMenuItem btnProximaConsulta;
    public javax.swing.JMenuItem btnTrabajosLaboratorio;
    public javax.swing.JMenuItem btnTratamientos;
    private javax.swing.JComboBox cbPacientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator16;
    private javax.swing.JPopupMenu.Separator jSeparator17;
    private javax.swing.JPopupMenu.Separator jSeparator18;
    private javax.swing.JPopupMenu.Separator jSeparator19;
    public javax.swing.JPopupMenu menuEntradas;
    // End of variables declaration//GEN-END:variables
}
