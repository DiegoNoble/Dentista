package com.dnsoft.dentista.views;

import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.beans.FotosTratamiento;
import com.dnsoft.dentista.beans.PlanTratamiento;
import com.dnsoft.dentista.beans.TrabajosTratamiento;
import com.dnsoft.dentista.daos.ICuentaPacienteDAO;
import com.dnsoft.dentista.daos.IFotosTratamientoDAO;
import com.dnsoft.dentista.daos.IPacienteDAO;
import com.dnsoft.dentista.daos.ITrabajosDAO;
import com.dnsoft.dentista.renderers.LocalDateCellRenderer;
import com.dnsoft.dentista.tablemodels.FotosTratamientoTableModel;
import com.dnsoft.dentista.tablemodels.TrabajosTratamientoTableModel;
import com.dnsoft.dentista.utiles.Container;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import sun.awt.image.ByteArrayImageSource;
import sun.awt.image.ToolkitImage;

public final class FotosTratamientoView extends javax.swing.JFrame {

    List<Paciente> listPacientes;
    FotosTratamientoTableModel tableModelFotosTratamiento;
    TrabajosTratamientoTableModel tableModelTrabajos;
    List<FotosTratamiento> listFotosTratamientos;
    List<TrabajosTratamiento> listTrabajos;
    FotosTratamiento FotosTratamientoSeleccionado;
    ITrabajosDAO trabajosDAO;
    IPacienteDAO pacienteDAO;
    ICuentaPacienteDAO cuentaPacienteDAO;
    IFotosTratamientoDAO FotosTratamientoDAO;
    Container container;
    PlanTratamiento planTratamiento;

    public FotosTratamientoView() {
        initComponents();
        inicio();
        buscarFotosTratamiento();
    }

    public FotosTratamientoView(PlanTratamiento planTratamiento) {
        initComponents();
        inicio();

        this.planTratamiento = planTratamiento;
        buscarFotosTratamiento();
    }

    void inicio() {
        container = Container.getInstancia();
        trabajosDAO = container.getBean(ITrabajosDAO.class);
        pacienteDAO = container.getBean(IPacienteDAO.class);
        FotosTratamientoDAO = container.getBean(IFotosTratamientoDAO.class);
        cuentaPacienteDAO = container.getBean(ICuentaPacienteDAO.class);
        setLocationRelativeTo(null);
        defineModelo();
        accionesBotones();
    }

    void buscarFotosTratamiento() {
        listFotosTratamientos.clear();
        listFotosTratamientos.addAll(FotosTratamientoDAO.findByPlanTratamiento(planTratamiento));
        tableModelFotosTratamiento.fireTableDataChanged();

    }

