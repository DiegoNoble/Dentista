package com.dnsoft.dentista.views;

import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.beans.PlanTratamiento;
import com.dnsoft.dentista.beans.Proveedor;
import com.dnsoft.dentista.beans.TrabajosProveedor;
import com.dnsoft.dentista.daos.IProveedorDAO;
import com.dnsoft.dentista.daos.ITrabajosProveedorDAO;
import com.dnsoft.dentista.renderers.LocalDateCellRenderer;
import com.dnsoft.dentista.renderers.TabelaTextAreaRenderer;
import com.dnsoft.dentista.tablemodels.TrabajosProveedorTableModel;
import com.dnsoft.dentista.utiles.Container;
import com.dnsoft.dentista.utiles.InternalFrameEstandar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Diego Noble
 */
public final class ConsultaTrabajosProveedor extends InternalFrameEstandar {

    TrabajosProveedor nuevotrabajosProveedor;
    TrabajosProveedor trabajoSeleccionado;
    ITrabajosProveedorDAO trabajosProveedorDAO;
    Container container;
    TrabajosProveedorTableModel tableModel;
    List<TrabajosProveedor> list;
    ListSelectionModel selectionListener;
    IProveedorDAO proveedorDAO;
    PlanTratamiento planTratamiento;

    public ConsultaTrabajosProveedor(PlanTratamiento planTratamiento) {
        initComponents();
        //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/diente.jpg")));
        //CIERRA JOPTIONPANE CON ESCAPE

        this.container = Container.getInstancia();
        this.planTratamiento = planTratamiento;
        inicio();

    }


    void inicio() {
        //setLocationRelativeTo(null);
        trabajosProveedorDAO = container.getBean(ITrabajosProveedorDAO.class);
        proveedorDAO = container.getBean(IProveedorDAO.class);
        list = new ArrayList<TrabajosProveedor>();
        defineModelo();
        actualizaComboBox();
        accionesBoton();

    }

    void actualizaTbl() {
        list.clear();
        list.addAll(trabajosProveedorDAO.findByProveedor((Proveedor) cbProveedor.getSelectedItem()));
        tableModel.fireTableDataChanged();

    }

    void actualizaComboBox() {
        AutoCompleteDecorator.decorate(cbProveedor);
        cbProveedor.removeAllItems();
        for (Proveedor proveedor : proveedorDAO.findAll()) {
            cbProveedor.addItem(proveedor);
        }

    }

    void accionesBoton() {
        btnFinalizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                trabajoSeleccionado.setFechaEntrega(LocalDate.now());
                trabajoSeleccionado.setFinalizado(true);
                trabajosProveedorDAO.save(trabajoSeleccionado);
                actualizaTbl();
            }
        });
    }

    private void defineModelo() {
        ((DefaultTableCellRenderer) tblHistoriaTrabajos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        tableModel = new TrabajosProveedorTableModel(list);
        tblHistoriaTrabajos.setModel(tableModel);

        tblHistoriaTrabajos.getColumn("Comentarios").setCellRenderer(new TabelaTextAreaRenderer());
        tblHistoriaTrabajos.getColumn("Fecha solicitud").setCellRenderer(new LocalDateCellRenderer());
        tblHistoriaTrabajos.getColumn("Fecha programada").setCellRenderer(new LocalDateCellRenderer());
        tblHistoriaTrabajos.getColumn("Fecha finalización").setCellRenderer(new LocalDateCellRenderer());
        tblHistoriaTrabajos.setRowHeight(50);
        int[] anchos = {20, 20, 20, 300};

        for (int i = 0; i < tblHistoriaTrabajos.getColumnCount(); i++) {

            tblHistoriaTrabajos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }
        selectionListener = tblHistoriaTrabajos.getSelectionModel();
        selectionListener.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tblHistoriaTrabajos.getSelectedRow() != -1) {

                    trabajoSeleccionado = list.get(tblHistoriaTrabajos.getSelectedRow());
                    if (trabajoSeleccionado.getFinalizado() != null) {
                        btnFinalizar.setEnabled(true);
                    } else {
                        btnFinalizar.setEnabled(false);
                    }
                }
            }
        });

    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cbProveedor = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHistoriaTrabajos = new javax.swing.JTable();
        btnFinalizar = new botones.BotonSeleccionar();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel3.setText("Consulta trabajos de proveedor");
        jLabel3.setFont(new java.awt.Font("Arial", 3, 48)); // NOI18N
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(jLabel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        cbProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProveedorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(cbProveedor, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Proveedor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel14, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        tblHistoriaTrabajos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tblHistoriaTrabajos.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tblHistoriaTrabajos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tblHistoriaTrabajos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel12.add(jScrollPane2, gridBagConstraints);

        btnFinalizar.setEnabled(false);
        btnFinalizar.setText("Finalizado");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel12.add(btnFinalizar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel12, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProveedorActionPerformed

        actualizaTbl();

    }//GEN-LAST:event_cbProveedorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private botones.BotonSeleccionar btnFinalizar;
    public javax.swing.JComboBox cbProveedor;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblHistoriaTrabajos;
    // End of variables declaration//GEN-END:variables

}
