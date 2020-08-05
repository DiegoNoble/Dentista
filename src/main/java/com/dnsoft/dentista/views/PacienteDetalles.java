package com.dnsoft.dentista.views;

import com.dnsoft.dentista.beans.ClaseTratamiento;
import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.beans.SexoEnum;
import com.dnsoft.dentista.daos.IPacienteDAO;
import com.dnsoft.dentista.utiles.Container;
import com.dnsoft.dentista.utiles.OptionPaneEstandar;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import sun.awt.image.ByteArrayImageSource;
import sun.awt.image.ToolkitImage;
import com.dnsoft.dentista.daos.IClaseTratamientoDAO;
import com.dnsoft.dentista.utiles.LeeProperties;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Diego Noble
 */
public class PacienteDetalles extends javax.swing.JDialog {

    Container container;
    IPacienteDAO pacienteDAO;
    IClaseTratamientoDAO clasePacienteDAO;
    Paciente paciente;
    LeeProperties props = new LeeProperties();

    public PacienteDetalles(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/diente.jpg")));
        //CIERRA JOPTIONPANE CON ESCAPE
        jPanel1.grabFocus();
        jPanel1.addKeyListener(new OptionPaneEstandar(this));

        this.container = Container.getInstancia();
        pacienteDAO = container.getBean(IPacienteDAO.class);
        clasePacienteDAO = container.getBean(IClaseTratamientoDAO.class);
        setLocationRelativeTo(null);
        dpIngreso.setDate(new Date());
        paciente = new Paciente();
        cbActivo.setSelected(true);

        inicio();

    }

    public PacienteDetalles(java.awt.Frame parent, boolean modal, Paciente PacienteSeleccionado) {
        super(parent, modal);
        initComponents();
        this.container = Container.getInstancia();
        pacienteDAO = container.getBean(IPacienteDAO.class);
        clasePacienteDAO = container.getBean(IClaseTratamientoDAO.class);
        this.paciente = PacienteSeleccionado;
        setLocationRelativeTo(null);
        inicio();
        detallesPaciente();
    }

    void inicio() {
        cbSexo.setModel(new DefaultComboBoxModel(SexoEnum.values()));
        if (paciente.getFechaConsentimientoExtraccionMolares() != null) {
            btnConsentimiento.setEnabled(true);
        }
        if (paciente.isFinaizaOrtodoncia() == true) {
            btnFinalizaOrtodoncia.setEnabled(true);
        }
        cargaCategorias();
        accionesBotones();
    }

    void cargaCategorias() {
        //AutoCompleteDecorator.decorate(cbCategoria);
        cbCategoria.removeAllItems();
        for (ClaseTratamiento categoria : clasePacienteDAO.findAll()) {
            cbCategoria.addItem(categoria);
        }
    }

    void detallesPaciente() {
        txtCel.setText(paciente.getCelular());
        txtDireccion.setText(paciente.getDireccion());
        txtDocumento.setText(paciente.getDocumento());
        txtEmail.setText(paciente.getEmail());
        dpIngreso.setDate(paciente.getFechaIngreso());
        dpNacimiento.setDate(paciente.getFechaNacimiento());
        cbActivo.setSelected(paciente.isActivo());
        cbGoogleCalendar.setSelected(paciente.getNotificaGoogleCalendar());
        chbConsentimiento.setSelected(paciente.isConsentimientoEtraccionMolares());
        chbFinalizaOrtodoncia.setSelected(paciente.isFinaizaOrtodoncia());
        FechaConsentimiento.setDate(paciente.getFechaConsentimientoExtraccionMolares());
        FechaFinalizaOrtodoncia.setDate(paciente.getFechaFinalizaOrtodoncia());

        if (paciente.getFoto() != null) {
            Image imagen = new ToolkitImage(new ByteArrayImageSource(paciente.getFoto()));
            Image imgReDimensionada = imagen.getScaledInstance(jlblFoto.getWidth(), jlblFoto.getHeight(),
                    Image.SCALE_SMOOTH);

            ImageIcon icon = new ImageIcon(imgReDimensionada);

            jlblFoto.setIcon(icon);

        }
        txtNombre.setText(paciente.getNombre());
        txtObservaciones.setText(paciente.getObservaciones());
        cbSexo.setSelectedItem(paciente.getSexo());
        txtTel.setText(paciente.getTelefono());
        cbCategoria.setSelectedItem(paciente.getClaseTratamiento());

    }

