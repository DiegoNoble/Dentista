package com.dnsoft.dentista.views;

import com.dnsoft.dentista.beans.HistoriaClinica;
import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.utiles.Container;
import com.dnsoft.dentista.utiles.OptionPaneEstandar;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.dnsoft.dentista.daos.IHistoriaClinicalDAO;
import com.dnsoft.dentista.utiles.LeeProperties;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Diego Noble
 */
public class HistoriaClinicaDetalles extends javax.swing.JDialog {

    Container container;
    IHistoriaClinicalDAO historiaClinicalDAO;
    Paciente paciente;
    HistoriaClinica h;
    LeeProperties props = new LeeProperties();

    public HistoriaClinicaDetalles(java.awt.Frame parent, boolean modal, Paciente PacienteSeleccionado) {
        super(parent, modal);
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/diente.jpg")));
        //CIERRA JOPTIONPANE CON ESCAPE
        jPanel1.grabFocus();
        jPanel1.addKeyListener(new OptionPaneEstandar(this));

        this.container = Container.getInstancia();
        historiaClinicalDAO = container.getBean(IHistoriaClinicalDAO.class);
        this.paciente = PacienteSeleccionado;
        setLocationRelativeTo(null);
        inicio();
        detallesHistoria();
    }

    void inicio() {
        txtNombre.setText(paciente.getNombre());
        accionesBotones();
    }

    void detallesHistoria() {

        h = historiaClinicalDAO.findHistoriaPaciente(paciente);
        if (h != null) {
            Anticoagulante.setSelected(h.getAnticoagulante());
            Aspirina.setSelected(h.getAspirina());
            DetalleAlergiasMedicamentos.setText(h.getDetalleAlergias());
            DetalleHepatitis.setText(h.getDetalleHepatitis());
            DetalleIntervension.setText(h.getDetalleCirugias());
            DetalleMedicacion.setText(h.getDetalleMedicacion());
            DetalleTratamientoMedico.setText(h.getDetalleTratamiento());
            DetalleEnfermedadesRespiratorias.setText(h.getDetalleEnfermedades());
            Diabetico.setSelected(h.getDiabetico());
            EnfermedadesRespiratorias.setSelected(h.getEnfermedadesRespiratorias());
            FechaQuimioRadio.setDate(h.getFechaQuimioRadioTerapia());
            FiebreReumatica.setSelected(h.getFiebreReumatica());
            Hepatitis.setSelected(h.getHepatitis());
            Hipertension.setSelected(h.getHipertension());
            Hipoglucemiente.setSelected(h.getHipoglucemiente());
            Infarto.setSelected(h.getInfarto());
            InsulinoDependiente.setSelected(h.getInsulinoDependiente());
            IntervenidoQuirurgicamente.setSelected(h.getCirugias());
            Nitroglicerina.setSelected(h.getNitroglicerina());
            ProblemasDeCicatrizacion.setSelected(h.getProblemas_cicatrizacion());
            ProtesisValvulares.setSelected(h.getProtesisValvulares());
            QuimioRadio.setSelected(h.getQuimioRadioTerapia());
            Sedante.setSelected(h.getSedante());
            TomaMedicacion.setSelected(h.getTomaMedicacion());
            AlergiaMedicamento.setSelected(h.getAlergicoMedicamentos());
            AnginaDePecho.setSelected(h.getAnginaDePecho());
            Hiv.setSelected(h.getHiv());
            TratamientoMedico.setSelected(h.getEstaEnTratamiento());
            Fecha.setDate(h.getFecha());
            txtNombre.setText(paciente.toString());

        }

    }

    void guardarCambios() {

        if (h == null) {
            h = new HistoriaClinica();
        }
        h.setAnticoagulante(Anticoagulante.isSelected());
        h.setAspirina(Aspirina.isSelected());
        h.setDetalleAlergias(DetalleAlergiasMedicamentos.getText());
        h.setDetalleHepatitis(DetalleHepatitis.getText());
        h.setDetalleCirugias(DetalleIntervension.getText());
        h.setDetalleMedicacion(DetalleMedicacion.getText());
        h.setDetalleTratamiento(TratamientoMedico.getText());
        h.setDetalleEnfermedades(DetalleEnfermedadesRespiratorias.getText());
        h.setDiabetico(Diabetico.isSelected());
        h.setEnfermedadesRespiratorias(EnfermedadesRespiratorias.isSelected());
        h.setFechaQuimioRadioTerapia(FechaQuimioRadio.getDate());
        h.setFiebreReumatica(FiebreReumatica.isSelected());
        h.setHepatitis(Hepatitis.isSelected());
        h.setHipertension(Hipertension.isSelected());
        h.setHipoglucemiente(Hipoglucemiente.isSelected());
        h.setInfarto(Infarto.isSelected());
        h.setInsulinoDependiente(InsulinoDependiente.isSelected());
        h.setCirugias(IntervenidoQuirurgicamente.isSelected());
        h.setNitroglicerina(Nitroglicerina.isSelected());
        h.setProblemas_cicatrizacion(ProblemasDeCicatrizacion.isSelected());
        h.setProtesisValvulares(ProtesisValvulares.isSelected());
        h.setQuimioRadioTerapia(QuimioRadio.isSelected());
        h.setSedante(Sedante.isSelected());
        h.setTomaMedicacion(TomaMedicacion.isSelected());
        h.setAlergicoMedicamentos(AlergiaMedicamento.isSelected());
        h.setAnginaDePecho(AnginaDePecho.isSelected());
        h.setHiv(Hiv.isSelected());
        h.setEstaEnTratamiento(TratamientoMedico.isSelected());
        h.setFecha(Fecha.getDate());
        h.setPaciente(paciente);

        historiaClinicalDAO.save(h);

        this.dispose();
    }