    private void defineModelo() {
        ((DefaultTableCellRenderer) tblPlanesTratamiento.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        listFotosTratamientos = new ArrayList<FotosTratamiento>();
        tableModelFotosTratamiento = new FotosTratamientoTableModel(listFotosTratamientos);
        tblPlanesTratamiento.setModel(tableModelFotosTratamiento);

        tblPlanesTratamiento.getColumn("Fecha").setCellRenderer(new LocalDateCellRenderer());

        tblPlanesTratamiento.setRowHeight(40);
        int[] anchos = {5, 400};

        for (int i = 0; i < tblPlanesTratamiento.getColumnCount(); i++) {

            tblPlanesTratamiento.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }

        ListSelectionModel listModel = tblPlanesTratamiento.getSelectionModel();
        listModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tblPlanesTratamiento.getSelectedRow() != -1) {
                    FotosTratamientoSeleccionado = listFotosTratamientos.get(tblPlanesTratamiento.getSelectedRow());
                    muestraDetalles();
                }

            }
        });

    }

    void muestraDetalles() {

        jTextArea1.setText("");
        jTextArea1.append(FotosTratamientoSeleccionado.getComentarios());
        datePicker1.setDate(FotosTratamientoSeleccionado.getFecha());

        if (FotosTratamientoSeleccionado.getFoto() != null) {
            Image imagen = new ToolkitImage(new ByteArrayImageSource(FotosTratamientoSeleccionado.getFoto()));
            Image imgReDimensionada = imagen.getScaledInstance(jlblFoto.getWidth(), jlblFoto.getHeight(),
                    Image.SCALE_SMOOTH);

            ImageIcon icon = new ImageIcon(imgReDimensionada);

            jlblFoto.setIcon(icon);

        }

    }

    void seleccionarFoto() {
        JFileChooser buscarFoto = new JFileChooser();
        buscarFoto.setCurrentDirectory(new File("c://"));
        buscarFoto.setDialogTitle("Cargar Foto");

        buscarFoto.setFileSelectionMode(JFileChooser.FILES_ONLY);
        buscarFoto.setMultiSelectionEnabled(true);
        buscarFoto.showOpenDialog(this);
        String foto = buscarFoto.getCurrentDirectory().getAbsolutePath();
        txtFoto.setText(foto);

        for (File file : buscarFoto.getSelectedFiles()) {
            guardarFoto(file);
        }

        /*ImageIcon fot = new ImageIcon(foto);
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(jlblFoto.getWidth(), jlblFoto.getHeight(), Image.SCALE_DEFAULT));
        jlblFoto.setIcon(icono);
        this.repaint();

        Image imgReDimensionada = fot.getImage().getScaledInstance(jlblFoto.getWidth(), jlblFoto.getHeight(),
                Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(imgReDimensionada);

        jlblFoto.setIcon(icon);
         */
    }

    void accionesBotones() {
        botonGuardar1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                guardarModificaciones();
            }
        });
        botonNuevo1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {

                seleccionarFoto();
                buscarFotosTratamiento();
                JOptionPane.showMessageDialog(null, "Fotos guardadas correctamente!");
            }
        });

        botonEliminar1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {

                FotosTratamientoDAO.delete(FotosTratamientoSeleccionado);
                JOptionPane.showMessageDialog(null, "Eliminado correctamente!");
                buscarFotosTratamiento();
            }
        });

    }

    void guardarFoto(File file) {

        FotosTratamiento nuevaFoto = new FotosTratamiento();
        nuevaFoto.setComentarios(jTextArea1.getText());
        nuevaFoto.setFecha(datePicker1.getDate());

        if (!txtFoto.getText().equals("")) {
            FileInputStream fileInputStream = null;
            try {
                //File file = new File(txtFoto.getText());
                byte[] fotoEnFormatoBytes = new byte[(int) file.length()];
                fileInputStream = new FileInputStream(file);
                fileInputStream.read(fotoEnFormatoBytes);
                //fileInputStream.close();
                nuevaFoto.setFoto(fotoEnFormatoBytes);

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

        nuevaFoto.setPaciente(planTratamiento.getPaciente());
        nuevaFoto.setPlanTratamiento(planTratamiento);

        FotosTratamientoDAO.save(nuevaFoto);

    }

    void guardarModificaciones() {

        FotosTratamientoSeleccionado.setComentarios(jTextArea1.getText());
        FotosTratamientoSeleccionado.setFecha(datePicker1.getDate());

        FotosTratamientoDAO.save(FotosTratamientoSeleccionado);
        buscarFotosTratamiento();
        JOptionPane.showMessageDialog(null, "Modificado correctamente!");
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
        jPanel12 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPlanesTratamiento = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jlblFoto = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        datePicker1 = new com.github.lgooddatepicker.components.DatePicker();
        txtFoto = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        botonEliminar1 = new botones.BotonEliminar();
        botonNuevo1 = new botones.BotonNuevo();
        botonGuardar1 = new botones.BotonGuardar();
        jPanel4 = new javax.swing.JPanel();

        jTextField1.setText("jTextField1");

        setTitle("Consultorio Odontológico - D.N.Soft .-");
        setPreferredSize(new java.awt.Dimension(900, 600));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Fotos del tratamiento");
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel3.setPreferredSize(new java.awt.Dimension(750, 500));
        jPanel3.setLayout(new java.awt.GridBagLayout());

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

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jlblFoto.setBackground(new java.awt.Color(204, 204, 204));
        jlblFoto.setOpaque(true);
        jlblFoto.setPreferredSize(new java.awt.Dimension(3, 4));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.ipadx = 210;
        gridBagConstraints.ipady = 170;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jlblFoto, gridBagConstraints);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jScrollPane2, gridBagConstraints);

        datePicker1.setDate(LocalDate.now());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(datePicker1, gridBagConstraints);

        txtFoto.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(txtFoto, gridBagConstraints);

        jButton1.setText("Maximizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new java.awt.GridBagConstraints());

        botonEliminar1.setText("");
        jPanel5.add(botonEliminar1);

        botonNuevo1.setText("");
        jPanel5.add(botonNuevo1);

        botonGuardar1.setText("");
        jPanel5.add(botonGuardar1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        jPanel2.add(jPanel5, gridBagConstraints);

        jPanel12.add(jPanel2, new java.awt.GridBagConstraints());

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbCodFotosTratamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCodFotosTratamientoActionPerformed

    }//GEN-LAST:event_rbCodFotosTratamientoActionPerformed

    private void rbPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPacienteActionPerformed


    }//GEN-LAST:event_rbPacienteActionPerformed

    private void btnAtenderFotosTratamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtenderFotosTratamientoActionPerformed

    }//GEN-LAST:event_btnAtenderFotosTratamientoActionPerformed

    private void btnExportarFotosTratamientoXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarFotosTratamientoXMLActionPerformed


    }//GEN-LAST:event_btnExportarFotosTratamientoXMLActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MaximizaFoto maximizaFoto = new MaximizaFoto(this, true, FotosTratamientoSeleccionado);
        maximizaFoto.setVisible(true);
        maximizaFoto.toFront();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private botones.BotonEliminar botonEliminar1;
    private botones.BotonGuardar botonGuardar1;
    private botones.BotonNuevo botonNuevo1;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.github.lgooddatepicker.components.DatePicker datePicker1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel jlblFoto;
    private javax.swing.JTable tblPlanesTratamiento;
    private javax.swing.JTextField txtFoto;
    // End of variables declaration//GEN-END:variables

}
