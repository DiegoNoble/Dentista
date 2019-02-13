package com.dnsoft.dentista.views;

import com.dnsoft.dentista.controllers.ClasePacientesController;
import com.dnsoft.dentista.controllers.ConsultaDeCajaController;
import com.dnsoft.dentista.controllers.ControlDeCajaController;
import com.dnsoft.dentista.controllers.DetalleCuentaPacientesController;
import com.dnsoft.dentista.controllers.PacientesController;
import com.dnsoft.dentista.controllers.ProveedorController;
import com.dnsoft.dentista.controllers.TrabajosController;
import com.dnsoft.dentista.utiles.DecorateDesktopPane;
import com.dnsoft.dentista.utiles.LeeProperties;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import org.springframework.stereotype.Component;

@Component
public final class MenuPrincipalView extends javax.swing.JFrame {

    public String perfil;
    DecorateDesktopPane desktopPane = new DecorateDesktopPane("/imagenes/fondo.jpg");
    //JDesktopPane desktopPane = new JDesktopPane();
    ImageIcon fot;
    ImageIcon icono;
    LeeProperties props = new LeeProperties();
    Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/diente.jpg"));

    public MenuPrincipalView() {
        initComponents();

        setSize(1060, 768);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        add(desktopPane);
        //imagenDeFondo();
        iniciar();

    }

