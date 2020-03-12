package com.dnsoft.dentista.views;

import com.dnsoft.dentista.beans.Perfil;
import com.dnsoft.dentista.beans.PerfilEnum;
import com.dnsoft.dentista.beans.Usuario;
import com.dnsoft.dentista.daos.IPerfilDAO;
import com.dnsoft.dentista.utiles.Container;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class ConfPerfiles extends javax.swing.JInternalFrame {

    MaskFormatter formatoftxtFechaIngreso;
    Perfil perfil;
    IPerfilDAO perfilDAO;
    Usuario usuarioSeleccionado;
    Container container;

    public ConfPerfiles() {
        initComponents();
        this.container = Container.getInstancia();
        perfilDAO = container.getBean(IPerfilDAO.class);
        cbPerfil.setModel(new DefaultComboBoxModel(PerfilEnum.values()));

    }

    private void buscaTodosLosRegistros() {

        perfil = perfilDAO.findPerfil((PerfilEnum) cbPerfil.getSelectedItem());

    }

    private void muestraDetalles() {

        if (perfil != null) {

            cbPerfil.setSelectedItem(perfil.getPerfilEnum());
            regitroMediosDePago.setSelected(perfil.getConsultas());
            Consultas.setSelected(perfil.getConsultas());
            Movimientos.setSelected(perfil.getMovimientos());
            Pacientes.setSelected(perfil.getPacientes());
            agenda.setSelected(perfil.getAgenda());
            consultarTrabajos.setSelected(perfil.getConsultarTrabajos());
            consultardeudores.setSelected(perfil.getConsultarDeudores());
            consultartratamientos.setSelected(perfil.getConsultarTratamientos());
            crearplantratamiento.setSelected(perfil.getCrearPlanTratamiento());
            cuentadepacientes.setSelected(perfil.getCuentadePacientes());
            parametros.setSelected(perfil.getParametros());
            permisos.setSelected(perfil.getPermisos());
            registrarproveedor.setSelected(perfil.getRegistrarProveedor());
            respaldos.setSelected(perfil.getRespaldos());
            trabajosPendientes.setSelected(perfil.getTrabajosPendientes());
            tratamientos.setSelected(perfil.getTratamientos());
            usuarios.setSelected(perfil.getUsuarios());
            clasificaTratamientos.setSelected(perfil.getClasificaTratamientos());

        }else{
            
            regitroMediosDePago.setSelected(false);
            Consultas.setSelected(false);
            Movimientos.setSelected(false);
            Pacientes.setSelected(false);
            agenda.setSelected(false);
            consultarTrabajos.setSelected(false);
            consultardeudores.setSelected(false);
            consultartratamientos.setSelected(false);
            crearplantratamiento.setSelected(false);
            cuentadepacientes.setSelected(false);
            parametros.setSelected(false);
            permisos.setSelected(false);
            registrarproveedor.setSelected(false);
            respaldos.setSelected(false);
            trabajosPendientes.setSelected(false);
            tratamientos.setSelected(false);
            usuarios.setSelected(false);
            clasificaTratamientos.setSelected(false);
            
        }
            
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbPerfil = new javax.swing.JComboBox();
        Pacientes = new javax.swing.JCheckBox();
        tratamientos = new javax.swing.JCheckBox();
        clasificaTratamientos = new javax.swing.JCheckBox();
        cuentadepacientes = new javax.swing.JCheckBox();
        crearplantratamiento = new javax.swing.JCheckBox();
        agenda = new javax.swing.JCheckBox();
        consultartratamientos = new javax.swing.JCheckBox();
        consultardeudores = new javax.swing.JCheckBox();
        usuarios = new javax.swing.JCheckBox();
        permisos = new javax.swing.JCheckBox();
        parametros = new javax.swing.JCheckBox();
        respaldos = new javax.swing.JCheckBox();
        Consultas = new javax.swing.JCheckBox();
        consultarTrabajos = new javax.swing.JCheckBox();
        Movimientos = new javax.swing.JCheckBox();
        registrarproveedor = new javax.swing.JCheckBox();
        trabajosPendientes = new javax.swing.JCheckBox();
        regitroMediosDePago = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setPreferredSize(new java.awt.Dimension(600, 400));
        setRequestFocusEnabled(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 400));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setText("Perfiles"); // NOI18N
        jLabel1.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Perfil"); // NOI18N
        jPanel2.add(jLabel4, new java.awt.GridBagConstraints());

        cbPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPerfilActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(cbPerfil, gridBagConstraints);

        Pacientes.setText("Pacientes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(Pacientes, gridBagConstraints);

        tratamientos.setText("Tratamientos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(tratamientos, gridBagConstraints);

        clasificaTratamientos.setText("Clasifica Tratamientos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(clasificaTratamientos, gridBagConstraints);

        cuentadepacientes.setText("Cuentas de Pacientes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(cuentadepacientes, gridBagConstraints);

        crearplantratamiento.setText("Crear plan tratamiento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(crearplantratamiento, gridBagConstraints);

        agenda.setText("Agenda");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(agenda, gridBagConstraints);

        consultartratamientos.setText("Consulta tratamientos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(consultartratamientos, gridBagConstraints);

        consultardeudores.setText("Consultar deudores");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(consultardeudores, gridBagConstraints);

        usuarios.setText("Usuarios");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(usuarios, gridBagConstraints);

        permisos.setText("Permisos");
        permisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                permisosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(permisos, gridBagConstraints);

        parametros.setText("Parametros");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(parametros, gridBagConstraints);

        respaldos.setText("Respaldos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(respaldos, gridBagConstraints);

        Consultas.setText("Consultas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(Consultas, gridBagConstraints);

        consultarTrabajos.setText("Consultar trabajos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(consultarTrabajos, gridBagConstraints);

        Movimientos.setText("Movimientos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(Movimientos, gridBagConstraints);

        registrarproveedor.setText("Registrar proveedor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(registrarproveedor, gridBagConstraints);

        trabajosPendientes.setText("Trabajos pendientes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(trabajosPendientes, gridBagConstraints);

        regitroMediosDePago.setText("Registro medios de Pago");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(regitroMediosDePago, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnSalvar.setText("Guardar cambios"); // NOI18N
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel4.add(btnSalvar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        
        if (perfil == null) {
            perfil = new Perfil();
        }
        perfil.setPerfilEnum((PerfilEnum) cbPerfil.getSelectedItem());
        perfil.setRegistraMediosDePago(regitroMediosDePago.isSelected());
        perfil.setConsultas(Consultas.isSelected());
        perfil.setMovimientos(Movimientos.isSelected());
        perfil.setPacientes(Pacientes.isSelected());
        perfil.setAgenda(agenda.isSelected());
        perfil.setConsultarTrabajos(consultarTrabajos.isSelected());
        perfil.setConsultarDeudores(consultardeudores.isSelected());
        perfil.setConsultarTratamientos(consultartratamientos.isSelected());
        perfil.setCrearPlanTratamiento(crearplantratamiento.isSelected());
        perfil.setCuentadePacientes(cuentadepacientes.isSelected());
        perfil.setParametros(parametros.isSelected());
        perfil.setPermisos(permisos.isSelected());
        perfil.setRegistrarProveedor(registrarproveedor.isSelected());
        perfil.setRespaldos(respaldos.isSelected());
        perfil.setTrabajosPendientes(trabajosPendientes.isSelected());
        perfil.setTratamientos(tratamientos.isSelected());
        perfil.setUsuarios(usuarios.isSelected());
        perfil.setClasificaTratamientos(clasificaTratamientos.isSelected());

        perfilDAO.saveAndFlush(perfil);

        JOptionPane.showMessageDialog(null, "Registrado correctamente");


    }//GEN-LAST:event_btnSalvarActionPerformed

    private void permisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_permisosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_permisosActionPerformed

    private void cbPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPerfilActionPerformed
        
        buscaTodosLosRegistros();
        muestraDetalles();

    }//GEN-LAST:event_cbPerfilActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Consultas;
    private javax.swing.JCheckBox Movimientos;
    private javax.swing.JCheckBox Pacientes;
    private javax.swing.JCheckBox agenda;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox cbPerfil;
    private javax.swing.JCheckBox clasificaTratamientos;
    private javax.swing.JCheckBox consultarTrabajos;
    private javax.swing.JCheckBox consultardeudores;
    private javax.swing.JCheckBox consultartratamientos;
    private javax.swing.JCheckBox crearplantratamiento;
    private javax.swing.JCheckBox cuentadepacientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JCheckBox parametros;
    private javax.swing.JCheckBox permisos;
    private javax.swing.JCheckBox registrarproveedor;
    private javax.swing.JCheckBox regitroMediosDePago;
    private javax.swing.JCheckBox respaldos;
    private javax.swing.JCheckBox trabajosPendientes;
    private javax.swing.JCheckBox tratamientos;
    private javax.swing.JCheckBox usuarios;
    // End of variables declaration//GEN-END:variables
}
