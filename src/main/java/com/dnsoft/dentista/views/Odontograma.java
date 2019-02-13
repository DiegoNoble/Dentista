/*º
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Diego
 */
public class Odontograma extends javax.swing.JFrame {

    /**
     * Creates new form Odontograma
     */
    public Odontograma() {
        initComponents();
        btn18.addActionListener(new BtnListener());
        btn17.addActionListener(new BtnListener());
        btn16.addActionListener(new BtnListener());
        btn15.addActionListener(new BtnListener());
        btn14.addActionListener(new BtnListener());
        btn13.addActionListener(new BtnListener());
        btn12.addActionListener(new BtnListener());
        btn11.addActionListener(new BtnListener());

        btn21.addActionListener(new BtnListener());
        btn22.addActionListener(new BtnListener());
        btn23.addActionListener(new BtnListener());
        btn24.addActionListener(new BtnListener());
        btn25.addActionListener(new BtnListener());
        btn26.addActionListener(new BtnListener());
        btn27.addActionListener(new BtnListener());
        btn28.addActionListener(new BtnListener());

        btn41.addActionListener(new BtnListener());
        btn42.addActionListener(new BtnListener());
        btn43.addActionListener(new BtnListener());
        btn44.addActionListener(new BtnListener());
        btn45.addActionListener(new BtnListener());
        btn46.addActionListener(new BtnListener());
        btn47.addActionListener(new BtnListener());
        btn48.addActionListener(new BtnListener());

        btn31.addActionListener(new BtnListener());
        btn32.addActionListener(new BtnListener());
        btn33.addActionListener(new BtnListener());
        btn34.addActionListener(new BtnListener());
        btn35.addActionListener(new BtnListener());
        btn36.addActionListener(new BtnListener());
        btn37.addActionListener(new BtnListener());
        btn38.addActionListener(new BtnListener());

        btn11Distal.addActionListener(new BtnCaraDienteListener());
        btn11Mesial.addActionListener(new BtnCaraDienteListener());
        btn11Oclusal.addActionListener(new BtnCaraDienteListener());
        btn11Palatino.addActionListener(new BtnCaraDienteListener());
        btn11Vestib.addActionListener(new BtnCaraDienteListener());

        btn12Distal.addActionListener(new BtnCaraDienteListener());
        btn12Mesial.addActionListener(new BtnCaraDienteListener());
        btn12Oclusal.addActionListener(new BtnCaraDienteListener());
        btn12Palatino.addActionListener(new BtnCaraDienteListener());
        btn12Vestib.addActionListener(new BtnCaraDienteListener());

        btn13Distal.addActionListener(new BtnCaraDienteListener());
        btn13Mesial.addActionListener(new BtnCaraDienteListener());
        btn13Oclusal.addActionListener(new BtnCaraDienteListener());
        btn13Palatino.addActionListener(new BtnCaraDienteListener());
        btn13Vestib.addActionListener(new BtnCaraDienteListener());

        btn14Distal.addActionListener(new BtnCaraDienteListener());
        btn14Mesial.addActionListener(new BtnCaraDienteListener());
        btn14Oclusal.addActionListener(new BtnCaraDienteListener());
        btn14Palatino.addActionListener(new BtnCaraDienteListener());
        btn14Vestib.addActionListener(new BtnCaraDienteListener());

        btn15Distal.addActionListener(new BtnCaraDienteListener());
        btn15Mesial.addActionListener(new BtnCaraDienteListener());
        btn15Oclusal.addActionListener(new BtnCaraDienteListener());
        btn15Palatino.addActionListener(new BtnCaraDienteListener());
        btn15Vestib.addActionListener(new BtnCaraDienteListener());

        btn16Distal.addActionListener(new BtnCaraDienteListener());
        btn16Mesial.addActionListener(new BtnCaraDienteListener());
        btn16Oclusal.addActionListener(new BtnCaraDienteListener());
        btn16Palatino.addActionListener(new BtnCaraDienteListener());
        btn16Vestib.addActionListener(new BtnCaraDienteListener());

        btn17Distal.addActionListener(new BtnCaraDienteListener());
        btn17Mesial.addActionListener(new BtnCaraDienteListener());
        btn17Oclusal.addActionListener(new BtnCaraDienteListener());
        btn17Palatino.addActionListener(new BtnCaraDienteListener());
        btn17Vestib.addActionListener(new BtnCaraDienteListener());

        btn18Distal.addActionListener(new BtnCaraDienteListener());
        btn18Mesial.addActionListener(new BtnCaraDienteListener());
        btn18Oclusal.addActionListener(new BtnCaraDienteListener());
        btn18Palatino.addActionListener(new BtnCaraDienteListener());
        btn18Vestib.addActionListener(new BtnCaraDienteListener());

        btn21Distal.addActionListener(new BtnCaraDienteListener());
        btn21Mesial.addActionListener(new BtnCaraDienteListener());
        btn21Oclusal.addActionListener(new BtnCaraDienteListener());
        btn21Palatino.addActionListener(new BtnCaraDienteListener());
        btn21Vestib.addActionListener(new BtnCaraDienteListener());

        btn22Distal.addActionListener(new BtnCaraDienteListener());
        btn22Mesial.addActionListener(new BtnCaraDienteListener());
        btn22Oclusal.addActionListener(new BtnCaraDienteListener());
        btn22Palatino.addActionListener(new BtnCaraDienteListener());
        btn22Vestib.addActionListener(new BtnCaraDienteListener());

        btn23Distal.addActionListener(new BtnCaraDienteListener());
        btn23Mesial.addActionListener(new BtnCaraDienteListener());
        btn23Oclusal.addActionListener(new BtnCaraDienteListener());
        btn23Palatino.addActionListener(new BtnCaraDienteListener());
        btn23Vestib.addActionListener(new BtnCaraDienteListener());

        btn24Distal.addActionListener(new BtnCaraDienteListener());
        btn24Mesial.addActionListener(new BtnCaraDienteListener());
        btn24Oclusal.addActionListener(new BtnCaraDienteListener());
        btn24Palatino.addActionListener(new BtnCaraDienteListener());
        btn24Vestib.addActionListener(new BtnCaraDienteListener());

        btn25Distal.addActionListener(new BtnCaraDienteListener());
        btn25Mesial.addActionListener(new BtnCaraDienteListener());
        btn25Oclusal.addActionListener(new BtnCaraDienteListener());
        btn25Palatino.addActionListener(new BtnCaraDienteListener());
        btn25Vestib.addActionListener(new BtnCaraDienteListener());

        btn26Distal.addActionListener(new BtnCaraDienteListener());
        btn26Mesial.addActionListener(new BtnCaraDienteListener());
        btn26Oclusal.addActionListener(new BtnCaraDienteListener());
        btn26Palatino.addActionListener(new BtnCaraDienteListener());
        btn26Vestib.addActionListener(new BtnCaraDienteListener());

        btn27Distal.addActionListener(new BtnCaraDienteListener());
        btn27Mesial.addActionListener(new BtnCaraDienteListener());
        btn27Oclusal.addActionListener(new BtnCaraDienteListener());
        btn27Palatino.addActionListener(new BtnCaraDienteListener());
        btn27Vestib.addActionListener(new BtnCaraDienteListener());

        btn28Distal.addActionListener(new BtnCaraDienteListener());
        btn28Mesial.addActionListener(new BtnCaraDienteListener());
        btn28Oclusal.addActionListener(new BtnCaraDienteListener());
        btn28Palatino.addActionListener(new BtnCaraDienteListener());
        btn28Vestib.addActionListener(new BtnCaraDienteListener());
    }

