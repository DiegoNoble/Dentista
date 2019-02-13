package com.dnsoft.dentista.views;

import com.dnsoft.dentista.beans.Consulta;
import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.beans.Parametros;
import com.dnsoft.dentista.beans.SituacionConsultaEnum;
import com.dnsoft.dentista.utiles.Container;
import com.dnsoft.dentista.utiles.OptionPaneEstandar;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.dnsoft.dentista.daos.IConsultaDAO;
import com.dnsoft.dentista.daos.IPacienteDAO;
import com.dnsoft.dentista.daos.IParametrosDAO;
import com.dnsoft.dentista.utiles.AgendaGoogleCalendar;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.demo.FullDemo;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Diego Noble
 */
public class AgendaDetalles extends javax.swing.JDialog {

    Container container;
    IConsultaDAO daoConsula;
    IPacienteDAO daoPaciente;
    IParametrosDAO parametrosDAO;
    Parametros parametros;
    LocalDate fecha;
    TimePickerSettings timeSettings1 = new TimePickerSettings();
    TimePickerSettings timeSettings2 = new TimePickerSettings();
    Consulta consulta;
    Boolean esNuevo = true;
    String dirWeb = "www.google.com";
    int puerto = 80;

    public AgendaDetalles(java.awt.Frame parent, boolean modal, LocalDate fecha) {
        super(parent, modal);
        configuraTimePicker();
        initComponents();
        iconosTimePicker();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/diente.jpg")));
        //CIERRA JOPTIONPANE CON ESCAPE
        jPanel1.grabFocus();
        jPanel1.addKeyListener(new OptionPaneEstandar(this));

        this.container = Container.getInstancia();
        this.fecha = fecha;
        this.daoConsula = container.getBean(IConsultaDAO.class);
        this.daoPaciente = container.getBean(IPacienteDAO.class);
        consulta = new Consulta();
        esNuevo = true;
        setLocationRelativeTo(null);
        inicio();

    }

    public AgendaDetalles(java.awt.Frame parent, boolean modal, LocalDate fecha, Consulta consultaSeleccionada) {
        super(parent, modal);
        configuraTimePicker();
        initComponents();
        iconosTimePicker();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/diente.jpg")));
        //CIERRA JOPTIONPANE CON ESCAPE
        jPanel1.grabFocus();
        jPanel1.addKeyListener(new OptionPaneEstandar(this));

        this.container = Container.getInstancia();
        this.fecha = fecha;
        this.consulta = consultaSeleccionada;
        this.daoConsula = container.getBean(IConsultaDAO.class);
        esNuevo = false;
        //this.daoPaciente = container.getBean(IPacienteDAO.class);
        setLocationRelativeTo(null);
        inicio();

    }

    void inicio() {
        this.parametrosDAO = container.getBean(IParametrosDAO.class);
        parametros = parametrosDAO.findOne(new Long(1));
        accionesBotones();

        configuraComboPacientes();
    }

    void configuraTimePicker() {
        timeSettings1.setAllowEmptyTimes(false);
        timeSettings1.initialTime = LocalTime.of(15, 00);
        timeSettings1.use24HourClockFormat();
        timeSettings2.setAllowEmptyTimes(false);
        timeSettings2.initialTime = LocalTime.of(16, 00);
        timeSettings2.use24HourClockFormat();

    }

    void iconosTimePicker() {
        URL timeIconURL = FullDemo.class.getResource("/imagenes/timepickerbutton.png");
        Image timeExampleImage = Toolkit.getDefaultToolkit().getImage(timeIconURL);
        ImageIcon timeExampleIcon = new ImageIcon(timeExampleImage);
        JButton timePickerButton1 = tpDesde.getComponentToggleTimeMenuButton();
        JButton timePickerButton2 = tpHasta.getComponentToggleTimeMenuButton();
        timePickerButton1.setText("");
        timePickerButton1.setIcon(timeExampleIcon);
        timePickerButton2.setText("");
        timePickerButton2.setIcon(timeExampleIcon);
        // Adjust the button size to fit the new icon.
        Dimension newTimeButtonSize = new Dimension(timeExampleIcon.getIconWidth() + 4, timeExampleIcon.getIconHeight() + 4);
        timePickerButton1.setPreferredSize(newTimeButtonSize);
        timePickerButton2.setPreferredSize(newTimeButtonSize);
    }

    void agendaConsulta() {

        consulta.setComentario(txtObs.getText());
        consulta.setFechaConsulta(fecha);
        consulta.setHoraConsultaDesde(tpDesde.getTime());
        consulta.setHoraConsultaHasta(tpHasta.getTime());
        consulta.setHora_desde(tpDesde.getText());
        consulta.setHora_hasta(tpHasta.getText());
        consulta.setPaciente((Paciente) cbPacientes.getSelectedItem());
        consulta.setSituacionConsulta(SituacionConsultaEnum.PENDIENTE);
        daoConsula.save(consulta);
        try {
            if (parametros.getActivaGoogleCalendar() == 1) {
                Socket s = new Socket(dirWeb, puerto);
                if (s.isConnected()) {
                    System.out.println("Hay conexión a internet!");
                    if (esNuevo == true) {

                        new AgendaGoogleCalendar().creaEvento(consulta);
                    } else {
                        new AgendaGoogleCalendar().modificarEvento(consulta);
                    }
                }
                JOptionPane.showMessageDialog(null, "Agendado correctamente!", "Transacción correcta", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Agendado correctamente!", "Transacción correcta", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
        } catch (UnknownHostException e) {
            System.err.println("Sin conexión a internet!");
           // e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Agendado correctamente!", "Transacción correcta", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(AgendaDetalles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AgendaDetalles.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void configuraComboPacientes() {
        if (esNuevo == true) {

            AutoCompleteDecorator.decorate(cbPacientes);
            List<Paciente> pacientes = daoPaciente.findAll();
            for (Paciente paciente : pacientes) {
                cbPacientes.addItem(paciente);
            }
        } else {
            cbPacientes.addItem(consulta.getPaciente());
        }
    }

    void accionesBotones() {
        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                agendaConsulta();
            }
        });
        btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {

                dispose();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnGuardar = new botones.BotonGuardar();
        btnVolver = new botones.BotonVolver();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbPacientes = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        tpDesde = tpDesde = new TimePicker(timeSettings1);
        tpHasta = tpHasta = new TimePicker(timeSettings2);
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel3.setFont(new java.awt.Font("Arial", 3, 48)); // NOI18N
        jLabel3.setText("Detalles agenda");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(jLabel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(btnGuardar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(btnVolver, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        jPanel1.add(jPanel3, gridBagConstraints);

        jLabel4.setText("hasta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel2.setText("Paciente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(cbPacientes, gridBagConstraints);

        jLabel1.setText("Comentarios:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel1, gridBagConstraints);

        txtObs.setColumns(20);
        txtObs.setRows(5);
        jScrollPane2.setViewportView(txtObs);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jScrollPane2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(tpDesde, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(tpHasta, gridBagConstraints);

        jLabel5.setText("Desde");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private botones.BotonGuardar btnGuardar;
    public botones.BotonVolver btnVolver;
    private javax.swing.JComboBox cbPacientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private com.github.lgooddatepicker.components.TimePicker tpDesde;
    private com.github.lgooddatepicker.components.TimePicker tpHasta;
    private javax.swing.JTextArea txtObs;
    // End of variables declaration//GEN-END:variables
}
