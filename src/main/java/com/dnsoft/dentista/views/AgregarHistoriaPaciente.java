package com.dnsoft.dentista.views;

import com.dnsoft.dentista.beans.HistoriaPlanTratamiento;
import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.beans.PlanTratamiento;
import com.dnsoft.dentista.beans.SituacionPlanTratamientoEnum;
import com.dnsoft.dentista.daos.IHistoriaPlanTratamientoDAO;
import com.dnsoft.dentista.daos.IPlanTratamientoDAO;
import com.dnsoft.dentista.renderers.LocalDateCellRenderer;
import com.dnsoft.dentista.renderers.TabelaTextAreaRenderer;
import com.dnsoft.dentista.tablemodels.HistoriaPlanTableModel;
import com.dnsoft.dentista.utiles.Container;
import com.dnsoft.dentista.utiles.OptionPaneEstandar;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Diego Noble
 */
public final class AgregarHistoriaPaciente extends javax.swing.JDialog {

    Paciente paciente;
    PlanTratamiento planTratamiento;
    IPlanTratamientoDAO planTratamientoDAO;
    IHistoriaPlanTratamientoDAO hitoriaDAO;
    Container container;
    HistoriaPlanTratamiento historia;
    HistoriaPlanTableModel tableModel;
    List<HistoriaPlanTratamiento> list;
    HistoriaPlanTratamiento historiaSeleccionada;

    public AgregarHistoriaPaciente(java.awt.Frame parent, boolean modal, PlanTratamiento planTratamiento) {
        super(parent, modal);
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/diente.jpg")));
        //CIERRA JOPTIONPANE CON ESCAPE
        jPanel1.grabFocus();
        jPanel1.addKeyListener(new OptionPaneEstandar(this));
        this.planTratamiento = planTratamiento;
        this.container = Container.getInstancia();

        inicio();
        defineModelo();
    }

    void inicio() {
        setLocationRelativeTo(null);
        dpFecha.setDate(LocalDate.now());
        planTratamientoDAO = container.getBean(IPlanTratamientoDAO.class);
        hitoriaDAO = container.getBean(IHistoriaPlanTratamientoDAO.class);

        accionesBotones();

    }

