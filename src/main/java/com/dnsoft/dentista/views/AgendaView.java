/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.views;

import com.dnsoft.dentista.beans.Consulta;
import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.beans.Parametros;
import com.dnsoft.dentista.beans.SituacionConsultaEnum;
import com.dnsoft.dentista.daos.IConsultaDAO;
import com.dnsoft.dentista.daos.IPacienteDAO;
import com.dnsoft.dentista.daos.IParametrosDAO;
import com.dnsoft.dentista.renderers.LocalDateCellRenderer;
import com.dnsoft.dentista.renderers.TabelaTextAreaRenderer;
import com.dnsoft.dentista.renderers.TableRendererColorAgenda;
import com.dnsoft.dentista.tablemodels.AgendaTableModel;
import com.dnsoft.dentista.utiles.AgendaGoogleCalendar;
import com.dnsoft.dentista.utiles.Container;
import com.dnsoft.dentista.utiles.InternalFrameEstandar;
import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Diego
 */
public class AgendaView extends InternalFrameEstandar {

    Container container;
    IPacienteDAO daoPaciente;
    IConsultaDAO daoConsula;
    IParametrosDAO parametrosDAO;
    Parametros parametros;
    DatePickerSettings dateSettings = new DatePickerSettings();
    List<Consulta> list = new ArrayList<Consulta>();
    Consulta consultaSeleccionada;
    ListSelectionModel listModel;
    String dirWeb = "www.google.com";
    int puerto = 80;
    AgendaTableModel tableModel;
    ArrayList<LocalTime> potentialMenuTimes;

    public AgendaView() {
        dateSettings.setAllowEmptyDates(false);
        int newHeight = (int) (dateSettings.getSizeDatePanelMinimumHeight() * 1.4);
        int newWidth = (int) (dateSettings.getSizeDatePanelMinimumWidth() * 1.4);
        dateSettings.setSizeDatePanelMinimumHeight(newHeight);
        dateSettings.setSizeDatePanelMinimumWidth(newWidth);

        initComponents();
        inicio();

    }

    public AgendaView(Paciente paciente) {
        dateSettings.setAllowEmptyDates(false);
        int newHeight = (int) (dateSettings.getSizeDatePanelMinimumHeight() * 1.4);
        int newWidth = (int) (dateSettings.getSizeDatePanelMinimumWidth() * 1.4);
        dateSettings.setSizeDatePanelMinimumHeight(newHeight);
        dateSettings.setSizeDatePanelMinimumWidth(newWidth);

        initComponents();
        inicio();
        Consulta buscarProximaConsulta = daoConsula.buscarProximaConsulta(paciente);
        cpFecha.setSelectedDate(buscarProximaConsulta.getFechaConsulta());

    }

    private void inicio() {
        /*URL dateImageURL = FullDemo.class.getResource("/imagenes/datepickerbutton.png");
        Image dateExampleImage = Toolkit.getDefaultToolkit().getImage(dateImageURL);
        ImageIcon dateExampleIcon = new ImageIcon(dateExampleImage);
        JButton datePickerButton = cpFecha.getComponentToggleCalendarButton();
        datePickerButton.setText("");
        datePickerButton.setIcon(dateExampleIcon);*/

        this.container = Container.getInstancia();
        this.daoPaciente = container.getBean(IPacienteDAO.class);
        this.daoConsula = container.getBean(IConsultaDAO.class);
        this.parametrosDAO = container.getBean(IParametrosDAO.class);
        parametros = parametrosDAO.findOne(new Long(1));
        tblModel();
        buscaConsultasDiaSeleccionado();
        accionesBotones();

    }