    void accionesBotones() {
        TratamientoMedico.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (TratamientoMedico.isSelected()) {
                    DetalleTratamientoMedico.setEnabled(true);
                } else {
                    DetalleTratamientoMedico.setEnabled(false);
                    DetalleTratamientoMedico.setText("");
                }
            }
        });
        TomaMedicacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (TomaMedicacion.isSelected()) {
                    DetalleMedicacion.setEnabled(true);
                } else {
                    DetalleMedicacion.setEnabled(false);
                    DetalleMedicacion.setText("");
                }
            }
        });
        EnfermedadesRespiratorias.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (EnfermedadesRespiratorias.isSelected()) {
                    DetalleEnfermedadesRespiratorias.setEnabled(true);
                } else {
                    DetalleEnfermedadesRespiratorias.setEnabled(false);
                    DetalleEnfermedadesRespiratorias.setText("");
                }
            }
        });
        AlergiaMedicamento.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (AlergiaMedicamento.isSelected()) {
                    DetalleAlergiasMedicamentos.setEnabled(true);
                } else {
                    DetalleAlergiasMedicamentos.setEnabled(false);
                    DetalleAlergiasMedicamentos.setText("");
                }
            }
        });
        Hepatitis.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (Hepatitis.isSelected()) {
                    DetalleHepatitis.setEnabled(true);
                } else {
                    DetalleHepatitis.setEnabled(false);
                    DetalleHepatitis.setText("");
                }
            }
        });
        IntervenidoQuirurgicamente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (IntervenidoQuirurgicamente.isSelected()) {
                    DetalleIntervension.setEnabled(true);
                } else {
                    DetalleIntervension.setEnabled(false);
                    DetalleIntervension.setText("");
                }
            }
        });
        QuimioRadio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (QuimioRadio.isSelected()) {
                    FechaQuimioRadio.setEnabled(true);
                } else {
                    FechaQuimioRadio.setEnabled(false);
                    FechaQuimioRadio.setDate(null);
                }
            }
        });

        btnGuardar1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                guardarCambios();
            }
        });
        btnVolver1.addMouseListener(new MouseAdapter() {
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
        jPanel3 = new javax.swing.JPanel();
        btnGuardar1 = new javax.swing.JButton();
        btnVolver1 = new javax.swing.JButton();
        btnConsentimiento = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        DetalleMedicacion = new javax.swing.JTextField();
        DetalleIntervension = new javax.swing.JTextField();
        DetalleTratamientoMedico = new javax.swing.JTextField();
        Fecha = new org.jdesktop.swingx.JXDatePicker();
        Infarto = new javax.swing.JCheckBox();
        TomaMedicacion = new javax.swing.JCheckBox();
        TratamientoMedico = new javax.swing.JCheckBox();
        Hiv = new javax.swing.JCheckBox();
        AnginaDePecho = new javax.swing.JCheckBox();
        AlergiaMedicamento = new javax.swing.JCheckBox();
        FiebreReumatica = new javax.swing.JCheckBox();
        ProtesisValvulares = new javax.swing.JCheckBox();
        Hipertension = new javax.swing.JCheckBox();
        DetalleEnfermedadesRespiratorias = new javax.swing.JTextField();
        Diabetico = new javax.swing.JCheckBox();
        InsulinoDependiente = new javax.swing.JCheckBox();
        Hepatitis = new javax.swing.JCheckBox();
        Aspirina = new javax.swing.JCheckBox();
        IntervenidoQuirurgicamente = new javax.swing.JCheckBox();
        Anticoagulante = new javax.swing.JCheckBox();
        ProblemasDeCicatrizacion = new javax.swing.JCheckBox();
        QuimioRadio = new javax.swing.JCheckBox();
        EnfermedadesRespiratorias = new javax.swing.JCheckBox();
        DetalleAlergiasMedicamentos = new javax.swing.JTextField();
        DetalleHepatitis = new javax.swing.JTextField();
        FechaQuimioRadio = new org.jdesktop.swingx.JXDatePicker();
        Sedante = new javax.swing.JCheckBox();
        Hipoglucemiente = new javax.swing.JCheckBox();
        Nitroglicerina = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(950, 600));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel3.setFont(new java.awt.Font("Arial", 3, 48)); // NOI18N
        jLabel3.setText("Historia clinica");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(jLabel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        btnGuardar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save32.png"))); // NOI18N
        btnGuardar1.setText("Guardar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(btnGuardar1, gridBagConstraints);

        btnVolver1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnVolver1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/back32.png"))); // NOI18N
        btnVolver1.setText("Volver");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(btnVolver1, gridBagConstraints);

        btnConsentimiento.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsentimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/medical-history24.png"))); // NOI18N
        btnConsentimiento.setText("Imprimir");
        btnConsentimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsentimientoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(btnConsentimiento, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        txtNombre.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(txtNombre, gridBagConstraints);

        DetalleMedicacion.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(DetalleMedicacion, gridBagConstraints);

        DetalleIntervension.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(DetalleIntervension, gridBagConstraints);

        DetalleTratamientoMedico.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(DetalleTratamientoMedico, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(Fecha, gridBagConstraints);

        Infarto.setText("Infarto");
        Infarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InfartoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(Infarto, gridBagConstraints);

        TomaMedicacion.setText("Toma alguna Medicación:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel1.add(TomaMedicacion, gridBagConstraints);

        TratamientoMedico.setText("Tratamiento médico:");
        TratamientoMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TratamientoMedicoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(TratamientoMedico, gridBagConstraints);

        Hiv.setText("HIV");
        Hiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HivActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(Hiv, gridBagConstraints);

        AnginaDePecho.setText("Angina de Pecho");
        AnginaDePecho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnginaDePechoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(AnginaDePecho, gridBagConstraints);

        AlergiaMedicamento.setText("Alérgia a medicamento:");
        AlergiaMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlergiaMedicamentoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(AlergiaMedicamento, gridBagConstraints);

        FiebreReumatica.setText("Fiebre Reumática");
        FiebreReumatica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiebreReumaticaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(FiebreReumatica, gridBagConstraints);

        ProtesisValvulares.setText("Prótesis Valvulares");
        ProtesisValvulares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProtesisValvularesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(ProtesisValvulares, gridBagConstraints);

        Hipertension.setText("Hipertensión");
        Hipertension.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HipertensionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(Hipertension, gridBagConstraints);

        DetalleEnfermedadesRespiratorias.setEnabled(false);
        DetalleEnfermedadesRespiratorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetalleEnfermedadesRespiratoriasActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(DetalleEnfermedadesRespiratorias, gridBagConstraints);

        Diabetico.setText("Diabético");
        Diabetico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiabeticoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(Diabetico, gridBagConstraints);

        InsulinoDependiente.setText("Insulino dependiente");
        InsulinoDependiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsulinoDependienteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(InsulinoDependiente, gridBagConstraints);

        Hepatitis.setText("Hepatitis");
        Hepatitis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HepatitisActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(Hepatitis, gridBagConstraints);

        Aspirina.setText("Aspirina");
        Aspirina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AspirinaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(Aspirina, gridBagConstraints);

        IntervenidoQuirurgicamente.setText("Intervenido quirúrgicamente:");
        IntervenidoQuirurgicamente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IntervenidoQuirurgicamenteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(IntervenidoQuirurgicamente, gridBagConstraints);

        Anticoagulante.setText("Anticoagulante");
        Anticoagulante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnticoagulanteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(Anticoagulante, gridBagConstraints);

        ProblemasDeCicatrizacion.setText("Problemas de cicatrizacion");
        ProblemasDeCicatrizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProblemasDeCicatrizacionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(ProblemasDeCicatrizacion, gridBagConstraints);

        QuimioRadio.setText("Quimioterapia o Radioterapia:");
        QuimioRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuimioRadioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(QuimioRadio, gridBagConstraints);

        EnfermedadesRespiratorias.setText("Enfermedades Respiratorias:");
        EnfermedadesRespiratorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnfermedadesRespiratoriasActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(EnfermedadesRespiratorias, gridBagConstraints);

        DetalleAlergiasMedicamentos.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(DetalleAlergiasMedicamentos, gridBagConstraints);

        DetalleHepatitis.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(DetalleHepatitis, gridBagConstraints);

        FechaQuimioRadio.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(FechaQuimioRadio, gridBagConstraints);

        Sedante.setText("Sedante");
        Sedante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SedanteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(Sedante, gridBagConstraints);

        Hipoglucemiente.setText("Hipoglucemiente");
        Hipoglucemiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HipoglucemienteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(Hipoglucemiente, gridBagConstraints);

        Nitroglicerina.setText("Nitroglicerina");
        Nitroglicerina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NitroglicerinaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(Nitroglicerina, gridBagConstraints);

        jLabel11.setText("Fecha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel11, gridBagConstraints);

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

    private void InfartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InfartoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InfartoActionPerformed

    private void TratamientoMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TratamientoMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TratamientoMedicoActionPerformed

    private void HivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HivActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HivActionPerformed

    private void AnginaDePechoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnginaDePechoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AnginaDePechoActionPerformed

    private void AlergiaMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlergiaMedicamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlergiaMedicamentoActionPerformed

    private void FiebreReumaticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiebreReumaticaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FiebreReumaticaActionPerformed

    private void ProtesisValvularesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProtesisValvularesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProtesisValvularesActionPerformed

    private void HipertensionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HipertensionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HipertensionActionPerformed

    private void DiabeticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiabeticoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiabeticoActionPerformed

    private void InsulinoDependienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsulinoDependienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InsulinoDependienteActionPerformed

    private void HepatitisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HepatitisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HepatitisActionPerformed

    private void AspirinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AspirinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AspirinaActionPerformed

    private void IntervenidoQuirurgicamenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IntervenidoQuirurgicamenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IntervenidoQuirurgicamenteActionPerformed

    private void AnticoagulanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnticoagulanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AnticoagulanteActionPerformed

    private void ProblemasDeCicatrizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProblemasDeCicatrizacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProblemasDeCicatrizacionActionPerformed

    private void QuimioRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuimioRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QuimioRadioActionPerformed

    private void EnfermedadesRespiratoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnfermedadesRespiratoriasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EnfermedadesRespiratoriasActionPerformed

    private void SedanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SedanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SedanteActionPerformed

    private void HipoglucemienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HipoglucemienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HipoglucemienteActionPerformed

    private void NitroglicerinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NitroglicerinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NitroglicerinaActionPerformed

    private void DetalleEnfermedadesRespiratoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetalleEnfermedadesRespiratoriasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DetalleEnfermedadesRespiratoriasActionPerformed

    private void btnConsentimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsentimientoActionPerformed

        try {
            HashMap parametros = new HashMap();
            parametros.clear();
            parametros.put("paciente", paciente.getId());

            Connection conexion = DriverManager.getConnection(props.getUrl(), props.getUsr(), props.getPsw());

            InputStream resource = getClass().getClassLoader().getResourceAsStream("reportes/historia_clinica.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(resource, parametros, conexion);
            JasperViewer reporte = new JasperViewer(jasperPrint, false);
            reporte.setVisible(true);

            reporte.toFront();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! " + e, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnConsentimientoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox AlergiaMedicamento;
    private javax.swing.JCheckBox AnginaDePecho;
    private javax.swing.JCheckBox Anticoagulante;
    private javax.swing.JCheckBox Aspirina;
    private javax.swing.JTextField DetalleAlergiasMedicamentos;
    private javax.swing.JTextField DetalleEnfermedadesRespiratorias;
    private javax.swing.JTextField DetalleHepatitis;
    private javax.swing.JTextField DetalleIntervension;
    private javax.swing.JTextField DetalleMedicacion;
    private javax.swing.JTextField DetalleTratamientoMedico;
    private javax.swing.JCheckBox Diabetico;
    private javax.swing.JCheckBox EnfermedadesRespiratorias;
    private org.jdesktop.swingx.JXDatePicker Fecha;
    private org.jdesktop.swingx.JXDatePicker FechaQuimioRadio;
    private javax.swing.JCheckBox FiebreReumatica;
    private javax.swing.JCheckBox Hepatitis;
    private javax.swing.JCheckBox Hipertension;
    private javax.swing.JCheckBox Hipoglucemiente;
    private javax.swing.JCheckBox Hiv;
    private javax.swing.JCheckBox Infarto;
    private javax.swing.JCheckBox InsulinoDependiente;
    private javax.swing.JCheckBox IntervenidoQuirurgicamente;
    private javax.swing.JCheckBox Nitroglicerina;
    private javax.swing.JCheckBox ProblemasDeCicatrizacion;
    private javax.swing.JCheckBox ProtesisValvulares;
    private javax.swing.JCheckBox QuimioRadio;
    private javax.swing.JCheckBox Sedante;
    private javax.swing.JCheckBox TomaMedicacion;
    private javax.swing.JCheckBox TratamientoMedico;
    public javax.swing.JButton btnConsentimiento;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnVolver1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
