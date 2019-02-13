package com.dnsoft.dentista.views;

import com.dnsoft.dentista.beans.PlanTratamiento;
import com.dnsoft.dentista.beans.TrabajosProveedor;
import com.dnsoft.dentista.daos.IProveedorDAO;
import com.dnsoft.dentista.daos.ITrabajosProveedorDAO;
import com.dnsoft.dentista.renderers.LocalDateCellRenderer;
import com.dnsoft.dentista.renderers.TabelaTextAreaRenderer;
import com.dnsoft.dentista.renderers.TableRendererColorTrabajoProveedor;
import com.dnsoft.dentista.tablemodels.RecordatorioTrabajosProveedorTableModel;
import com.dnsoft.dentista.utiles.Container;
import com.dnsoft.dentista.utiles.InternalFrameEstandar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Diego Noble
 */
public final class RecoratorioTrabajosProveedor extends InternalFrameEstandar {

    ITrabajosProveedorDAO trabajosProveedorDAO;
    Container container;
    RecordatorioTrabajosProveedorTableModel tableModel;
    List<TrabajosProveedor> list;
    ListSelectionModel selectionListener;
    IProveedorDAO proveedorDAO;
    PlanTratamiento planTratamiento;
    TrabajosProveedor trabajosProveedorSeleccionado;

    public RecoratorioTrabajosProveedor() {
        initComponents();
        toFront();
        setVisible(true);
        //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/diente.jpg")));
        //CIERRA JOPTIONPANE CON ESCAPE
        setLocation(700, 0);
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
        actualizaTbl();
        accionesBotones();
    }

    void actualizaTbl() {
        list.clear();
        list.addAll(trabajosProveedorDAO.findTrabajosPenientes());
        tableModel.fireTableDataChanged();
        if (list.isEmpty()) {
            this.dispose();
        }
    }

    private void defineModelo() {
        ((DefaultTableCellRenderer) tblHistoriaTrabajos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        tableModel = new RecordatorioTrabajosProveedorTableModel(list);
        tblHistoriaTrabajos.setModel(tableModel);

        tblHistoriaTrabajos.getColumn("Comentarios").setCellRenderer(new TabelaTextAreaRenderer());
        tblHistoriaTrabajos.getColumn("Fecha solicitud").setCellRenderer(new LocalDateCellRenderer());
        tblHistoriaTrabajos.getColumn("Fecha programada").setCellRenderer(new TableRendererColorTrabajoProveedor(0));

        tblHistoriaTrabajos.setRowHeight(50);
        int[] anchos = {5, 10, 200, 5, 200};

        for (int i = 0; i < tblHistoriaTrabajos.getColumnCount(); i++) {

            tblHistoriaTrabajos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }
        selectionListener = tblHistoriaTrabajos.getSelectionModel();
        selectionListener.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tblHistoriaTrabajos.getSelectedRow() != -1) {
                    btnFinalizar.setEnabled(true);
                    trabajosProveedorSeleccionado = list.get(tblHistoriaTrabajos.getSelectedRow());
                } else {
                    btnFinalizar.setEnabled(false);
                }
            }
        });

    }

    void accionesBotones() {

        btnFinalizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                trabajosProveedorSeleccionado.setFechaEntrega(LocalDate.now());
                trabajosProveedorSeleccionado.setFinalizado(true);
                trabajosProveedorDAO.save(trabajosProveedorSeleccionado);
                actualizaTbl();
            }
        });

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHistoriaTrabajos = new javax.swing.JTable();
        btnFinalizar = new botones.BotonSeleccionar();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setPreferredSize(new java.awt.Dimension(850, 598));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel3.setFont(new java.awt.Font("Arial", 3, 48)); // NOI18N
        jLabel3.setText("Recordatorio de tercerizados");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(jLabel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private botones.BotonSeleccionar btnFinalizar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblHistoriaTrabajos;
    // End of variables declaration//GEN-END:variables

}