    void actualizaTbl() {
        list.clear();
        list.addAll(hitoriaDAO.findByPlanTratamientoOrderByFechaDesc(planTratamiento));
        tableModel.fireTableDataChanged();

    }

  
    private void defineModelo() {
        ((DefaultTableCellRenderer) tblHistoria.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        list = new ArrayList<HistoriaPlanTratamiento>();
        tableModel = new HistoriaPlanTableModel(list);
        tblHistoria.setModel(tableModel);
        list.addAll(hitoriaDAO.findByPlanTratamientoOrderByFechaDesc(planTratamiento));
        tableModel.fireTableDataChanged();

        tblHistoria.getColumn("Comentarios").setCellRenderer(new TextAreaRenderer());
        tblHistoria.getColumn("Comentarios").setCellEditor(new TextAreaEditor());
        
        tblHistoria.getColumn("Fecha").setCellRenderer(new LocalDateCellRenderer());
        tblHistoria.setRowHeight(70);
        
        int[] anchos = {10, 400};

        for (int i = 0; i < tblHistoria.getColumnCount(); i++) {

            tblHistoria.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }

        ListSelectionModel listModel = tblHistoria.getSelectionModel();
        listModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tblHistoria.getSelectedRow() != -1) {
                    botonEliminar1.setEnabled(true);
                    historiaSeleccionada = list.get(tblHistoria.getSelectedRow());
                } else {
                    botonEliminar1.setEnabled(false);
                }
            }
        });
    }

    void accionesBotones() {
        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (!txtComentarios.getText().equals("")) {
                    registraHistoria();
                    actualizaTbl();
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un detalle", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                dispose();
            }
        });

        botonEliminar1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                hitoriaDAO.delete(historiaSeleccionada);
                actualizaTbl();

            }
        });

    }

    private void actualizaSituacioTratamiento() {

        if (historia.getFinalizado() == Boolean.TRUE) {
            planTratamiento.setFechaFinalizado(dpFecha.getDate());
            planTratamiento.setSituacionPlanTratamientoEnum(SituacionPlanTratamientoEnum.TRATAMIENTO_FINALIZADO);
            this.dispose();
        }
        if (planTratamiento.getSituacionPlanTratamientoEnum() == SituacionPlanTratamientoEnum.CONFIRMA_PRESUPUESTO) {
            planTratamiento.setFechaInicio(dpFecha.getDate());
            planTratamiento.setSituacionPlanTratamientoEnum(SituacionPlanTratamientoEnum.TRATAMIENTO_EN_CURSO);
        }
        planTratamientoDAO.save(planTratamiento);
    }

    private void registraHistoria() {
        historia = new HistoriaPlanTratamiento();
        historia.setFecha(dpFecha.getDate());
        historia.setFinalizado(cbFinalizado.isSelected());
        historia.setPlanTratamiento(planTratamiento);
        historia.setComentarios(txtComentarios.getText());

        hitoriaDAO.save(historia);

        actualizaSituacioTratamiento();
    }

    class TextAreaRenderer extends JScrollPane implements TableCellRenderer {

        JTextArea textarea;

        public TextAreaRenderer() {
            textarea = new JTextArea();
            textarea.setLineWrap(true);
            textarea.setWrapStyleWord(true);
            //textarea.setBorder(new TitledBorder("This is a JTextArea"));
            getViewport().add(textarea);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus,
                int row, int column) {
            textarea.setFont(table.getFont());
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
                textarea.setForeground(table.getSelectionForeground());
                textarea.setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
                textarea.setForeground(table.getForeground());
                textarea.setBackground(table.getBackground());
            }

            textarea.setText((String) value);
            textarea.setCaretPosition(0);
            return this;
        }
    }

    class TextAreaEditor extends DefaultCellEditor {

        protected JScrollPane scrollpane;
        protected JTextArea textarea;

        public TextAreaEditor() {
            super(new JCheckBox());
            scrollpane = new JScrollPane();
            textarea = new JTextArea();
            textarea.setLineWrap(true);
            textarea.setWrapStyleWord(true);
            //textarea.setBorder(new TitledBorder("This is a JTextArea"));
            scrollpane.getViewport().add(textarea);
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            textarea.setText((String) value);

            return scrollpane;
        }

        public Object getCellEditorValue() {
            return textarea.getText();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnVolver = new botones.BotonVolver();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtComentarios = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        dpFecha = new com.github.lgooddatepicker.components.DatePicker();
        cbFinalizado = new javax.swing.JCheckBox();
        btnGuardar = new botones.BotonNuevo();
        botonEliminar1 = new botones.BotonEliminar();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHistoria = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(650, 807));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel3.setText("Historia de tratamiento");
        jLabel3.setFont(new java.awt.Font("Arial", 3, 48)); // NOI18N
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(jLabel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(btnVolver, gridBagConstraints);

        jLabel10.setText("Comentarios:");
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel10, gridBagConstraints);

        txtComentarios.setColumns(20);
        txtComentarios.setRows(5);
        jScrollPane1.setViewportView(txtComentarios);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jLabel13.setText("Fecha");
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel13, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(dpFecha, gridBagConstraints);

        cbFinalizado.setText(" Plan de tratamiento Finalizado?");
        cbFinalizado.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(cbFinalizado, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(btnGuardar, gridBagConstraints);

        botonEliminar1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        jPanel1.add(botonEliminar1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        tblHistoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tblHistoria.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        tblHistoria.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tblHistoria);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel12.add(jScrollPane2, gridBagConstraints);

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
    private botones.BotonEliminar botonEliminar1;
    private botones.BotonNuevo btnGuardar;
    private botones.BotonVolver btnVolver;
    private javax.swing.JCheckBox cbFinalizado;
    private com.github.lgooddatepicker.components.DatePicker dpFecha;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblHistoria;
    public javax.swing.JTextArea txtComentarios;
    // End of variables declaration//GEN-END:variables

}