    class BtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            if (Aparato.isSelected()) {
                boton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Aparato/" + boton.getName() + ".jpg")));
            }
            if (Implante.isSelected()) {
                boton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Implante/" + boton.getName() + ".jpg")));
            }
            if (Ausente.isSelected()) {
                boton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Ausente/" + boton.getName() + ".jpg")));
            }
            if (Limpiar.isSelected()) {
                boton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/" + boton.getName() + ".jpg")));
            }
            if (Fractura.isSelected()) {
                boton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Fractura/" + boton.getName() + ".jpg")));
            }
        }

    }

    class BtnCaraDienteListener implements ActionListener {

        JButton boton;

        @Override
        public void actionPerformed(ActionEvent e) {
            boton = (JButton) e.getSource();
            System.out.println(boton.getName());
            if (Carie.isSelected()) {
                boton.setBackground(Color.red);
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btn18 = new javax.swing.JButton();
        btn17 = new javax.swing.JButton();
        btn15 = new javax.swing.JButton();
        btn16 = new javax.swing.JButton();
        btn14 = new javax.swing.JButton();
        btn13 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        btn11 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btn18Distal = new javax.swing.JButton();
        btn18Mesial = new javax.swing.JButton();
        btn18Vestib = new javax.swing.JButton();
        btn18Palatino = new javax.swing.JButton();
        btn18Oclusal = new javax.swing.JButton();
        jPanel39 = new javax.swing.JPanel();
        btn15Distal = new javax.swing.JButton();
        btn15Mesial = new javax.swing.JButton();
        btn15Vestib = new javax.swing.JButton();
        btn15Palatino = new javax.swing.JButton();
        btn15Oclusal = new javax.swing.JButton();
        jPanel40 = new javax.swing.JPanel();
        btn16Distal = new javax.swing.JButton();
        btn16Mesial = new javax.swing.JButton();
        btn16Vestib = new javax.swing.JButton();
        btn16Palatino = new javax.swing.JButton();
        btn16Oclusal = new javax.swing.JButton();
        jPanel41 = new javax.swing.JPanel();
        btn14Distal = new javax.swing.JButton();
        btn14Mesial = new javax.swing.JButton();
        btn14Vestib = new javax.swing.JButton();
        btn14Palatino = new javax.swing.JButton();
        btn14Oclusal = new javax.swing.JButton();
        jPanel42 = new javax.swing.JPanel();
        btn13Distal = new javax.swing.JButton();
        btn13Mesial = new javax.swing.JButton();
        btn13Vestib = new javax.swing.JButton();
        btn13Palatino = new javax.swing.JButton();
        btn13Oclusal = new javax.swing.JButton();
        jPanel43 = new javax.swing.JPanel();
        btn12Distal = new javax.swing.JButton();
        btn12Mesial = new javax.swing.JButton();
        btn12Vestib = new javax.swing.JButton();
        btn12Palatino = new javax.swing.JButton();
        btn12Oclusal = new javax.swing.JButton();
        jPanel44 = new javax.swing.JPanel();
        btn11Distal = new javax.swing.JButton();
        btn11Mesial = new javax.swing.JButton();
        btn11Vestib = new javax.swing.JButton();
        btn11Palatino = new javax.swing.JButton();
        btn11Oclusal = new javax.swing.JButton();
        jPanel45 = new javax.swing.JPanel();
        btn17Distal = new javax.swing.JButton();
        btn17Mesial = new javax.swing.JButton();
        btn17Vestib = new javax.swing.JButton();
        btn17Palatino = new javax.swing.JButton();
        btn17Oclusal = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btn28 = new javax.swing.JButton();
        btn21 = new javax.swing.JButton();
        btn27 = new javax.swing.JButton();
        btn26 = new javax.swing.JButton();
        btn25 = new javax.swing.JButton();
        btn24 = new javax.swing.JButton();
        btn23 = new javax.swing.JButton();
        btn22 = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        btn21Mesial = new javax.swing.JButton();
        btn21Distal = new javax.swing.JButton();
        btn21Vestib = new javax.swing.JButton();
        btn21Palatino = new javax.swing.JButton();
        btn21Oclusal = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        btn28Mesial = new javax.swing.JButton();
        btn28Distal = new javax.swing.JButton();
        btn28Vestib = new javax.swing.JButton();
        btn28Palatino = new javax.swing.JButton();
        btn28Oclusal = new javax.swing.JButton();
        jPanel32 = new javax.swing.JPanel();
        btn27Mesial = new javax.swing.JButton();
        btn27Distal = new javax.swing.JButton();
        btn27Vestib = new javax.swing.JButton();
        btn27Palatino = new javax.swing.JButton();
        btn27Oclusal = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();
        btn26Mesial = new javax.swing.JButton();
        btn26Distal = new javax.swing.JButton();
        btn26Vestib = new javax.swing.JButton();
        btn26Palatino = new javax.swing.JButton();
        btn26Oclusal = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        btn25Mesial = new javax.swing.JButton();
        btn25Distal = new javax.swing.JButton();
        btn25Vestib = new javax.swing.JButton();
        btn25Palatino = new javax.swing.JButton();
        btn25Oclusal = new javax.swing.JButton();
        jPanel35 = new javax.swing.JPanel();
        btn24Mesial = new javax.swing.JButton();
        btn24Distal = new javax.swing.JButton();
        btn24Vestib = new javax.swing.JButton();
        btn24Palatino = new javax.swing.JButton();
        btn24Oclusal = new javax.swing.JButton();
        jPanel36 = new javax.swing.JPanel();
        btn23Mesial = new javax.swing.JButton();
        btn23Distal = new javax.swing.JButton();
        btn23Vestib = new javax.swing.JButton();
        btn23Palatino = new javax.swing.JButton();
        btn23Oclusal = new javax.swing.JButton();
        jPanel37 = new javax.swing.JPanel();
        btn22Mesial = new javax.swing.JButton();
        btn22Distal = new javax.swing.JButton();
        btn22Vestib = new javax.swing.JButton();
        btn22Palatino = new javax.swing.JButton();
        btn22Oclusal = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btn48 = new javax.swing.JButton();
        btn47 = new javax.swing.JButton();
        btn46 = new javax.swing.JButton();
        btn45 = new javax.swing.JButton();
        btn44 = new javax.swing.JButton();
        btn43 = new javax.swing.JButton();
        btn42 = new javax.swing.JButton();
        btn41 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        btn48Distal = new javax.swing.JButton();
        btn48Mesial = new javax.swing.JButton();
        btn48Lingual = new javax.swing.JButton();
        btn48Vestib = new javax.swing.JButton();
        btn48Oclusal = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        btn46Distal = new javax.swing.JButton();
        btn46Mesial = new javax.swing.JButton();
        btn46Lingual = new javax.swing.JButton();
        btn46Vestib = new javax.swing.JButton();
        btn46Oclusal = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        btn45Distal = new javax.swing.JButton();
        btn45Mesial = new javax.swing.JButton();
        btn45Lingual = new javax.swing.JButton();
        btn45Vestib = new javax.swing.JButton();
        btn45Oclusal = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        btn44Distal = new javax.swing.JButton();
        btn44Mesial = new javax.swing.JButton();
        btn44Lingual = new javax.swing.JButton();
        btn44Vestib = new javax.swing.JButton();
        btn44Oclusal = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        btn43Distal = new javax.swing.JButton();
        btn43Mesial = new javax.swing.JButton();
        btn43Lingual = new javax.swing.JButton();
        btn43Vestib = new javax.swing.JButton();
        btn43Oclusal = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        btn42Distal = new javax.swing.JButton();
        btn42Mesial = new javax.swing.JButton();
        btn42Lingual = new javax.swing.JButton();
        btn42Vestib = new javax.swing.JButton();
        btn42Oclusal = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        btn41Distal = new javax.swing.JButton();
        btn41Mesial = new javax.swing.JButton();
        btn41Lingual = new javax.swing.JButton();
        btn41Vestib = new javax.swing.JButton();
        btn41Oclusal = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        btn47Distal = new javax.swing.JButton();
        btn47Mesial = new javax.swing.JButton();
        btn47Lingual = new javax.swing.JButton();
        btn47Vestib = new javax.swing.JButton();
        btn47Oclusal = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btn31 = new javax.swing.JButton();
        btn32 = new javax.swing.JButton();
        btn33 = new javax.swing.JButton();
        btn34 = new javax.swing.JButton();
        btn35 = new javax.swing.JButton();
        btn36 = new javax.swing.JButton();
        btn37 = new javax.swing.JButton();
        btn38 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        btn31Mesial = new javax.swing.JButton();
        btn31Distal = new javax.swing.JButton();
        btn31Lingual = new javax.swing.JButton();
        btn31Vestib = new javax.swing.JButton();
        btn31Oclusal = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        btn38Mesial = new javax.swing.JButton();
        btn38Distal = new javax.swing.JButton();
        btn38Lingual = new javax.swing.JButton();
        btn38Vestib = new javax.swing.JButton();
        btn38Oclusal = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        btn37Mesial = new javax.swing.JButton();
        btn37Distal = new javax.swing.JButton();
        btn37Lingual = new javax.swing.JButton();
        btn37Vestib = new javax.swing.JButton();
        btn37Oclusal = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        btn36Mesial = new javax.swing.JButton();
        btn36Distal = new javax.swing.JButton();
        btn36Lingual = new javax.swing.JButton();
        btn36Vestib = new javax.swing.JButton();
        btn36Oclusal = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        btn35Mesial = new javax.swing.JButton();
        btn35Distal = new javax.swing.JButton();
        btn35Lingual = new javax.swing.JButton();
        btn35Vestib = new javax.swing.JButton();
        btn35Oclusal = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        btn34Mesial = new javax.swing.JButton();
        btn34Distal = new javax.swing.JButton();
        btn34Lingual = new javax.swing.JButton();
        btn34Vestib = new javax.swing.JButton();
        btn34Oclusal = new javax.swing.JButton();
        jPanel28 = new javax.swing.JPanel();
        btn33Mesial = new javax.swing.JButton();
        btn33Distal = new javax.swing.JButton();
        btn33Lingual = new javax.swing.JButton();
        btn33Vestib = new javax.swing.JButton();
        btn33Oclusal = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        btn32Mesial = new javax.swing.JButton();
        btn32Distal = new javax.swing.JButton();
        btn32Lingual = new javax.swing.JButton();
        btn32Vestib = new javax.swing.JButton();
        btn32Oclusal = new javax.swing.JButton();
        jPanel38 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        Limpiar = new javax.swing.JToggleButton();
        Carie = new javax.swing.JToggleButton();
        Aparato = new javax.swing.JToggleButton();
        Implante = new javax.swing.JToggleButton();
        Fractura = new javax.swing.JToggleButton();
        Ausente = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        btn18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/18.jpg"))); // NOI18N
        btn18.setIconTextGap(-3);
        btn18.setName("18"); // NOI18N
        btn18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn18ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(btn18, gridBagConstraints);

        btn17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/17.jpg"))); // NOI18N
        btn17.setName("17"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(btn17, gridBagConstraints);

        btn15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/16.jpg"))); // NOI18N
        btn15.setName("16"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(btn15, gridBagConstraints);

        btn16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/15.jpg"))); // NOI18N
        btn16.setName("15"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(btn16, gridBagConstraints);

        btn14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/14.jpg"))); // NOI18N
        btn14.setName("14"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(btn14, gridBagConstraints);

        btn13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/13.jpg"))); // NOI18N
        btn13.setName("13"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(btn13, gridBagConstraints);

        btn12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/12.jpg"))); // NOI18N
        btn12.setName("12"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(btn12, gridBagConstraints);

        btn11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/11.jpg"))); // NOI18N
        btn11.setName("11"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(btn11, gridBagConstraints);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        btn18Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn18Distal.setName("Distal18"); // NOI18N
        btn18Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(btn18Distal, gridBagConstraints);

        btn18Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn18Mesial.setName("Mesial18"); // NOI18N
        btn18Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(btn18Mesial, gridBagConstraints);

        btn18Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn18Vestib.setName("Vestib18"); // NOI18N
        btn18Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(btn18Vestib, gridBagConstraints);

        btn18Palatino.setForeground(new java.awt.Color(51, 51, 51));
        btn18Palatino.setName("Palatino18"); // NOI18N
        btn18Palatino.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(btn18Palatino, gridBagConstraints);

        btn18Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn18Oclusal.setName("Oculusal18"); // NOI18N
        btn18Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(btn18Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jPanel6, gridBagConstraints);

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));
        jPanel39.setLayout(new java.awt.GridBagLayout());

        btn15Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn15Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel39.add(btn15Distal, gridBagConstraints);

        btn15Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn15Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel39.add(btn15Mesial, gridBagConstraints);

        btn15Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn15Vestib.setName(""); // NOI18N
        btn15Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel39.add(btn15Vestib, gridBagConstraints);

        btn15Palatino.setForeground(new java.awt.Color(51, 51, 51));
        btn15Palatino.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel39.add(btn15Palatino, gridBagConstraints);

        btn15Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn15Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel39.add(btn15Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jPanel39, gridBagConstraints);

        jPanel40.setBackground(new java.awt.Color(255, 255, 255));
        jPanel40.setLayout(new java.awt.GridBagLayout());

        btn16Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn16Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel40.add(btn16Distal, gridBagConstraints);

        btn16Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn16Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel40.add(btn16Mesial, gridBagConstraints);

        btn16Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn16Vestib.setName(""); // NOI18N
        btn16Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel40.add(btn16Vestib, gridBagConstraints);

        btn16Palatino.setForeground(new java.awt.Color(51, 51, 51));
        btn16Palatino.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel40.add(btn16Palatino, gridBagConstraints);

        btn16Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn16Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel40.add(btn16Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jPanel40, gridBagConstraints);

        jPanel41.setBackground(new java.awt.Color(255, 255, 255));
        jPanel41.setLayout(new java.awt.GridBagLayout());

        btn14Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn14Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel41.add(btn14Distal, gridBagConstraints);

        btn14Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn14Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel41.add(btn14Mesial, gridBagConstraints);

        btn14Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn14Vestib.setName(""); // NOI18N
        btn14Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel41.add(btn14Vestib, gridBagConstraints);

        btn14Palatino.setForeground(new java.awt.Color(51, 51, 51));
        btn14Palatino.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel41.add(btn14Palatino, gridBagConstraints);

        btn14Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn14Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel41.add(btn14Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jPanel41, gridBagConstraints);

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));
        jPanel42.setLayout(new java.awt.GridBagLayout());

        btn13Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn13Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel42.add(btn13Distal, gridBagConstraints);

        btn13Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn13Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel42.add(btn13Mesial, gridBagConstraints);

        btn13Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn13Vestib.setName(""); // NOI18N
        btn13Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel42.add(btn13Vestib, gridBagConstraints);

        btn13Palatino.setForeground(new java.awt.Color(51, 51, 51));
        btn13Palatino.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel42.add(btn13Palatino, gridBagConstraints);

        btn13Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn13Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel42.add(btn13Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jPanel42, gridBagConstraints);

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setLayout(new java.awt.GridBagLayout());

        btn12Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn12Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel43.add(btn12Distal, gridBagConstraints);

        btn12Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn12Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel43.add(btn12Mesial, gridBagConstraints);

        btn12Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn12Vestib.setName(""); // NOI18N
        btn12Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel43.add(btn12Vestib, gridBagConstraints);

        btn12Palatino.setForeground(new java.awt.Color(51, 51, 51));
        btn12Palatino.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel43.add(btn12Palatino, gridBagConstraints);

        btn12Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn12Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel43.add(btn12Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jPanel43, gridBagConstraints);

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));
        jPanel44.setLayout(new java.awt.GridBagLayout());

        btn11Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn11Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel44.add(btn11Distal, gridBagConstraints);

        btn11Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn11Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel44.add(btn11Mesial, gridBagConstraints);

        btn11Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn11Vestib.setName(""); // NOI18N
        btn11Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel44.add(btn11Vestib, gridBagConstraints);

        btn11Palatino.setForeground(new java.awt.Color(51, 51, 51));
        btn11Palatino.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel44.add(btn11Palatino, gridBagConstraints);

        btn11Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn11Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel44.add(btn11Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jPanel44, gridBagConstraints);

        jPanel45.setBackground(new java.awt.Color(255, 255, 255));
        jPanel45.setLayout(new java.awt.GridBagLayout());

        btn17Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn17Distal.setName("17Distal"); // NOI18N
        btn17Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel45.add(btn17Distal, gridBagConstraints);

        btn17Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn17Mesial.setName("17Mesial"); // NOI18N
        btn17Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel45.add(btn17Mesial, gridBagConstraints);

        btn17Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn17Vestib.setName("17Vestib"); // NOI18N
        btn17Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel45.add(btn17Vestib, gridBagConstraints);

        btn17Palatino.setForeground(new java.awt.Color(51, 51, 51));
        btn17Palatino.setName("17Palatino"); // NOI18N
        btn17Palatino.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel45.add(btn17Palatino, gridBagConstraints);

        btn17Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn17Oclusal.setName("17Oclusal"); // NOI18N
        btn17Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel45.add(btn17Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jPanel45, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        btn28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/28.jpg"))); // NOI18N
        btn28.setFocusPainted(false);
        btn28.setName("28"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel4.add(btn28, gridBagConstraints);

        btn21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/21.jpg"))); // NOI18N
        btn21.setName("21"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel4.add(btn21, gridBagConstraints);

        btn27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/27.jpg"))); // NOI18N
        btn27.setName("27"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel4.add(btn27, gridBagConstraints);

        btn26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/26.jpg"))); // NOI18N
        btn26.setName("26"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel4.add(btn26, gridBagConstraints);

        btn25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/25.jpg"))); // NOI18N
        btn25.setName("25"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel4.add(btn25, gridBagConstraints);

        btn24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/24.jpg"))); // NOI18N
        btn24.setName("24"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel4.add(btn24, gridBagConstraints);

        btn23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/23.jpg"))); // NOI18N
        btn23.setName("23"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel4.add(btn23, gridBagConstraints);

        btn22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/22.jpg"))); // NOI18N
        btn22.setName("22"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel4.add(btn22, gridBagConstraints);

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setLayout(new java.awt.GridBagLayout());

        btn21Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn21Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel30.add(btn21Mesial, gridBagConstraints);

        btn21Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn21Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel30.add(btn21Distal, gridBagConstraints);

        btn21Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn21Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel30.add(btn21Vestib, gridBagConstraints);

        btn21Palatino.setForeground(new java.awt.Color(51, 51, 51));
        btn21Palatino.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel30.add(btn21Palatino, gridBagConstraints);

        btn21Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn21Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel30.add(btn21Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jPanel30, gridBagConstraints);

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setLayout(new java.awt.GridBagLayout());

        btn28Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn28Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel31.add(btn28Mesial, gridBagConstraints);

        btn28Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn28Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel31.add(btn28Distal, gridBagConstraints);

        btn28Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn28Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel31.add(btn28Vestib, gridBagConstraints);

        btn28Palatino.setForeground(new java.awt.Color(51, 51, 51));
        btn28Palatino.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel31.add(btn28Palatino, gridBagConstraints);

        btn28Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn28Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel31.add(btn28Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jPanel31, gridBagConstraints);

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setLayout(new java.awt.GridBagLayout());

        btn27Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn27Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel32.add(btn27Mesial, gridBagConstraints);

        btn27Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn27Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel32.add(btn27Distal, gridBagConstraints);

        btn27Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn27Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel32.add(btn27Vestib, gridBagConstraints);

        btn27Palatino.setForeground(new java.awt.Color(51, 51, 51));
        btn27Palatino.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel32.add(btn27Palatino, gridBagConstraints);

        btn27Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn27Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel32.add(btn27Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jPanel32, gridBagConstraints);

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setLayout(new java.awt.GridBagLayout());

        btn26Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn26Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel33.add(btn26Mesial, gridBagConstraints);

        btn26Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn26Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel33.add(btn26Distal, gridBagConstraints);

        btn26Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn26Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel33.add(btn26Vestib, gridBagConstraints);

        btn26Palatino.setForeground(new java.awt.Color(51, 51, 51));
        btn26Palatino.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel33.add(btn26Palatino, gridBagConstraints);

        btn26Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn26Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel33.add(btn26Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jPanel33, gridBagConstraints);

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setLayout(new java.awt.GridBagLayout());

        btn25Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn25Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel34.add(btn25Mesial, gridBagConstraints);

        btn25Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn25Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel34.add(btn25Distal, gridBagConstraints);

        btn25Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn25Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel34.add(btn25Vestib, gridBagConstraints);

        btn25Palatino.setForeground(new java.awt.Color(51, 51, 51));
        btn25Palatino.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel34.add(btn25Palatino, gridBagConstraints);

        btn25Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn25Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel34.add(btn25Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jPanel34, gridBagConstraints);

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setLayout(new java.awt.GridBagLayout());

        btn24Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn24Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel35.add(btn24Mesial, gridBagConstraints);

        btn24Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn24Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel35.add(btn24Distal, gridBagConstraints);

        btn24Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn24Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel35.add(btn24Vestib, gridBagConstraints);

        btn24Palatino.setForeground(new java.awt.Color(51, 51, 51));
        btn24Palatino.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel35.add(btn24Palatino, gridBagConstraints);

        btn24Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn24Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel35.add(btn24Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jPanel35, gridBagConstraints);

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.setLayout(new java.awt.GridBagLayout());

        btn23Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn23Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel36.add(btn23Mesial, gridBagConstraints);

        btn23Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn23Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel36.add(btn23Distal, gridBagConstraints);

        btn23Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn23Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel36.add(btn23Vestib, gridBagConstraints);

        btn23Palatino.setForeground(new java.awt.Color(51, 51, 51));
        btn23Palatino.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel36.add(btn23Palatino, gridBagConstraints);

        btn23Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn23Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel36.add(btn23Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jPanel36, gridBagConstraints);

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setLayout(new java.awt.GridBagLayout());

        btn22Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn22Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel37.add(btn22Mesial, gridBagConstraints);

        btn22Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn22Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel37.add(btn22Distal, gridBagConstraints);

        btn22Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn22Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel37.add(btn22Vestib, gridBagConstraints);

        btn22Palatino.setForeground(new java.awt.Color(51, 51, 51));
        btn22Palatino.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel37.add(btn22Palatino, gridBagConstraints);

        btn22Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn22Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        btn22Oclusal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn22OclusalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel37.add(btn22Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jPanel37, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jPanel4, gridBagConstraints);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        btn48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/48.jpg"))); // NOI18N
        btn48.setName("48"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(btn48, gridBagConstraints);

        btn47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/47.jpg"))); // NOI18N
        btn47.setName("47"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(btn47, gridBagConstraints);

        btn46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/46.jpg"))); // NOI18N
        btn46.setName("46"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(btn46, gridBagConstraints);

        btn45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/45.jpg"))); // NOI18N
        btn45.setName("45"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(btn45, gridBagConstraints);

        btn44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/44.jpg"))); // NOI18N
        btn44.setName("44"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(btn44, gridBagConstraints);

        btn43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/43.jpg"))); // NOI18N
        btn43.setName("43"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(btn43, gridBagConstraints);

        btn42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/42.jpg"))); // NOI18N
        btn42.setName("42"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(btn42, gridBagConstraints);

        btn41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/41.jpg"))); // NOI18N
        btn41.setName("41"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(btn41, gridBagConstraints);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new java.awt.GridBagLayout());

        btn48Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn48Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel14.add(btn48Distal, gridBagConstraints);

        btn48Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn48Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel14.add(btn48Mesial, gridBagConstraints);

        btn48Lingual.setForeground(new java.awt.Color(51, 51, 51));
        btn48Lingual.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel14.add(btn48Lingual, gridBagConstraints);

        btn48Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn48Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel14.add(btn48Vestib, gridBagConstraints);

        btn48Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn48Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel14.add(btn48Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jPanel14, gridBagConstraints);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(new java.awt.GridBagLayout());

        btn46Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn46Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel15.add(btn46Distal, gridBagConstraints);

        btn46Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn46Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel15.add(btn46Mesial, gridBagConstraints);

        btn46Lingual.setForeground(new java.awt.Color(51, 51, 51));
        btn46Lingual.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel15.add(btn46Lingual, gridBagConstraints);

        btn46Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn46Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel15.add(btn46Vestib, gridBagConstraints);

        btn46Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn46Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel15.add(btn46Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jPanel15, gridBagConstraints);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new java.awt.GridBagLayout());

        btn45Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn45Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel16.add(btn45Distal, gridBagConstraints);

        btn45Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn45Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel16.add(btn45Mesial, gridBagConstraints);

        btn45Lingual.setForeground(new java.awt.Color(51, 51, 51));
        btn45Lingual.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel16.add(btn45Lingual, gridBagConstraints);

        btn45Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn45Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel16.add(btn45Vestib, gridBagConstraints);

        btn45Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn45Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel16.add(btn45Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jPanel16, gridBagConstraints);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setLayout(new java.awt.GridBagLayout());

        btn44Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn44Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel17.add(btn44Distal, gridBagConstraints);

        btn44Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn44Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel17.add(btn44Mesial, gridBagConstraints);

        btn44Lingual.setForeground(new java.awt.Color(51, 51, 51));
        btn44Lingual.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel17.add(btn44Lingual, gridBagConstraints);

        btn44Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn44Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel17.add(btn44Vestib, gridBagConstraints);

        btn44Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn44Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel17.add(btn44Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jPanel17, gridBagConstraints);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setLayout(new java.awt.GridBagLayout());

        btn43Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn43Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel18.add(btn43Distal, gridBagConstraints);

        btn43Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn43Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel18.add(btn43Mesial, gridBagConstraints);

        btn43Lingual.setForeground(new java.awt.Color(51, 51, 51));
        btn43Lingual.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel18.add(btn43Lingual, gridBagConstraints);

        btn43Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn43Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel18.add(btn43Vestib, gridBagConstraints);

        btn43Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn43Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel18.add(btn43Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jPanel18, gridBagConstraints);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setLayout(new java.awt.GridBagLayout());

        btn42Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn42Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel19.add(btn42Distal, gridBagConstraints);

        btn42Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn42Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel19.add(btn42Mesial, gridBagConstraints);

        btn42Lingual.setForeground(new java.awt.Color(51, 51, 51));
        btn42Lingual.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel19.add(btn42Lingual, gridBagConstraints);

        btn42Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn42Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel19.add(btn42Vestib, gridBagConstraints);

        btn42Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn42Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel19.add(btn42Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jPanel19, gridBagConstraints);

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setLayout(new java.awt.GridBagLayout());

        btn41Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn41Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel20.add(btn41Distal, gridBagConstraints);

        btn41Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn41Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel20.add(btn41Mesial, gridBagConstraints);

        btn41Lingual.setForeground(new java.awt.Color(51, 51, 51));
        btn41Lingual.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel20.add(btn41Lingual, gridBagConstraints);

        btn41Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn41Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel20.add(btn41Vestib, gridBagConstraints);

        btn41Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn41Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel20.add(btn41Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jPanel20, gridBagConstraints);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setLayout(new java.awt.GridBagLayout());

        btn47Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn47Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel21.add(btn47Distal, gridBagConstraints);

        btn47Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn47Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel21.add(btn47Mesial, gridBagConstraints);

        btn47Lingual.setForeground(new java.awt.Color(51, 51, 51));
        btn47Lingual.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel21.add(btn47Lingual, gridBagConstraints);

        btn47Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn47Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel21.add(btn47Vestib, gridBagConstraints);

        btn47Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn47Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel21.add(btn47Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jPanel21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        btn31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/31.jpg"))); // NOI18N
        btn31.setName("31"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel5.add(btn31, gridBagConstraints);

        btn32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/32.jpg"))); // NOI18N
        btn32.setName("32"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel5.add(btn32, gridBagConstraints);

        btn33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/33.jpg"))); // NOI18N
        btn33.setName("33"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel5.add(btn33, gridBagConstraints);

        btn34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/34.jpg"))); // NOI18N
        btn34.setName("34"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel5.add(btn34, gridBagConstraints);

        btn35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/35.jpg"))); // NOI18N
        btn35.setName("35"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel5.add(btn35, gridBagConstraints);

        btn36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/36.jpg"))); // NOI18N
        btn36.setName("36"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel5.add(btn36, gridBagConstraints);

        btn37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/37.jpg"))); // NOI18N
        btn37.setName("37"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel5.add(btn37, gridBagConstraints);

        btn38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Normal/38.jpg"))); // NOI18N
        btn38.setName("38"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel5.add(btn38, gridBagConstraints);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setLayout(new java.awt.GridBagLayout());

        btn31Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn31Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel22.add(btn31Mesial, gridBagConstraints);

        btn31Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn31Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel22.add(btn31Distal, gridBagConstraints);

        btn31Lingual.setForeground(new java.awt.Color(51, 51, 51));
        btn31Lingual.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel22.add(btn31Lingual, gridBagConstraints);

        btn31Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn31Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel22.add(btn31Vestib, gridBagConstraints);

        btn31Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn31Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel22.add(btn31Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jPanel22, gridBagConstraints);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setLayout(new java.awt.GridBagLayout());

        btn38Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn38Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel23.add(btn38Mesial, gridBagConstraints);

        btn38Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn38Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel23.add(btn38Distal, gridBagConstraints);

        btn38Lingual.setForeground(new java.awt.Color(51, 51, 51));
        btn38Lingual.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel23.add(btn38Lingual, gridBagConstraints);

        btn38Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn38Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel23.add(btn38Vestib, gridBagConstraints);

        btn38Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn38Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel23.add(btn38Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jPanel23, gridBagConstraints);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setLayout(new java.awt.GridBagLayout());

        btn37Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn37Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel24.add(btn37Mesial, gridBagConstraints);

        btn37Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn37Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel24.add(btn37Distal, gridBagConstraints);

        btn37Lingual.setForeground(new java.awt.Color(51, 51, 51));
        btn37Lingual.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel24.add(btn37Lingual, gridBagConstraints);

        btn37Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn37Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel24.add(btn37Vestib, gridBagConstraints);

        btn37Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn37Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel24.add(btn37Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jPanel24, gridBagConstraints);

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setLayout(new java.awt.GridBagLayout());

        btn36Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn36Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel25.add(btn36Mesial, gridBagConstraints);

        btn36Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn36Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel25.add(btn36Distal, gridBagConstraints);

        btn36Lingual.setForeground(new java.awt.Color(51, 51, 51));
        btn36Lingual.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel25.add(btn36Lingual, gridBagConstraints);

        btn36Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn36Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel25.add(btn36Vestib, gridBagConstraints);

        btn36Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn36Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel25.add(btn36Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jPanel25, gridBagConstraints);

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setLayout(new java.awt.GridBagLayout());

        btn35Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn35Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel26.add(btn35Mesial, gridBagConstraints);

        btn35Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn35Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel26.add(btn35Distal, gridBagConstraints);

        btn35Lingual.setForeground(new java.awt.Color(51, 51, 51));
        btn35Lingual.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel26.add(btn35Lingual, gridBagConstraints);

        btn35Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn35Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel26.add(btn35Vestib, gridBagConstraints);

        btn35Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn35Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel26.add(btn35Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jPanel26, gridBagConstraints);

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setLayout(new java.awt.GridBagLayout());

        btn34Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn34Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel27.add(btn34Mesial, gridBagConstraints);

        btn34Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn34Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel27.add(btn34Distal, gridBagConstraints);

        btn34Lingual.setForeground(new java.awt.Color(51, 51, 51));
        btn34Lingual.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel27.add(btn34Lingual, gridBagConstraints);

        btn34Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn34Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel27.add(btn34Vestib, gridBagConstraints);

        btn34Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn34Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel27.add(btn34Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jPanel27, gridBagConstraints);

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setLayout(new java.awt.GridBagLayout());

        btn33Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn33Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel28.add(btn33Mesial, gridBagConstraints);

        btn33Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn33Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel28.add(btn33Distal, gridBagConstraints);

        btn33Lingual.setForeground(new java.awt.Color(51, 51, 51));
        btn33Lingual.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel28.add(btn33Lingual, gridBagConstraints);

        btn33Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn33Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel28.add(btn33Vestib, gridBagConstraints);

        btn33Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn33Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel28.add(btn33Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jPanel28, gridBagConstraints);

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setLayout(new java.awt.GridBagLayout());

        btn32Mesial.setForeground(new java.awt.Color(51, 51, 51));
        btn32Mesial.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel29.add(btn32Mesial, gridBagConstraints);

        btn32Distal.setForeground(new java.awt.Color(51, 51, 51));
        btn32Distal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel29.add(btn32Distal, gridBagConstraints);

        btn32Lingual.setForeground(new java.awt.Color(51, 51, 51));
        btn32Lingual.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel29.add(btn32Lingual, gridBagConstraints);

        btn32Vestib.setForeground(new java.awt.Color(51, 51, 51));
        btn32Vestib.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel29.add(btn32Vestib, gridBagConstraints);

        btn32Oclusal.setForeground(new java.awt.Color(51, 51, 51));
        btn32Oclusal.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel29.add(btn32Oclusal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jPanel29, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jPanel5, gridBagConstraints);

        jPanel38.setLayout(new java.awt.GridBagLayout());

        jRadioButton1.setText("Adulto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel38.add(jRadioButton1, gridBagConstraints);

        jRadioButton2.setText("Niño");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel38.add(jRadioButton2, gridBagConstraints);

        buttonGroup1.add(Limpiar);
        Limpiar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        Limpiar.setText("Limpiar");
        Limpiar.setPreferredSize(new java.awt.Dimension(70, 70));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel38.add(Limpiar, gridBagConstraints);

        buttonGroup1.add(Carie);
        Carie.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        Carie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caries.png"))); // NOI18N
        Carie.setText("Caries");
        Carie.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Carie.setPreferredSize(new java.awt.Dimension(70, 70));
        Carie.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel38.add(Carie, gridBagConstraints);

        buttonGroup1.add(Aparato);
        Aparato.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        Aparato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aparato.png"))); // NOI18N
        Aparato.setText("Aparato");
        Aparato.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Aparato.setPreferredSize(new java.awt.Dimension(70, 70));
        Aparato.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel38.add(Aparato, gridBagConstraints);

        buttonGroup1.add(Implante);
        Implante.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        Implante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/implante.png"))); // NOI18N
        Implante.setText("Implante");
        Implante.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Implante.setPreferredSize(new java.awt.Dimension(70, 70));
        Implante.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel38.add(Implante, gridBagConstraints);

        buttonGroup1.add(Fractura);
        Fractura.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        Fractura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fractura.png"))); // NOI18N
        Fractura.setText("Fractura");
        Fractura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Fractura.setPreferredSize(new java.awt.Dimension(70, 70));
        Fractura.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel38.add(Fractura, gridBagConstraints);

        buttonGroup1.add(Ausente);
        Ausente.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        Ausente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/diente ausente.png"))); // NOI18N
        Ausente.setText("Ausente");
        Ausente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Ausente.setPreferredSize(new java.awt.Dimension(70, 70));
        Ausente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Ausente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AusenteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel38.add(Ausente, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jPanel38, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn22OclusalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn22OclusalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn22OclusalActionPerformed

    private void btn18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn18ActionPerformed


    }//GEN-LAST:event_btn18ActionPerformed

    private void AusenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AusenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AusenteActionPerformed

    /**
     * @param args the command line arguments
     */
  /*  public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Odontograma.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Odontograma.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Odontograma.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Odontograma.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
       /* java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Odontograma().setVisible(true);
            }
        });*/
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton Aparato;
    private javax.swing.JToggleButton Ausente;
    private javax.swing.JToggleButton Carie;
    private javax.swing.JToggleButton Fractura;
    private javax.swing.JToggleButton Implante;
    private javax.swing.JToggleButton Limpiar;
    private javax.swing.JButton btn11;
    private javax.swing.JButton btn11Distal;
    private javax.swing.JButton btn11Mesial;
    private javax.swing.JButton btn11Oclusal;
    private javax.swing.JButton btn11Palatino;
    private javax.swing.JButton btn11Vestib;
    private javax.swing.JButton btn12;
    private javax.swing.JButton btn12Distal;
    private javax.swing.JButton btn12Mesial;
    private javax.swing.JButton btn12Oclusal;
    private javax.swing.JButton btn12Palatino;
    private javax.swing.JButton btn12Vestib;
    private javax.swing.JButton btn13;
    private javax.swing.JButton btn13Distal;
    private javax.swing.JButton btn13Mesial;
    private javax.swing.JButton btn13Oclusal;
    private javax.swing.JButton btn13Palatino;
    private javax.swing.JButton btn13Vestib;
    private javax.swing.JButton btn14;
    private javax.swing.JButton btn14Distal;
    private javax.swing.JButton btn14Mesial;
    private javax.swing.JButton btn14Oclusal;
    private javax.swing.JButton btn14Palatino;
    private javax.swing.JButton btn14Vestib;
    private javax.swing.JButton btn15;
    private javax.swing.JButton btn15Distal;
    private javax.swing.JButton btn15Mesial;
    private javax.swing.JButton btn15Oclusal;
    private javax.swing.JButton btn15Palatino;
    private javax.swing.JButton btn15Vestib;
    private javax.swing.JButton btn16;
    private javax.swing.JButton btn16Distal;
    private javax.swing.JButton btn16Mesial;
    private javax.swing.JButton btn16Oclusal;
    private javax.swing.JButton btn16Palatino;
    private javax.swing.JButton btn16Vestib;
    private javax.swing.JButton btn17;
    private javax.swing.JButton btn17Distal;
    private javax.swing.JButton btn17Mesial;
    private javax.swing.JButton btn17Oclusal;
    private javax.swing.JButton btn17Palatino;
    private javax.swing.JButton btn17Vestib;
    private javax.swing.JButton btn18;
    private javax.swing.JButton btn18Distal;
    private javax.swing.JButton btn18Mesial;
    private javax.swing.JButton btn18Oclusal;
    private javax.swing.JButton btn18Palatino;
    private javax.swing.JButton btn18Vestib;
    private javax.swing.JButton btn21;
    private javax.swing.JButton btn21Distal;
    private javax.swing.JButton btn21Mesial;
    private javax.swing.JButton btn21Oclusal;
    private javax.swing.JButton btn21Palatino;
    private javax.swing.JButton btn21Vestib;
    private javax.swing.JButton btn22;
    private javax.swing.JButton btn22Distal;
    private javax.swing.JButton btn22Mesial;
    private javax.swing.JButton btn22Oclusal;
    private javax.swing.JButton btn22Palatino;
    private javax.swing.JButton btn22Vestib;
    private javax.swing.JButton btn23;
    private javax.swing.JButton btn23Distal;
    private javax.swing.JButton btn23Mesial;
    private javax.swing.JButton btn23Oclusal;
    private javax.swing.JButton btn23Palatino;
    private javax.swing.JButton btn23Vestib;
    private javax.swing.JButton btn24;
    private javax.swing.JButton btn24Distal;
    private javax.swing.JButton btn24Mesial;
    private javax.swing.JButton btn24Oclusal;
    private javax.swing.JButton btn24Palatino;
    private javax.swing.JButton btn24Vestib;
    private javax.swing.JButton btn25;
    private javax.swing.JButton btn25Distal;
    private javax.swing.JButton btn25Mesial;
    private javax.swing.JButton btn25Oclusal;
    private javax.swing.JButton btn25Palatino;
    private javax.swing.JButton btn25Vestib;
    private javax.swing.JButton btn26;
    private javax.swing.JButton btn26Distal;
    private javax.swing.JButton btn26Mesial;
    private javax.swing.JButton btn26Oclusal;
    private javax.swing.JButton btn26Palatino;
    private javax.swing.JButton btn26Vestib;
    private javax.swing.JButton btn27;
    private javax.swing.JButton btn27Distal;
    private javax.swing.JButton btn27Mesial;
    private javax.swing.JButton btn27Oclusal;
    private javax.swing.JButton btn27Palatino;
    private javax.swing.JButton btn27Vestib;
    private javax.swing.JButton btn28;
    private javax.swing.JButton btn28Distal;
    private javax.swing.JButton btn28Mesial;
    private javax.swing.JButton btn28Oclusal;
    private javax.swing.JButton btn28Palatino;
    private javax.swing.JButton btn28Vestib;
    private javax.swing.JButton btn31;
    private javax.swing.JButton btn31Distal;
    private javax.swing.JButton btn31Lingual;
    private javax.swing.JButton btn31Mesial;
    private javax.swing.JButton btn31Oclusal;
    private javax.swing.JButton btn31Vestib;
    private javax.swing.JButton btn32;
    private javax.swing.JButton btn32Distal;
    private javax.swing.JButton btn32Lingual;
    private javax.swing.JButton btn32Mesial;
    private javax.swing.JButton btn32Oclusal;
    private javax.swing.JButton btn32Vestib;
    private javax.swing.JButton btn33;
    private javax.swing.JButton btn33Distal;
    private javax.swing.JButton btn33Lingual;
    private javax.swing.JButton btn33Mesial;
    private javax.swing.JButton btn33Oclusal;
    private javax.swing.JButton btn33Vestib;
    private javax.swing.JButton btn34;
    private javax.swing.JButton btn34Distal;
    private javax.swing.JButton btn34Lingual;
    private javax.swing.JButton btn34Mesial;
    private javax.swing.JButton btn34Oclusal;
    private javax.swing.JButton btn34Vestib;
    private javax.swing.JButton btn35;
    private javax.swing.JButton btn35Distal;
    private javax.swing.JButton btn35Lingual;
    private javax.swing.JButton btn35Mesial;
    private javax.swing.JButton btn35Oclusal;
    private javax.swing.JButton btn35Vestib;
    private javax.swing.JButton btn36;
    private javax.swing.JButton btn36Distal;
    private javax.swing.JButton btn36Lingual;
    private javax.swing.JButton btn36Mesial;
    private javax.swing.JButton btn36Oclusal;
    private javax.swing.JButton btn36Vestib;
    private javax.swing.JButton btn37;
    private javax.swing.JButton btn37Distal;
    private javax.swing.JButton btn37Lingual;
    private javax.swing.JButton btn37Mesial;
    private javax.swing.JButton btn37Oclusal;
    private javax.swing.JButton btn37Vestib;
    private javax.swing.JButton btn38;
    private javax.swing.JButton btn38Distal;
    private javax.swing.JButton btn38Lingual;
    private javax.swing.JButton btn38Mesial;
    private javax.swing.JButton btn38Oclusal;
    private javax.swing.JButton btn38Vestib;
    private javax.swing.JButton btn41;
    private javax.swing.JButton btn41Distal;
    private javax.swing.JButton btn41Lingual;
    private javax.swing.JButton btn41Mesial;
    private javax.swing.JButton btn41Oclusal;
    private javax.swing.JButton btn41Vestib;
    private javax.swing.JButton btn42;
    private javax.swing.JButton btn42Distal;
    private javax.swing.JButton btn42Lingual;
    private javax.swing.JButton btn42Mesial;
    private javax.swing.JButton btn42Oclusal;
    private javax.swing.JButton btn42Vestib;
    private javax.swing.JButton btn43;
    private javax.swing.JButton btn43Distal;
    private javax.swing.JButton btn43Lingual;
    private javax.swing.JButton btn43Mesial;
    private javax.swing.JButton btn43Oclusal;
    private javax.swing.JButton btn43Vestib;
    private javax.swing.JButton btn44;
    private javax.swing.JButton btn44Distal;
    private javax.swing.JButton btn44Lingual;
    private javax.swing.JButton btn44Mesial;
    private javax.swing.JButton btn44Oclusal;
    private javax.swing.JButton btn44Vestib;
    private javax.swing.JButton btn45;
    private javax.swing.JButton btn45Distal;
    private javax.swing.JButton btn45Lingual;
    private javax.swing.JButton btn45Mesial;
    private javax.swing.JButton btn45Oclusal;
    private javax.swing.JButton btn45Vestib;
    private javax.swing.JButton btn46;
    private javax.swing.JButton btn46Distal;
    private javax.swing.JButton btn46Lingual;
    private javax.swing.JButton btn46Mesial;
    private javax.swing.JButton btn46Oclusal;
    private javax.swing.JButton btn46Vestib;
    private javax.swing.JButton btn47;
    private javax.swing.JButton btn47Distal;
    private javax.swing.JButton btn47Lingual;
    private javax.swing.JButton btn47Mesial;
    private javax.swing.JButton btn47Oclusal;
    private javax.swing.JButton btn47Vestib;
    private javax.swing.JButton btn48;
    private javax.swing.JButton btn48Distal;
    private javax.swing.JButton btn48Lingual;
    private javax.swing.JButton btn48Mesial;
    private javax.swing.JButton btn48Oclusal;
    private javax.swing.JButton btn48Vestib;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    // End of variables declaration//GEN-END:variables
}