    public void iniciar() {

        PacientesView view = new PacientesView();
        PacientesController controller = new PacientesController(view, desktopPane);
        desktopPane.add(view);
        controller.go();

        AgendaView agenda = new AgendaView();
        desktopPane.add(agenda);
        agenda.setLocation(0, 130);
        agenda.toFront();
        agenda.setVisible(true);

        RecoratorioTrabajosProveedor viewp = new RecoratorioTrabajosProveedor();
        desktopPane.add(viewp);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        mnuSistema = new javax.swing.JMenu();
        mnuCotizacion10 = new javax.swing.JMenuItem();
        mnuItemSesion1 = new javax.swing.JMenuItem();
        mnuItemSalir = new javax.swing.JMenuItem();
        mnuRegistros = new javax.swing.JMenu();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        mnuInquilinos = new javax.swing.JMenuItem();
        jSeparator22 = new javax.swing.JPopupMenu.Separator();
        mnuItemPropietarios = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        mnuItemPropiedades = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        mnuInquilinos1 = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        mnuInquilinos3 = new javax.swing.JMenuItem();
        jSeparator17 = new javax.swing.JPopupMenu.Separator();
        mnuInquilinos4 = new javax.swing.JMenuItem();
        jSeparator18 = new javax.swing.JPopupMenu.Separator();
        mnuInquilinos5 = new javax.swing.JMenuItem();
        mnuProveedor = new javax.swing.JMenu();
        jSeparator21 = new javax.swing.JPopupMenu.Separator();
        mnuInquilinos2 = new javax.swing.JMenuItem();
        jSeparator19 = new javax.swing.JPopupMenu.Separator();
        mnuItemPropiedades7 = new javax.swing.JMenuItem();
        jSeparator20 = new javax.swing.JPopupMenu.Separator();
        mnuItemPropiedades8 = new javax.swing.JMenuItem();
        mnuAyuda2 = new javax.swing.JMenu();
        jSeparator16 = new javax.swing.JPopupMenu.Separator();
        mnuItemPropiedades6 = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        mnuItemPropiedades9 = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        mnuAyuda = new javax.swing.JMenu();
        mnuItemConsultaCuentasProveedores1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consultorio Odontológico - D.N.Soft .-");
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/diente.jpg")).getImage());

        jMenuBar1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N

        mnuSistema.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuSistema.setText(" SISTEMA ");
        mnuSistema.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        mnuCotizacion10.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuCotizacion10.setText(" Parametros Globales ");
        mnuCotizacion10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuCotizacion10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCotizacion10ActionPerformed(evt);
            }
        });
        mnuSistema.add(mnuCotizacion10);

        mnuItemSesion1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuItemSesion1.setText(" Respaldar Base");
        mnuItemSesion1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuItemSesion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemSesion1ActionPerformed(evt);
            }
        });
        mnuSistema.add(mnuItemSesion1);

        mnuItemSalir.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuItemSalir.setText(" Salir ");
        mnuItemSalir.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemSalirActionPerformed(evt);
            }
        });
        mnuSistema.add(mnuItemSalir);

        jMenuBar1.add(mnuSistema);

        mnuRegistros.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuRegistros.setText(" REGISTROS ");
        mnuRegistros.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mnuRegistros.add(jSeparator13);

        mnuInquilinos.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuInquilinos.setText(" Pacientes ");
        mnuInquilinos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuInquilinos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuInquilinosActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuInquilinos);
        mnuRegistros.add(jSeparator22);

        mnuItemPropietarios.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuItemPropietarios.setText(" Clasificación tratamientos ");
        mnuItemPropietarios.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuItemPropietarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemPropietariosActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuItemPropietarios);
        mnuRegistros.add(jSeparator12);

        mnuItemPropiedades.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuItemPropiedades.setText(" Tratamientos ");
        mnuItemPropiedades.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuItemPropiedades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemPropiedadesActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuItemPropiedades);
        mnuRegistros.add(jSeparator10);

        mnuInquilinos1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuInquilinos1.setText(" Cuentas de pacientes");
        mnuInquilinos1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuInquilinos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuInquilinos1ActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuInquilinos1);
        mnuRegistros.add(jSeparator11);

        mnuInquilinos3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuInquilinos3.setText(" Agenda");
        mnuInquilinos3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuInquilinos3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuInquilinos3ActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuInquilinos3);
        mnuRegistros.add(jSeparator17);

        mnuInquilinos4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuInquilinos4.setText(" Crear planes de tratamiento");
        mnuInquilinos4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuInquilinos4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuInquilinos4ActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuInquilinos4);
        mnuRegistros.add(jSeparator18);

        mnuInquilinos5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuInquilinos5.setText(" Consultar tratamientos");
        mnuInquilinos5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuInquilinos5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuInquilinos5ActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuInquilinos5);

        jMenuBar1.add(mnuRegistros);

        mnuProveedor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuProveedor.setText(" PROVEEDORES");
        mnuProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mnuProveedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mnuProveedor.add(jSeparator21);

        mnuInquilinos2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuInquilinos2.setText(" Registrar Proveedor");
        mnuInquilinos2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuInquilinos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuInquilinos2ActionPerformed(evt);
            }
        });
        mnuProveedor.add(mnuInquilinos2);
        mnuProveedor.add(jSeparator19);

        mnuItemPropiedades7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuItemPropiedades7.setText(" Consultar trabajos ");
        mnuItemPropiedades7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuItemPropiedades7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemPropiedades7ActionPerformed(evt);
            }
        });
        mnuProveedor.add(mnuItemPropiedades7);
        mnuProveedor.add(jSeparator20);

        mnuItemPropiedades8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuItemPropiedades8.setText(" Trabajos pendientes");
        mnuItemPropiedades8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuItemPropiedades8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemPropiedades8ActionPerformed(evt);
            }
        });
        mnuProveedor.add(mnuItemPropiedades8);

        jMenuBar1.add(mnuProveedor);

        mnuAyuda2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuAyuda2.setText(" CAJA ");
        mnuAyuda2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mnuAyuda2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mnuAyuda2.add(jSeparator16);

        mnuItemPropiedades6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuItemPropiedades6.setText(" Consultas ");
        mnuItemPropiedades6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuItemPropiedades6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemPropiedades6ActionPerformed(evt);
            }
        });
        mnuAyuda2.add(mnuItemPropiedades6);
        mnuAyuda2.add(jSeparator14);

        mnuItemPropiedades9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuItemPropiedades9.setText(" Movimientos  ");
        mnuItemPropiedades9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuItemPropiedades9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemPropiedades9ActionPerformed(evt);
            }
        });
        mnuAyuda2.add(mnuItemPropiedades9);
        mnuAyuda2.add(jSeparator15);

        jMenuBar1.add(mnuAyuda2);

        mnuAyuda.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuAyuda.setText("  Ayuda  ");
        mnuAyuda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mnuAyuda.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        mnuItemConsultaCuentasProveedores1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuItemConsultaCuentasProveedores1.setText("  Sobre  ");
        mnuItemConsultaCuentasProveedores1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuItemConsultaCuentasProveedores1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemConsultaCuentasProveedores1ActionPerformed(evt);
            }
        });
        mnuAyuda.add(mnuItemConsultaCuentasProveedores1);

        jMenuBar1.add(mnuAyuda);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnuItemSalirActionPerformed

    private void mnuInquilinosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuInquilinosActionPerformed

        PacientesView view = new PacientesView();
        PacientesController controller = new PacientesController(view, desktopPane);
        desktopPane.add(view);

        controller.go();

    }//GEN-LAST:event_mnuInquilinosActionPerformed

    private void mnuItemConsultaCuentasProveedores1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemConsultaCuentasProveedores1ActionPerformed

        JOptionPane.showMessageDialog(null, "<html><font size=6 face=Verdana color=black><center>Sistema Consultorio Odontológico<br><br>"
                + "Desarrollado por Diego Noble</center><br><br>"
                + "<font size=5 face=Verdana color=black>Contactos: Diego Noble<br><br>"
                + "cel: +598 91390000<br><br>"
                + "E-mail: dnoblemello@gmail.com<br><br></html>");

    }//GEN-LAST:event_mnuItemConsultaCuentasProveedores1ActionPerformed

    private void mnuItemPropietariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemPropietariosActionPerformed

        ClaseTratamientoView view = new ClaseTratamientoView();

        ClasePacientesController controller = new ClasePacientesController(view, desktopPane);
        desktopPane.add(view);
        controller.go();
    }//GEN-LAST:event_mnuItemPropietariosActionPerformed

    private void mnuItemPropiedadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemPropiedadesActionPerformed

        TrabajosView view = new TrabajosView();

        TrabajosController controller = new TrabajosController(view, desktopPane);
        desktopPane.add(view);
        controller.go();
    }//GEN-LAST:event_mnuItemPropiedadesActionPerformed

    private void mnuItemPropiedades6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemPropiedades6ActionPerformed

        ConsultaDeCajaView view = new ConsultaDeCajaView();

        ConsultaDeCajaController controller = new ConsultaDeCajaController(view, desktopPane);
        desktopPane.add(view);
        controller.go();
    }//GEN-LAST:event_mnuItemPropiedades6ActionPerformed

    private void mnuCotizacion10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCotizacion10ActionPerformed

    }//GEN-LAST:event_mnuCotizacion10ActionPerformed

    private void mnuItemSesion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemSesion1ActionPerformed

        BackUpsView logBackup = new BackUpsView();
        logBackup.setVisible(true);
        logBackup.toFront();

    }//GEN-LAST:event_mnuItemSesion1ActionPerformed

    private void mnuInquilinos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuInquilinos2ActionPerformed

        ProveedorView view = new ProveedorView();

        ProveedorController controller = new ProveedorController(view, desktopPane);
        desktopPane.add(view);
        controller.go();

    }//GEN-LAST:event_mnuInquilinos2ActionPerformed

    private void mnuInquilinos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuInquilinos3ActionPerformed

        AgendaView agenda = new AgendaView();
        desktopPane.add(agenda);
        agenda.setVisible(true);
        agenda.toFront();
    }//GEN-LAST:event_mnuInquilinos3ActionPerformed

    private void mnuInquilinos4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuInquilinos4ActionPerformed

        PlanTratamientoView planTratamiento = new PlanTratamientoView();
        planTratamiento.setVisible(true);
        planTratamiento.toFront();
    }//GEN-LAST:event_mnuInquilinos4ActionPerformed

    private void mnuInquilinos5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuInquilinos5ActionPerformed

        ConsultaPlanesTratamientoView consultaPlanesTratamientoView = new ConsultaPlanesTratamientoView();
        consultaPlanesTratamientoView.setVisible(true);
        consultaPlanesTratamientoView.toFront();

    }//GEN-LAST:event_mnuInquilinos5ActionPerformed

    private void mnuItemPropiedades7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemPropiedades7ActionPerformed

        ConsultaTrabajosProveedor consultaTrabajosProveedor = new ConsultaTrabajosProveedor(null);
        desktopPane.add(consultaTrabajosProveedor);
        consultaTrabajosProveedor.setVisible(true);
        consultaTrabajosProveedor.toFront();

    }//GEN-LAST:event_mnuItemPropiedades7ActionPerformed

    private void mnuItemPropiedades8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemPropiedades8ActionPerformed
        RecoratorioTrabajosProveedor view = new RecoratorioTrabajosProveedor();
        desktopPane.add(view);


    }//GEN-LAST:event_mnuItemPropiedades8ActionPerformed

    private void mnuInquilinos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuInquilinos1ActionPerformed
        DetalleMovimientosCuentaPaciente cCPacienteView = new DetalleMovimientosCuentaPaciente(null, true);
        DetalleCuentaPacientesController controller = new DetalleCuentaPacientesController(cCPacienteView);
        controller.go();
    }//GEN-LAST:event_mnuInquilinos1ActionPerformed

    private void mnuItemPropiedades9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemPropiedades9ActionPerformed
        ControlDeCajaView view = new ControlDeCajaView();

        ControlDeCajaController controller = new ControlDeCajaController(view, desktopPane);
        desktopPane.add(view);
        controller.go();

    }//GEN-LAST:event_mnuItemPropiedades9ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator16;
    private javax.swing.JPopupMenu.Separator jSeparator17;
    private javax.swing.JPopupMenu.Separator jSeparator18;
    private javax.swing.JPopupMenu.Separator jSeparator19;
    private javax.swing.JPopupMenu.Separator jSeparator20;
    private javax.swing.JPopupMenu.Separator jSeparator21;
    private javax.swing.JPopupMenu.Separator jSeparator22;
    private javax.swing.JMenu mnuAyuda;
    private javax.swing.JMenu mnuAyuda2;
    private javax.swing.JMenuItem mnuCotizacion10;
    private javax.swing.JMenuItem mnuInquilinos;
    private javax.swing.JMenuItem mnuInquilinos1;
    private javax.swing.JMenuItem mnuInquilinos2;
    private javax.swing.JMenuItem mnuInquilinos3;
    private javax.swing.JMenuItem mnuInquilinos4;
    private javax.swing.JMenuItem mnuInquilinos5;
    private javax.swing.JMenuItem mnuItemConsultaCuentasProveedores1;
    private javax.swing.JMenuItem mnuItemPropiedades;
    private javax.swing.JMenuItem mnuItemPropiedades6;
    private javax.swing.JMenuItem mnuItemPropiedades7;
    private javax.swing.JMenuItem mnuItemPropiedades8;
    private javax.swing.JMenuItem mnuItemPropiedades9;
    private javax.swing.JMenuItem mnuItemPropietarios;
    private javax.swing.JMenuItem mnuItemSalir;
    private javax.swing.JMenuItem mnuItemSesion1;
    private javax.swing.JMenu mnuProveedor;
    private javax.swing.JMenu mnuRegistros;
    private javax.swing.JMenu mnuSistema;
    // End of variables declaration//GEN-END:variables

}