    void guardarCambios() {
        paciente.setCelular(txtCel.getText());
        paciente.setDireccion(txtDireccion.getText());
        paciente.setDocumento(txtDocumento.getText());
        paciente.setEmail(txtEmail.getText());
        paciente.setFechaIngreso(dpIngreso.getDate());
        paciente.setFechaNacimiento(dpNacimiento.getDate());
        paciente.setNombre(txtNombre.getText());
        paciente.setActivo(cbActivo.isSelected());
        paciente.setNotificaGoogleCalendar(cbGoogleCalendar.isSelected());
        paciente.setConsentimientoEtraccionMolares(chbConsentimiento.isSelected());
        paciente.setFinaizaOrtodoncia(chbFinalizaOrtodoncia.isSelected());
        paciente.setFechaConsentimientoExtraccionMolares(FechaConsentimiento.getDate());
        paciente.setFechaFinalizaOrtodoncia(FechaFinalizaOrtodoncia.getDate());

        if (!txtFoto.getText().equals("")) {
            FileInputStream fileInputStream = null;
            try {
                File file = new File(txtFoto.getText());
                byte[] fotoEnFormatoBytes = new byte[(int) file.length()];
                fileInputStream = new FileInputStream(file);
                fileInputStream.read(fotoEnFormatoBytes);
                //fileInputStream.close();
                paciente.setFoto(fotoEnFormatoBytes);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(PacienteDetalles.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PacienteDetalles.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fileInputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(PacienteDetalles.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        paciente.setNombre(txtNombre.getText());
        paciente.setObservaciones(txtObservaciones.getText());
        paciente.setSexo((SexoEnum) cbSexo.getSelectedItem());
        paciente.setTelefono(txtTel.getText());
        paciente.setClaseTratamiento((ClaseTratamiento) cbCategoria.getSelectedItem());
        pacienteDAO.save(paciente);

        this.dispose();
    }

    void accionesBotones() {
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

        chbFinalizaOrtodoncia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (chbFinalizaOrtodoncia.isSelected()) {
                    FechaFinalizaOrtodoncia.setEnabled(true);
                } else {
                    FechaFinalizaOrtodoncia.setEnabled(false);
                }
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
        btnHistoriaClinica = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCel = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        dpNacimiento = new org.jdesktop.swingx.JXDatePicker();
        dpIngreso = new org.jdesktop.swingx.JXDatePicker();
        jLabel11 = new javax.swing.JLabel();
        jlblFoto = new javax.swing.JLabel();
        btnFoto = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        cbSexo = new javax.swing.JComboBox();
        txtFoto = new javax.swing.JTextField();
        cbCategoria = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        btnNuevaCategoria = new javax.swing.JButton();
        cbActivo = new javax.swing.JCheckBox();
        cbGoogleCalendar = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        chbConsentimiento = new javax.swing.JCheckBox();
        FechaConsentimiento = new org.jdesktop.swingx.JXDatePicker();
        chbFinalizaOrtodoncia = new javax.swing.JCheckBox();
        FechaFinalizaOrtodoncia = new org.jdesktop.swingx.JXDatePicker();
        btnConsentimiento = new javax.swing.JButton();
        btnFinalizaOrtodoncia = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(950, 600));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel3.setFont(new java.awt.Font("Arial", 3, 48)); // NOI18N
        jLabel3.setText("Detalles paciente");
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

        btnHistoriaClinica.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHistoriaClinica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/medical-history24.png"))); // NOI18N
        btnHistoriaClinica.setText("Historia clínica");
        btnHistoriaClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoriaClinicaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(btnHistoriaClinica, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(txtEmail, gridBagConstraints);

        jLabel4.setText("Dirección");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(txtNombre, gridBagConstraints);

        jLabel5.setText("Tel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(txtTel, gridBagConstraints);

        jLabel6.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(txtDireccion, gridBagConstraints);

        jLabel7.setText("Cel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(txtCel, gridBagConstraints);

        jLabel9.setText("Categoría");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(txtDocumento, gridBagConstraints);

        jLabel8.setText("Observaciones");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel8, gridBagConstraints);

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane1.setViewportView(txtObservaciones);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jLabel10.setText("Fecha Ingreso");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel10, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(dpNacimiento, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(dpIngreso, gridBagConstraints);

        jLabel11.setText("Fecha nacimiento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel11, gridBagConstraints);

        jlblFoto.setBackground(new java.awt.Color(204, 204, 204));
        jlblFoto.setOpaque(true);
        jlblFoto.setPreferredSize(new java.awt.Dimension(3, 4));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.ipadx = 210;
        gridBagConstraints.ipady = 170;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jlblFoto, gridBagConstraints);

        btnFoto.setText("...");
        btnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFotoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(btnFoto, gridBagConstraints);

        jLabel12.setText("Sexo"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel12, gridBagConstraints);

        cbSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M", "F" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(cbSexo, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(txtFoto, gridBagConstraints);

        cbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoriaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(cbCategoria, gridBagConstraints);

        jLabel13.setText("Documento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel13, gridBagConstraints);

        btnNuevaCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add.png"))); // NOI18N
        btnNuevaCategoria.setBorder(null);
        btnNuevaCategoria.setBorderPainted(false);
        btnNuevaCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaCategoriaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        jPanel1.add(btnNuevaCategoria, gridBagConstraints);

        cbActivo.setText("Activo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel1.add(cbActivo, gridBagConstraints);

        cbGoogleCalendar.setText("Notifica Google Calendar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        jPanel1.add(cbGoogleCalendar, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tratamientos"));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        chbConsentimiento.setText("Consentimiento Extración Molares");
        chbConsentimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbConsentimientoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(chbConsentimiento, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(FechaConsentimiento, gridBagConstraints);

        chbFinalizaOrtodoncia.setText("Finaliza Ortodoncia");
        chbFinalizaOrtodoncia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbFinalizaOrtodonciaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(chbFinalizaOrtodoncia, gridBagConstraints);

        FechaFinalizaOrtodoncia.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(FechaFinalizaOrtodoncia, gridBagConstraints);

        btnConsentimiento.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsentimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/medical-history24.png"))); // NOI18N
        btnConsentimiento.setText("Imprimir");
        btnConsentimiento.setEnabled(false);
        btnConsentimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsentimientoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(btnConsentimiento, gridBagConstraints);

        btnFinalizaOrtodoncia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnFinalizaOrtodoncia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/medical-history24.png"))); // NOI18N
        btnFinalizaOrtodoncia.setText("Imprimir");
        btnFinalizaOrtodoncia.setEnabled(false);
        btnFinalizaOrtodoncia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizaOrtodonciaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(btnFinalizaOrtodoncia, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 5;
        jPanel1.add(jPanel4, gridBagConstraints);

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

    private void btnFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFotoActionPerformed

        JFileChooser buscarFoto = new JFileChooser();
        buscarFoto.setCurrentDirectory(new File("c://"));
        buscarFoto.setDialogTitle("Cargar Foto");
        buscarFoto.showOpenDialog(this);
        String foto = buscarFoto.getSelectedFile().getAbsolutePath();
        txtFoto.setText(foto);

        ImageIcon fot = new ImageIcon(foto);
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(jlblFoto.getWidth(), jlblFoto.getHeight(), Image.SCALE_DEFAULT));
        jlblFoto.setIcon(icono);
        this.repaint();

        Image imgReDimensionada = fot.getImage().getScaledInstance(jlblFoto.getWidth(), jlblFoto.getHeight(),
                Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(imgReDimensionada);

        jlblFoto.setIcon(icon);

        //txtFoto.setText(webCam.getTempFile().getAbsolutePath());

    }//GEN-LAST:event_btnFotoActionPerformed

    private void btnNuevaCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaCategoriaActionPerformed
        ClaseTratamientoDetalles nuevoInquilino = new ClaseTratamientoDetalles(null, true);
        nuevoInquilino.setVisible(true);
        nuevoInquilino.toFront();
        cargaCategorias();
    }//GEN-LAST:event_btnNuevaCategoriaActionPerformed

    private void cbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCategoriaActionPerformed

    private void btnHistoriaClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoriaClinicaActionPerformed

        HistoriaClinicaDetalles clinicaDetalles = new HistoriaClinicaDetalles(null, false, paciente);
        clinicaDetalles.setVisible(true);
        clinicaDetalles.toFront();
    }//GEN-LAST:event_btnHistoriaClinicaActionPerformed

    private void chbConsentimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbConsentimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbConsentimientoActionPerformed

    private void chbFinalizaOrtodonciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbFinalizaOrtodonciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbFinalizaOrtodonciaActionPerformed

    private void btnConsentimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsentimientoActionPerformed
      
         try {
            HashMap parametros = new HashMap();
            parametros.clear();
            parametros.put("paciente", paciente.getId());

            Connection conexion = DriverManager.getConnection(props.getUrl(), props.getUsr(), props.getPsw());

            InputStream resource = getClass().getClassLoader().getResourceAsStream("reportes/extraccion.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(resource, parametros, conexion);
            JasperViewer reporte = new JasperViewer(jasperPrint, false);
            reporte.setVisible(true);

            reporte.toFront();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! " + e, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
         
    }//GEN-LAST:event_btnConsentimientoActionPerformed

    private void btnFinalizaOrtodonciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizaOrtodonciaActionPerformed
        try {
            HashMap parametros = new HashMap();
            parametros.clear();
            parametros.put("paciente", paciente.getId());

            Connection conexion = DriverManager.getConnection(props.getUrl(), props.getUsr(), props.getPsw());

            InputStream resource = getClass().getClassLoader().getResourceAsStream("reportes/finaliza_ortodoncia.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(resource, parametros, conexion);
            JasperViewer reporte = new JasperViewer(jasperPrint, false);
            reporte.setVisible(true);

            reporte.toFront();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! " + e, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnFinalizaOrtodonciaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker FechaConsentimiento;
    private org.jdesktop.swingx.JXDatePicker FechaFinalizaOrtodoncia;
    public javax.swing.JButton btnConsentimiento;
    public javax.swing.JButton btnFinalizaOrtodoncia;
    private javax.swing.JButton btnFoto;
    private javax.swing.JButton btnGuardar1;
    public javax.swing.JButton btnHistoriaClinica;
    public javax.swing.JButton btnNuevaCategoria;
    private javax.swing.JButton btnVolver1;
    private javax.swing.JCheckBox cbActivo;
    private javax.swing.JComboBox cbCategoria;
    private javax.swing.JCheckBox cbGoogleCalendar;
    private javax.swing.JComboBox cbSexo;
    private javax.swing.JCheckBox chbConsentimiento;
    private javax.swing.JCheckBox chbFinalizaOrtodoncia;
    private org.jdesktop.swingx.JXDatePicker dpIngreso;
    private org.jdesktop.swingx.JXDatePicker dpNacimiento;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlblFoto;
    private javax.swing.JTextField txtCel;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFoto;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
}
