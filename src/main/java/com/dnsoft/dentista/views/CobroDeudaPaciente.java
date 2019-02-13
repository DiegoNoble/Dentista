package com.dnsoft.dentista.views;

import com.dnsoft.dentista.beans.Caja;
import com.dnsoft.dentista.beans.CuentaPaciente;
import com.dnsoft.dentista.beans.MonedaEnum;
import com.dnsoft.dentista.beans.Paciente;
import com.dnsoft.dentista.beans.Rubro;
import com.dnsoft.dentista.daos.ICajaDAO;
import com.dnsoft.dentista.daos.ICuentaPacienteDAO;
import com.dnsoft.dentista.utiles.ActualizaSaldos;
import com.dnsoft.dentista.utiles.Container;
import com.dnsoft.dentista.utiles.ControlarEntradaTexto;
import com.dnsoft.dentista.utiles.OptionPaneEstandar;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego Noble
 */
public final class CobroDeudaPaciente extends javax.swing.JDialog {

    BigDecimal importePago;
    Rubro rubro;
    Paciente Paciente;
    MonedaEnum moneda;
    ICuentaPacienteDAO cCPacienteDAO;
    ICajaDAO cajaDAO;
    Container container;
    Caja movimientoCaja;

    public CobroDeudaPaciente(java.awt.Frame parent, boolean modal, Rubro rubro, Paciente Paciente, MonedaEnum moneda) {
        super(parent, modal);
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/diente.jpg")));
        //CIERRA JOPTIONPANE CON ESCAPE
        jPanel1.grabFocus();
        jPanel1.addKeyListener(new OptionPaneEstandar(this));

        this.container = Container.getInstancia();
        this.Paciente = Paciente;
        this.moneda = moneda;
        this.rubro = rubro;

        inicio();
    }

    void inicio() {
        setLocationRelativeTo(null);
        dpFecha.setDate(LocalDate.now());
        cCPacienteDAO = container.getBean(ICuentaPacienteDAO.class);
        cajaDAO = container.getBean(ICajaDAO.class);
        Character chs[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', '-'};
        txtImporte.setDocument(new ControlarEntradaTexto(10, chs));
        cbMoneda.addItem(moneda);
        accionesBotones();

    }

    void accionesBotones() {
        btnPagar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (!txtDescripcion.getText().equals("")) {
                    VerificaImporte();
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un detalle del pago", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                dispose();
            }
        });

    }

    void VerificaImporte() {
        try {
            importePago = new BigDecimal(txtImporte.getText()).setScale(2, RoundingMode.CEILING);
            if (importePago.doubleValue() > 0.00) {
                //Si el retiro es mayor al saldo, solicita confirmacion
                int resp = JOptionPane.showConfirmDialog(this, "Confirma el importe ingresado ? " + moneda + " " + importePago, "Atención!", JOptionPane.YES_NO_OPTION);
                if (resp != -1) {
                    registraPago();
                    JOptionPane.showMessageDialog(null, "Se registro el pago correctamente", "Correcto", JOptionPane.INFORMATION_MESSAGE);
                    //new ImprimeRecibo(movimientoCaja, Paciente.toString(), txtDescripcion.getText(), "", "").imprimieReciboEntrada();
                }
            } else {
                JOptionPane.showConfirmDialog(this, "El importe debe ser mayor que 0", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar registros: " + e);

        }
    }

    private void movimientoCaja() {
        movimientoCaja = new Caja();
        movimientoCaja.setDescripcion("Cobro deuda Paciente " + Paciente + ", Descripción: " + txtDescripcion.getText());
        movimientoCaja.setEntrada(importePago);
        movimientoCaja.setFecha(LocalDate.now());
        movimientoCaja.setMoneda(moneda);
        movimientoCaja.setRubro(rubro);
        movimientoCaja.setObs(txtDescripcion.getText());
        movimientoCaja.setSalida(BigDecimal.ZERO);
        cajaDAO.save(movimientoCaja);
        this.dispose();
    }

    private void registraPago() {

        CuentaPaciente pago = new CuentaPaciente();
        pago.setPaciente(Paciente);
        pago.setDebe(BigDecimal.ZERO);
        pago.setHaber(importePago);
        pago.setObservacion(txtDescripcion.getText());
        pago.setFechaMovimiento(dpFecha.getDate());
        pago.setMoneda(moneda);
        
        cCPacienteDAO.save(pago);

        //Ajusta saldo cc Paciente
        ActualizaSaldos acSaldo = new ActualizaSaldos();
        List<CuentaPaciente> ccPaciente = cCPacienteDAO.findByPacienteAndMonedaOrderFechaMovimientoAsc(Paciente, moneda);
        cCPacienteDAO.save(acSaldo.ActualizaSaldosPacientes(ccPaciente));
        movimientoCaja();
    }


   

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtImporte = new javax.swing.JTextField();
        btnPagar = new botones.BotonPagar();
        btnVolver = new botones.BotonVolver();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbMoneda = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        dpFecha = new com.github.lgooddatepicker.components.DatePicker();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel3.setFont(new java.awt.Font("Arial", 3, 48)); // NOI18N
        jLabel3.setText("Pago cuenta paciente");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(jLabel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        txtImporte.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(txtImporte, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(btnPagar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(btnVolver, gridBagConstraints);

        jLabel12.setText("Importe");
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel1.add(jLabel12, gridBagConstraints);

        jLabel10.setText("Concepto:");
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel10, gridBagConstraints);

        cbMoneda.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(cbMoneda, gridBagConstraints);

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jLabel13.setText("Fecha pago");
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel13, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(dpFecha, gridBagConstraints);

        jLabel14.setText("Moneda");
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel14, gridBagConstraints);

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
    private botones.BotonPagar btnPagar;
    private botones.BotonVolver btnVolver;
    public javax.swing.JComboBox cbMoneda;
    private com.github.lgooddatepicker.components.DatePicker dpFecha;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtImporte;
    // End of variables declaration//GEN-END:variables

}