    void tblModel() {

        ((DefaultTableCellRenderer) tbl.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        tableModel = new AgendaTableModel(list);
        tbl.setModel(tableModel);
        tbl.setRowHeight(50);
        tbl.getColumn("Paciente").setCellRenderer(new TabelaTextAreaRenderer());
        tbl.getColumn("Fecha").setCellRenderer(new LocalDateCellRenderer());
        tbl.getColumn("Situación").setCellRenderer(new TableRendererColorAgenda(3));

        int[] anchos = {40, 50, 300, 20};

        for (int i = 0; i < tbl.getColumnCount(); i++) {

            tbl.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }

        listModel = tbl.getSelectionModel();
        listModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tbl.getSelectedRow() != -1) {
                    consultaSeleccionada = list.get(tbl.getSelectedRow());
                    btnEliminar.setEnabled(true);
                    btnModificar.setEnabled(true);

                } else {
                    consultaSeleccionada = null;
                    btnEliminar.setEnabled(false);

                    btnModificar.setEnabled(false);

                }
            }
        });

    }

    void buscaConsultasDiaSeleccionado() {
        list.clear();
        LocalDate localDate = cpFecha.getSelectedDate();

        list.addAll(daoConsula.findByFechaConsultaOrderByHoraConsultaDesdeAsc(localDate));
        tableModel.fireTableDataChanged();
    }

    void accionesBotones() {
        cpFecha.addCalendarListener(new CalendarListener() {
            @Override
            public void selectedDateChanged(CalendarSelectionEvent event) {
                buscaConsultasDiaSeleccionado();
            }

            @Override
            public void yearMonthChanged(YearMonthChangeEvent event) {
                buscaConsultasDiaSeleccionado();
            }
        });

        btnNuevo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {

                AgendaDetalles agendaDetalles = new AgendaDetalles(null, true, cpFecha.getSelectedDate());
                agendaDetalles.setVisible(true);
                agendaDetalles.toFront();

                buscaConsultasDiaSeleccionado();
            }
        });

        btnModificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                AgendaDetalles agendaDetalles = new AgendaDetalles(null, true, cpFecha.getSelectedDate(), consultaSeleccionada);
                agendaDetalles.setVisible(true);
                agendaDetalles.toFront();

                buscaConsultasDiaSeleccionado();

            }
        });

        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {

                try {
                    int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Desea eliminar la consulta seleccionada?", "Atención!", JOptionPane.YES_NO_OPTION);
                    if (showConfirmDialog == JOptionPane.YES_OPTION) {
                        if (parametros.getActivaGoogleCalendar() == 1) {
                            Socket s = new Socket(dirWeb, puerto);
                            if (s.isConnected()) {
                                System.out.println("Hay conexión a internet!");
                                new AgendaGoogleCalendar().eliminarEvento(consultaSeleccionada);
                            }
                        }
                    }
                } catch (UnknownHostException e) {
                    System.err.println("Sin conexión a internet!");
                    // e.printStackTrace();

                    //JOptionPane.showMessageDialog(null, "Eliminado correctamente!", "Transacción correcta", JOptionPane.INFORMATION_MESSAGE);
                } catch (GeneralSecurityException ex) {
                    Logger.getLogger(AgendaView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(AgendaView.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    consultaSeleccionada.setSituacionConsulta(SituacionConsultaEnum.CONSULTA_CANCELADA);
                    daoConsula.save(consultaSeleccionada);
                    buscaConsultasDiaSeleccionado();
                }
            }
        });
    }

    /*public static void main(String[] args) {
        AgendaView agenda = new AgendaView();
        agenda.setVisible(true);
    }
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnNuevo = new botones.BotonNuevo();
        btnModificar = new botones.BotonEdicion();
        btnEliminar = new botones.BotonEliminar();
        jPanel1 = new javax.swing.JPanel();
        cpFecha = cpFecha = new CalendarPanel(dateSettings);

        setClosable(true);
        setResizable(true);
        setPreferredSize(new java.awt.Dimension(1200, 500));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.GridBagLayout());

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel3.setText("Agenda consultorio");
        jLabel3.setFont(new java.awt.Font("Arial", 3, 48)); // NOI18N
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(jLabel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnNuevo.setText("Agendar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel4.add(btnNuevo, gridBagConstraints);

        btnModificar.setEnabled(false);
        btnModificar.setText("Editar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(btnModificar, gridBagConstraints);

        btnEliminar.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel4.add(btnEliminar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(jPanel4, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(cpFecha, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel5.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel5, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public botones.BotonEliminar btnEliminar;
    public botones.BotonEdicion btnModificar;
    public botones.BotonNuevo btnNuevo;
    private com.github.lgooddatepicker.components.CalendarPanel cpFecha;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tbl;
    // End of variables declaration//GEN-END:variables

}
