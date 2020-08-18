package com.dnsoft.dentista.views;

import com.dnsoft.dentista.beans.Perfil;
import com.dnsoft.dentista.beans.Usuario;
import com.dnsoft.dentista.controllers.ClasePacientesController;
import com.dnsoft.dentista.controllers.ConsultaDeCajaController;
import com.dnsoft.dentista.controllers.ControlDeCajaController;
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

    Usuario usuaro;
    Perfil perfilUsuarioLogin;
    DecorateDesktopPane desktopPane = new DecorateDesktopPane("/imagenes/fondo.jpg");
    //JDesktopPane desktopPane = new JDesktopPane();
    ImageIcon fot;
    ImageIcon icono;
    LeeProperties props = new LeeProperties();
    Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/diente.jpg"));

    public MenuPrincipalView(Usuario usuaro, Perfil perfil) {
        initComponents();
        this.usuaro = usuaro;
        this.perfilUsuarioLogin = perfil;

        setSize(1060, 768);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        add(desktopPane);
        //imagenDeFondo();
        iniciar();
        verificarPermisos();

    }

    public void iniciar() {

        PacientesView view = new PacientesView();
        PacientesController controller = new PacientesController(view, desktopPane);
        desktopPane.add(view);
        controller.go();

        /*AgendaView agenda = new AgendaView();
        desktopPane.add(agenda);
        agenda.setLocation(0, 130);
        agenda.toFront();
        agenda.setVisible(true);
         */
        RecoratorioTrabajosProveedor viewp = new RecoratorioTrabajosProveedor();
        desktopPane.add(viewp);

    }

    void verificarPermisos() {
        if (perfilUsuarioLogin != null) {

            mnuAgenda.setEnabled(perfilUsuarioLogin.getAgenda());
            mnuConsultarCaja.setEnabled(perfilUsuarioLogin.getConsultas());
            mnuConsultarDeudores.setEnabled(perfilUsuarioLogin.getConsultarDeudores());
            mnuConsultarTrabajos.setEnabled(perfilUsuarioLogin.getConsultarTrabajos());
            mnuConsultarTratamientos.setEnabled(perfilUsuarioLogin.getConsultarTratamientos());
            mnuCrearPlanes.setEnabled(perfilUsuarioLogin.getCrearPlanTratamiento());
            mnuCuentasPacientes.setEnabled(perfilUsuarioLogin.getCuentadePacientes());
            mnuItemClasificacionTratamientos.setEnabled(perfilUsuarioLogin.getClasificaTratamientos());
            mnuItemTratamientos.setEnabled(perfilUsuarioLogin.getTratamientos());
            mnuMediosDePago.setEnabled(perfilUsuarioLogin.getRegistraMediosDePago());
            mnuMovimientos.setEnabled(perfilUsuarioLogin.getMovimientos());
            mnuPacientes.setEnabled(perfilUsuarioLogin.getPacientes());
            mnuPermisosUsuarios.setEnabled(perfilUsuarioLogin.getPermisos());
            mnuRegistrarProveedor.setEnabled(perfilUsuarioLogin.getRegistrarProveedor());
            mnuRespaldos.setEnabled(perfilUsuarioLogin.getRespaldos());
            mnuTrabajosPendientes.setEnabled(perfilUsuarioLogin.getTrabajosPendientes());
            mnuUsuarios.setEnabled(perfilUsuarioLogin.getUsuarios());

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        mnuSistema = new javax.swing.JMenu();
        mnuUsuarios = new javax.swing.JMenuItem();
        mnuPermisosUsuarios = new javax.swing.JMenuItem();
        mnuRespaldos = new javax.swing.JMenuItem();
        mnuSalir = new javax.swing.JMenuItem();
        mnuRegistros = new javax.swing.JMenu();
        mnuMediosDePago1 = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        mnuMediosDePago = new javax.swing.JMenuItem();
        jSeparator24 = new javax.swing.JPopupMenu.Separator();
        mnuPacientes = new javax.swing.JMenuItem();
        jSeparator22 = new javax.swing.JPopupMenu.Separator();
        mnuItemClasificacionTratamientos = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        mnuItemTratamientos = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        mnuCuentasPacientes = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        mnuAgenda = new javax.swing.JMenuItem();
        jSeparator17 = new javax.swing.JPopupMenu.Separator();
        mnuCrearPlanes = new javax.swing.JMenuItem();
        jSeparator18 = new javax.swing.JPopupMenu.Separator();
        mnuConsultarTratamientos = new javax.swing.JMenuItem();
        jSeparator23 = new javax.swing.JPopupMenu.Separator();
        mnuConsultarDeudores = new javax.swing.JMenuItem();
        mnuProveedor = new javax.swing.JMenu();
        jSeparator21 = new javax.swing.JPopupMenu.Separator();
        mnuRegistrarProveedor = new javax.swing.JMenuItem();
        jSeparator19 = new javax.swing.JPopupMenu.Separator();
        mnuConsultarTrabajos = new javax.swing.JMenuItem();
        jSeparator20 = new javax.swing.JPopupMenu.Separator();
        mnuTrabajosPendientes = new javax.swing.JMenuItem();
        mnuCaja = new javax.swing.JMenu();
        jSeparator16 = new javax.swing.JPopupMenu.Separator();
        mnuConsultarCaja = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        mnuMovimientos = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        mnuAyuda = new javax.swing.JMenu();
        mnuItemAyuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consultorio Odontológico - D.N.Soft .-");
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/diente.jpg")).getImage());

        jMenuBar1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N

        mnuSistema.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuSistema.setText(" SISTEMA ");
        mnuSistema.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        mnuUsuarios.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuUsuarios.setText(" Usuarios");
        mnuUsuarios.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuUsuariosActionPerformed(evt);
            }
        });
        mnuSistema.add(mnuUsuarios);

        mnuPermisosUsuarios.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuPermisosUsuarios.setText(" Permisos usuarios");
        mnuPermisosUsuarios.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuPermisosUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPermisosUsuariosActionPerformed(evt);
            }
        });
        mnuSistema.add(mnuPermisosUsuarios);

        mnuRespaldos.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuRespaldos.setText(" Respaldar Base");
        mnuRespaldos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuRespaldos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRespaldosActionPerformed(evt);
            }
        });
        mnuSistema.add(mnuRespaldos);

        mnuSalir.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuSalir.setText(" Salir ");
        mnuSalir.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSalirActionPerformed(evt);
            }
        });
        mnuSistema.add(mnuSalir);

        jMenuBar1.add(mnuSistema);

        mnuRegistros.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuRegistros.setText(" REGISTROS ");
        mnuRegistros.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        mnuMediosDePago1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuMediosDePago1.setText(" Rubros ");
        mnuMediosDePago1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuMediosDePago1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMediosDePago1ActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuMediosDePago1);
        mnuRegistros.add(jSeparator13);

        mnuMediosDePago.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuMediosDePago.setText(" Medios de Pago");
        mnuMediosDePago.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuMediosDePago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMediosDePagoActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuMediosDePago);
        mnuRegistros.add(jSeparator24);

        mnuPacientes.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuPacientes.setText(" Pacientes ");
        mnuPacientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPacientesActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuPacientes);
        mnuRegistros.add(jSeparator22);

        mnuItemClasificacionTratamientos.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuItemClasificacionTratamientos.setText(" Clasificación tratamientos ");
        mnuItemClasificacionTratamientos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuItemClasificacionTratamientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemClasificacionTratamientosActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuItemClasificacionTratamientos);
        mnuRegistros.add(jSeparator12);

        mnuItemTratamientos.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuItemTratamientos.setText(" Tratamientos ");
        mnuItemTratamientos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuItemTratamientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemTratamientosActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuItemTratamientos);
        mnuRegistros.add(jSeparator10);

        mnuCuentasPacientes.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuCuentasPacientes.setText(" Cuentas de pacientes");
        mnuCuentasPacientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuCuentasPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCuentasPacientesActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuCuentasPacientes);
        mnuRegistros.add(jSeparator11);

        mnuAgenda.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuAgenda.setText(" Agenda");
        mnuAgenda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAgendaActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuAgenda);
        mnuRegistros.add(jSeparator17);

        mnuCrearPlanes.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuCrearPlanes.setText(" Crear planes de tratamiento");
        mnuCrearPlanes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuCrearPlanes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCrearPlanesActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuCrearPlanes);
        mnuRegistros.add(jSeparator18);

        mnuConsultarTratamientos.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuConsultarTratamientos.setText(" Consultar tratamientos");
        mnuConsultarTratamientos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuConsultarTratamientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConsultarTratamientosActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuConsultarTratamientos);
        mnuRegistros.add(jSeparator23);

        mnuConsultarDeudores.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuConsultarDeudores.setText(" Consultar Deudores");
        mnuConsultarDeudores.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuConsultarDeudores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConsultarDeudoresActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuConsultarDeudores);

        jMenuBar1.add(mnuRegistros);

        mnuProveedor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuProveedor.setText(" PROVEEDORES");
        mnuProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mnuProveedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mnuProveedor.add(jSeparator21);

        mnuRegistrarProveedor.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuRegistrarProveedor.setText(" Registrar Proveedor");
        mnuRegistrarProveedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuRegistrarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRegistrarProveedorActionPerformed(evt);
            }
        });
        mnuProveedor.add(mnuRegistrarProveedor);
        mnuProveedor.add(jSeparator19);

        mnuConsultarTrabajos.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuConsultarTrabajos.setText(" Consultar trabajos ");
        mnuConsultarTrabajos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuConsultarTrabajos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConsultarTrabajosActionPerformed(evt);
            }
        });
        mnuProveedor.add(mnuConsultarTrabajos);
        mnuProveedor.add(jSeparator20);

        mnuTrabajosPendientes.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuTrabajosPendientes.setText(" Trabajos pendientes");
        mnuTrabajosPendientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuTrabajosPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTrabajosPendientesActionPerformed(evt);
            }
        });
        mnuProveedor.add(mnuTrabajosPendientes);

        jMenuBar1.add(mnuProveedor);

        mnuCaja.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuCaja.setText(" CAJA ");
        mnuCaja.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mnuCaja.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mnuCaja.add(jSeparator16);

        mnuConsultarCaja.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuConsultarCaja.setText(" Consultas ");
        mnuConsultarCaja.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuConsultarCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConsultarCajaActionPerformed(evt);
            }
        });
        mnuCaja.add(mnuConsultarCaja);
        mnuCaja.add(jSeparator14);

        mnuMovimientos.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuMovimientos.setText(" Movimientos  ");
        mnuMovimientos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnuMovimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMovimientosActionPerformed(evt);
            }
        });
        mnuCaja.add(mnuMovimientos);
        mnuCaja.add(jSeparator15);

        jMenuBar1.add(mnuCaja);

        mnuAyuda.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuAyuda.setText("  Ayuda  ");
        mnuAyuda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mnuAyuda.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        mnuItemAyuda.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mnuItemAyuda.setText("  Sobre  ");
        mnuItemAyuda.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuItemAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemAyudaActionPerformed(evt);
            }
        });
        mnuAyuda.add(mnuItemAyuda);

        jMenuBar1.add(mnuAyuda);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnuSalirActionPerformed

    private void mnuPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPacientesActionPerformed

        PacientesView view = new PacientesView();
        PacientesController controller = new PacientesController(view, desktopPane);
        desktopPane.add(view);

        controller.go();

    }//GEN-LAST:event_mnuPacientesActionPerformed

    private void mnuItemAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemAyudaActionPerformed

        JOptionPane.showMessageDialog(null, "<html><font size=6 face=Verdana color=black><center>Sistema Consultorio Odontológico<br><br>"
                + "Desarrollado por Diego Noble</center><br><br>"
                + "<font size=5 face=Verdana color=black>Contactos: Diego Noble<br><br>"
                + "cel: +598 91390000<br><br>"
                + "E-mail: dnoblemello@gmail.com<br><br></html>");

    }//GEN-LAST:event_mnuItemAyudaActionPerformed

    private void mnuItemClasificacionTratamientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemClasificacionTratamientosActionPerformed

        ClaseTratamientoView view = new ClaseTratamientoView();

        ClasePacientesController controller = new ClasePacientesController(view, desktopPane);
        desktopPane.add(view);
        controller.go();
    }//GEN-LAST:event_mnuItemClasificacionTratamientosActionPerformed

    private void mnuItemTratamientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemTratamientosActionPerformed

        TrabajosView view = new TrabajosView();

        TrabajosController controller = new TrabajosController(view, desktopPane);
        desktopPane.add(view);
        controller.go();
    }//GEN-LAST:event_mnuItemTratamientosActionPerformed

    private void mnuConsultarCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuConsultarCajaActionPerformed

        ConsultaDeCajaView view = new ConsultaDeCajaView();

        ConsultaDeCajaController controller = new ConsultaDeCajaController(view, desktopPane);
        desktopPane.add(view);
        controller.go();
    }//GEN-LAST:event_mnuConsultarCajaActionPerformed

    private void mnuRespaldosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRespaldosActionPerformed

        BackUpsView logBackup = new BackUpsView();
        logBackup.setVisible(true);
        logBackup.toFront();

    }//GEN-LAST:event_mnuRespaldosActionPerformed

    private void mnuRegistrarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRegistrarProveedorActionPerformed

        ProveedorView view = new ProveedorView();

        ProveedorController controller = new ProveedorController(view, desktopPane);
        desktopPane.add(view);
        controller.go();

    }//GEN-LAST:event_mnuRegistrarProveedorActionPerformed

    private void mnuAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAgendaActionPerformed

        AgendaView agenda = new AgendaView();
        desktopPane.add(agenda);
        agenda.setVisible(true);
        agenda.toFront();
    }//GEN-LAST:event_mnuAgendaActionPerformed

    private void mnuCrearPlanesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCrearPlanesActionPerformed

        PlanTratamientoView planTratamiento = new PlanTratamientoView();
        planTratamiento.setVisible(true);
        planTratamiento.toFront();
    }//GEN-LAST:event_mnuCrearPlanesActionPerformed

    private void mnuConsultarTratamientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuConsultarTratamientosActionPerformed

        ConsultaPlanesTratamientoView consultaPlanesTratamientoView = ConsultaPlanesTratamientoView.getInstancia();
        consultaPlanesTratamientoView.setVisible(true);
        consultaPlanesTratamientoView.toFront();

    }//GEN-LAST:event_mnuConsultarTratamientosActionPerformed

    private void mnuConsultarTrabajosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuConsultarTrabajosActionPerformed

        ConsultaTrabajosProveedor consultaTrabajosProveedor = new ConsultaTrabajosProveedor(null);
        desktopPane.add(consultaTrabajosProveedor);
        consultaTrabajosProveedor.setVisible(true);
        consultaTrabajosProveedor.toFront();

    }//GEN-LAST:event_mnuConsultarTrabajosActionPerformed

    private void mnuTrabajosPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTrabajosPendientesActionPerformed
        RecoratorioTrabajosProveedor view = new RecoratorioTrabajosProveedor();
        desktopPane.add(view);
        view.setVisible(true);
        view.toFront();


    }//GEN-LAST:event_mnuTrabajosPendientesActionPerformed

    private void mnuCuentasPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCuentasPacientesActionPerformed

        DetalleMovimientosCuentaPaciente2 instancia = DetalleMovimientosCuentaPaciente2.getInstancia();
        instancia.setVisible(true);
        instancia.toFront();

    }//GEN-LAST:event_mnuCuentasPacientesActionPerformed

    private void mnuMovimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMovimientosActionPerformed
        ControlDeCajaView view = new ControlDeCajaView();

        ControlDeCajaController controller = new ControlDeCajaController(view, desktopPane);
        desktopPane.add(view);
        controller.go();

    }//GEN-LAST:event_mnuMovimientosActionPerformed

    private void mnuConsultarDeudoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuConsultarDeudoresActionPerformed

        DeudoresView deudoresView = new DeudoresView();
        desktopPane.add(deudoresView);
        deudoresView.setVisible(true);
        deudoresView.toFront();


    }//GEN-LAST:event_mnuConsultarDeudoresActionPerformed

    private void mnuUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuUsuariosActionPerformed
        registroUsuarios u = new registroUsuarios();
        desktopPane.add(u);
        u.setVisible(true);
        u.toFront();
    }//GEN-LAST:event_mnuUsuariosActionPerformed

    private void mnuPermisosUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPermisosUsuariosActionPerformed

        ConfPerfiles u = new ConfPerfiles();
        desktopPane.add(u);
        u.setVisible(true);
        u.toFront();
    }//GEN-LAST:event_mnuPermisosUsuariosActionPerformed

    private void mnuMediosDePagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMediosDePagoActionPerformed

        registroMediosDePago p = new registroMediosDePago();
        desktopPane.add(p);
        p.setVisible(true);
        p.toFront();

    }//GEN-LAST:event_mnuMediosDePagoActionPerformed

    private void mnuMediosDePago1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMediosDePago1ActionPerformed
        RubrosView p = new RubrosView();
        desktopPane.add(p);
        p.setVisible(true);
        p.toFront();

    }//GEN-LAST:event_mnuMediosDePago1ActionPerformed

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
    private javax.swing.JPopupMenu.Separator jSeparator23;
    private javax.swing.JPopupMenu.Separator jSeparator24;
    private javax.swing.JMenuItem mnuAgenda;
    private javax.swing.JMenu mnuAyuda;
    private javax.swing.JMenu mnuCaja;
    private javax.swing.JMenuItem mnuConsultarCaja;
    private javax.swing.JMenuItem mnuConsultarDeudores;
    private javax.swing.JMenuItem mnuConsultarTrabajos;
    private javax.swing.JMenuItem mnuConsultarTratamientos;
    private javax.swing.JMenuItem mnuCrearPlanes;
    private javax.swing.JMenuItem mnuCuentasPacientes;
    private javax.swing.JMenuItem mnuItemAyuda;
    private javax.swing.JMenuItem mnuItemClasificacionTratamientos;
    private javax.swing.JMenuItem mnuItemTratamientos;
    private javax.swing.JMenuItem mnuMediosDePago;
    private javax.swing.JMenuItem mnuMediosDePago1;
    private javax.swing.JMenuItem mnuMovimientos;
    private javax.swing.JMenuItem mnuPacientes;
    private javax.swing.JMenuItem mnuPermisosUsuarios;
    private javax.swing.JMenu mnuProveedor;
    private javax.swing.JMenuItem mnuRegistrarProveedor;
    private javax.swing.JMenu mnuRegistros;
    private javax.swing.JMenuItem mnuRespaldos;
    private javax.swing.JMenuItem mnuSalir;
    private javax.swing.JMenu mnuSistema;
    private javax.swing.JMenuItem mnuTrabajosPendientes;
    private javax.swing.JMenuItem mnuUsuarios;
    // End of variables declaration//GEN-END:variables

}
